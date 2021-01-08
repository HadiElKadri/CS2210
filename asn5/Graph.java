import java.util.Iterator;
import java.util.Stack;
/**
 * @author Hadi El-Kadri
 * Public class Graph implements a Graph Object storing the edges between two nodes.
 */
public class Graph implements GraphADT {
	
	private Node[] narray;
    private Edge[][] ematrix;
    private int size; 
    
    /**
     * Constructor of the class Graph takes an int as input and creates an array of nodes as well as a 2-D array
     * of edges
     * @param n the size of the node array and edge array and node names also from 0 to n-1
     */
    public Graph(int n){
		
		// Create array of size n and edge matrix n x n 
		this.narray = new Node[n];
		this.ematrix = new Edge[n][n];
		this.size = n;
		
		// Create nodes and name them from 0 to n - 1 and put them in the array at the respective indices
		 for (int i = 0; i < n; i++){
	            Node newnode = new Node(i);
	            narray[i] = newnode;
	            
	            // Initialize the edge matrix
	            for (int j = 0; j < n; j++){
	            	ematrix[i][j] = null;
	            	}
	            }
    }
	
    /**
     * Public class insertEdge will insert an edge of a given type connecting node u and node v
     * @param nodeu a node u to be connected to edge 
     * @param nodev a node v to be connected to edge
     * @param edgeType the type of the edge to be inserted 
     */
	public void insertEdge(Node nodeu, Node nodev, int edgeType) throws GraphException {
		
		// Check if both nodes are present in the node array
		if (inArray(nodeu, nodev) == false) {
			throw new GraphException();
		}
		
		else {
			
			// Make a new edge == the edge matrix at the indices of the names of the nodes
			Edge edge = ematrix[nodeu.getName()][nodev.getName()];
	        
			// If the edge is not null this means there is an edge present
	        if(edge != null){
	            throw new GraphException();
	        }
	        
	        // Make new 2 new edges one from node u to v and vice versa and add into ematrix indices (since its symmetrical)
	        else{
	           ematrix[nodeu.getName()][nodev.getName()] = new Edge(nodeu,nodev,edgeType);
	           ematrix[nodev.getName()][nodeu.getName()] = new Edge(nodev,nodeu,edgeType);
	           }
	        } 
		}
	
	/**
	 * Public class getNode returns the node with the input name. If no node with this
	 * name exists, throw a GraphException.
	 * @param name is the name of the node that will be returned
	 */
	public Node getNode(int name) throws GraphException {
        
		// If the name is not in between 0 and the size of the array throw exception
		if(name < 0 || name >= size){
            throw new GraphException();
        }
		
		// Return the node at the index name
        else{
            return narray[name];
        }
    }
	
	/**
	 * public class incidentEdges returns a Java iterator storing edges incident on u or null if there are none incident
	 * @param u the node to be checked for its incident edges
	 */
	public Iterator incidentEdges(Node u) throws GraphException {
		
		// Find the node in the node array
		getNode(u.getName());

         // Creates a new stack of edges
         Stack<Edge> edgeStack = new Stack<>();

         for(int i = 0; i < size; i ++){
             // if there is a incident edge then push it to the stack
             if(ematrix[u.getName()][i] != null){
                 edgeStack.push(ematrix[u.getName()][i]);
             }
         }
         return edgeStack.iterator();
     }
	
	/**
	 * Public class getEdge returns edge that connects node u and v or throw
	 * an exception if the edge doesn't exist
	 * @param u a node u
	 * @param v a node v
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		// Check if both nodes are in the node array
        if(inArray(u,v)){
        
            // If there exists and edge between the two GraphNodes return it
            if(ematrix[u.getName()][v.getName()] != null){
                return ematrix[u.getName()][v.getName()];
            }
            
            // Throw a GraphException if there is no edge between the two nodes
            else{
                throw new GraphException();
            }
        }
        // Throw a GraphException if the nodes don't exist in the node array
        else{
            throw new GraphException();
        }
    }

	/**
	 * 
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if(inArray(u,v)) {
			
			// If the nodes have an edge between them return true because they are adjacent
            if(ematrix[u.getName()][v.getName()] != null){
                return true;
            }
            
            // Return false if there is no edge between them because they are not adjacent
            else{
                return false;
            }
		}
		
		// Throw GraphException if the nodes are not in the graph
        else{
            throw new GraphException();
        }
    }
	
	/**
	 * Helper method inArray to check if 2 input nodes are in the node array
	 * @param u the first node
	 * @param v the second node
	 * @return true if both nodes are in the array false if both or one is missing
	 */
	private boolean inArray(Node u, Node v) {
		try {
            // If both nodes are in the graph return true
            getNode(u.getName());
            getNode(v.getName());
            return true;
        }
        // If one or both nodes do not exist in the graph catch the exception thrown by class getNode
        catch (GraphException e){
            return false;
        }
    }
}
