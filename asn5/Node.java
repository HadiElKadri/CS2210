/**
 * @author Hadi El-Kadri
 * Public class node will represent a node of the graph. It will have a name which is an integer
 * value between 0 and n-1 where n is the number of nodes in the graph. It can also be marked
 * true or false.
 */

public class Node {

    private int name;
    private boolean mark;

    /** Constructs a new node
     * @param name will name the node with the input integer value
     * Mark will be set to false
     */
    public Node(int name){
        this.name = name;
        mark = false;
    }

    /**
     * Sets the mark of a node to true or false
     * @param mark true or false
     */
    public void setMark(boolean mark){
        this.mark = mark;
    }

    /**
     * Gets the mark value of the node
     * @return mark
     */
    public Boolean getMark(){
        return this.mark;
    }

    /**
     * Gets the name of the node
     * @return name
     */
    public int getName(){
        return this.name;
    }
}
