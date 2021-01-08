/**
 * @author Hadi El-Kadri 
 * Class BinaryNode represents a node of a binary search tree
 * Each node will have a pixel object inside and reference the left and right child 
 * of the node as well as its parent
 */
public class BinaryNode {
	
	private Pixel value;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	
	/**
	 * Constructs a new node
	 * @param value pixel object stored in the node
	 * @param left reference to the left child of the node
	 * @param right reference to the right child of the node
	 * @param parent reference to the parent of the node
	 */
	public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	/**
	 * Constructor that initializes a leaf node
	 */
	public BinaryNode () {
		value = null;
		left = null;
		right = null;
		parent = null;
	}
	
	/**
	 * @return parent of the node
	 */
	public BinaryNode getParent() {
		return parent;
	}
	
	/**
	 * @param parent set parent of the node to given parameter parent
	 */
	public void setParent(BinaryNode parent){
		this.parent = parent;
	}
	
	/**
	 * @param p set left child of the node to given parameter p
	 */
	public void setLeft (BinaryNode p) {
		this.left = p;
	}
	
	/**
	 * @param p set right child of the node to given parameter p
	 */
	public void setRight (BinaryNode p) {
		this.right = p;
	}
	
	/**
	 * @param value set value of pixel object in node to given parameter value
	 */
	public void setData (Pixel value) {
		this.value = value;
	}
	
	/** 
	 * @return true if node is leaf, false if not
	 */
	public boolean isLeaf() {
		if(left == null && right == null) return true;
		else return false;
		}
	
	/**
	 * @return Pixel object value in node
	 */
	public Pixel getData () {
		return value;
	}
	
	/**
	 * @return left child of the node
	 */
	public BinaryNode getLeft() {
		return left;
	}
	
	/**
	 * @return right child of the node
	 */
	public BinaryNode getRight() {
		return right;
	}
}
