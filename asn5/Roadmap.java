import java.io.*;
import java.util.*;

/**
 * @author Hadi El-Kadri
 * Public class Roadmap implements a Roadmap object which constructs a map from an input file.
 */
public class RoadMap {
	 private Graph graph;
	 private int scale, start, end, width, length, initial_budget,toll, gain;
	 private Stack<Node> stack = new Stack<Node>();
	 
	 /**
	  * Class constructor constructs a roadmap out of an input file
	  * @param inputFile a map
	  * @throws MapException if the file does not exist
	  * @throws GraphException if the graph can not be made
	  */
	 public RoadMap(String inputFile) throws MapException, GraphException {
		 BufferedReader reader;
		 try {
			 
			 // If the file is correct, read the file line by line converting the string integers to regular integers
			 //and assign the values to the variables given in order
			 reader = new BufferedReader(new FileReader(inputFile));
			 this.scale = Integer.parseInt(reader.readLine());
			 this.start = Integer.parseInt(reader.readLine());
			 this.end = Integer.parseInt(reader.readLine());
			 this.width = Integer.parseInt(reader.readLine());
			 this.length = Integer.parseInt(reader.readLine());
			 this.initial_budget = Integer.parseInt(reader.readLine());
			 this.toll = Integer.parseInt(reader.readLine());
			 this.gain = Integer.parseInt(reader.readLine());
			 
			 // Construct a graph using the width and length
			 this.graph = new Graph(width * length);
			 
			 
			 String line, line2;
			 line = reader.readLine();
			 
			 // A count for horizontal nodes being checked
			 int hcheck = 0;
			 
			 // A count for vertical nodes being checked
			 int vcheck = 0;
			 
			 // Read every line of the file
			 while(line != null){
				 
				 // Start at index 0 and since a node is in every other position increment by 2
				 for (int i = 0; i < line.length(); i += 2) {
					 
					 // Check to see if their is an intersection or dead end and that it is within the line
					 if (line.charAt(i) == '+') {
						 
						 if (i + 2 < line.length()) {
						 
						 // If the character is not "X" (a block of houses
						 //then find check what kind of road it is by character value and connect the two HORIZONTAL nodes
							 if (line.charAt(i + 1) != 'X') {
								 
								 if(line.charAt(i + 1) == 'F') {
									 graph.insertEdge(graph.getNode(hcheck), graph.getNode(hcheck + 1), 0);
								 }
								 
								 else if(line.charAt(i + 1) == 'T') {
									 graph.insertEdge(graph.getNode(hcheck), graph.getNode(hcheck + 1), 1);
								 }
								 
								 else if(line.charAt(i + 1) == 'C') {
									 graph.insertEdge(graph.getNode(hcheck), graph.getNode(hcheck + 1), -1);
								 }
								 }
						 }
						hcheck = hcheck + 1; 
						}
					 }
				 
				 line2 = reader.readLine();
				 if (line2 == null) {
					 break;
					 }
				 
				 // Start at index 0 and since a node is in every other position 
				 for (int j = 0; j < line2.length(); j += 2) {
					 
					// If the character is not "X" (a block of houses
					//then find check what kind of road it is by character value and connect the two VERTICAL nodes
					 if (line2.charAt(j) != 'X') {
						 
						 if(line2.charAt(j) == 'F') {
							 graph.insertEdge(graph.getNode(vcheck), graph.getNode(vcheck + width), 0);
							 vcheck = vcheck + 1;
						 }
						 
						 else if(line2.charAt(j) == 'T') {
							 graph.insertEdge(graph.getNode(vcheck), graph.getNode(vcheck + width), 1);
							 vcheck = vcheck + 1;
						 }
						 
						 else if(line2.charAt(j) == 'C') {
							 graph.insertEdge(graph.getNode(vcheck), graph.getNode(vcheck + width), -1);
							 vcheck = vcheck + 1;
						 }
						 
						 else {
							 vcheck = vcheck + 1;
						 }
					 }
				 }
				 
				 // Get the next line
				 line = reader.readLine();
			 }
			 
			 // Close the reader
			 reader.close();
		 } 
		 
		 // Catch an exception if the input is incorrect
		 catch (IOException e) {
	            throw new MapException();
	        }
	    }
	 
	 /**
	  * Public class getGraph() returns the graph of the RoadMap object
	  * @return graph 
	  */
	 public Graph getGraph(){
		 return this.graph;
	 }
	 
	 /**
	  * Public class getStartingNode returns the starting node of the RoadMap object
	  * @return start
	  */
	 public int getStartingNode() {
		 return this.start;
	 }
	 
	 /**
	  * Public class getDestinationNode returns the final destination node of the RoadMap object
	  * @return
	  */
	 public int getDestinationNode() {
		 return this.end;
	 }
	 
	 /**
	  * Public class getInitialMoney returns the initial budget of the RoadMap object
	  * @return initial budget
	  */
	 public int getInitialMoney() {
			return this.initial_budget;
		}
	 
	 /**
	  * Public class findPath will return a Java Iterator containing the nodes of a path from the start node to the destination node if such a path exists
	  * @param start starting node
	  * @param destination destination node
	  * @param initialMoney how much money there is to pay for toll routes 
	  * @return java iterator containing nodes of the path
	  */
	 public Iterator<Node> findPath(int start, int destination, int initialMoney) {
		 try {
			 
			 // Get the start and end destination from the graph
			 Node StartNode = graph.getNode(start);
			 Node DestinationNode = graph.getNode(destination);
			 
			 // Push the start node onto the stack and mark as true
			 stack.push(StartNode);
			 StartNode.setMark(true);
			 
			 // Check if destination is reached
			 if (stack.peek().equals(DestinationNode)) {
					return stack.iterator();
				} 
			 
			 else {
				 
				 // Create an iterator for the edges incident to the starting node
				 Iterator<Edge> currentNodeEdges = graph.incidentEdges(StartNode);
				 
				 // While there are edges incident to the node
				 while (currentNodeEdges.hasNext()) {
					 
					 Edge edge = currentNodeEdges.next();
					 int currentMoney = initialMoney;
					 Iterator<Node> path;
					 
					 // If the first endpoint is the start then check the endpoint of the edge
					 if (edge.firstEndpoint().getName() == start) {
						 
						 // If the endpoint is not marked check the type
						 if (edge.secondEndpoint().getMark() == false){
							 
							 if (edge.getType() == 1) {
								currentMoney = currentMoney - toll;
							 }
							 
							 else if (edge.getType() == -1) {
								currentMoney = currentMoney + gain;
								}
							 else if (currentMoney < 0) {
								continue;
							}
							 
							// Recursively call path to build a path from the node if one exists, to the destination
							path = findPath(edge.secondEndpoint().getName(), destination, currentMoney);
							
							// Return the path if there exists a path
							if (path != null) {
								return path;
							}
						 }
					 }
					 
					 else {
						 if (edge.firstEndpoint().getMark() == false){
							 if (edge.getType() == 1) {
								currentMoney = currentMoney - toll;
							 }
							 else if (edge.getType() == -1) {
								currentMoney = currentMoney + gain;
								}
							 else if (currentMoney < 0) {
								continue;
							}
							 
							path = findPath(edge.firstEndpoint().getName(), destination, currentMoney);
							if (path != null) {
								return path;
							}
					 }
					 }
				 }
			 }
		 }
				 catch (GraphException e) {
				e.printStackTrace();
			}
			
		 // If no edge is found pop it from the stack
		 stack.peek().setMark(false);
		 stack.pop();
		 return null;
	 }
}
