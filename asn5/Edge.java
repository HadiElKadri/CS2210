/**
 * @author Hadi El-Kadri
 * Class Edge represents an edge of the graph 
 */
public class Edge {
	
	private Node firstendpoint, secondendpoint;
	int type;
	
	/**
	 * Constructs an edge
	 * @param u the first endpoint of the edge
	 * @param v the second endpoint of the edge
	 * @param type 0: public road, 1: public road, -1: private road
	 */
	public Edge(Node u, Node v, int type){
		this.firstendpoint = u;
		this.secondendpoint = v;
		this.type = type;
	}
	
	/**
	 * Getter method for the first endpoint
	 * @return firstendpoint of edge
	 */
	public Node firstEndpoint(){
		return this.firstendpoint;
	}
	
	/**
	 * Getter method for the second endpoint
	 * @return secondendpoint of edge
	 */
	public Node secondEndpoint(){
		return this.secondendpoint;
	}
	
	/**
	 * Returns type of edge
	 * @return type
	 */
	public int getType(){
		return this.type;
	}
}
