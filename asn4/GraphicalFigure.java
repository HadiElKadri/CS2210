/**
 * @author Hadi El-Kadri
 * Class GraphicalFigure
 */

public class GraphicalFigure implements GraphicalFigureADT{
	
	//INSTANCE VARIABLES
	private int gfId;
	private int gfWidth;
	private int gfHeight;
	private String gfType;
	private Location gfPos;
	private BinarySearchTree bst;
	
	/**
	 * Graphical figure constructor
	 * @param id identifier of the figure
	 * @param width width of the enclosing rectangle for this figure
	 * @param height height of the enclosing rectangle for this figure
	 * @param type the type of figure
	 * @param pos the offset of the figure
	 */
	public GraphicalFigure (int id, int width, int height, String type, Location pos) {
		this.gfId = id;
		this.gfWidth = width;
		this.gfHeight = height;
		this.gfType = type;
		this.gfPos = pos;
		bst = new BinarySearchTree();
		
	}
	
	/**
	 * Getter method to get width
	 */
	public int getWidth() {
		return gfWidth;
	}
	
	/**
	 * Getter method to get height
	 */
	public int getHeight() {
		return gfHeight;
	}
	
	/**
	 * Getter method to get type
	 */
	public String getType() {
		return gfType;
	}
	
	/**
	 * Getter method to get ID
	 */
	public int getId() {
		return gfId;
	}
	
	/**
	 * Getter method to get offset
	 */
	public Location getOffset() {
		return gfPos;
	}
	
	/**
	 * Setter method to set offset value
	 */
	public void setOffset(Location value) {
		gfPos = value;
	}

	/**
	 * Setter method to set the type of the value
	 */
	public void setType(String t) {
		gfType = t;
	}
	
	/**
	 * Method addPixel adds pixel to the BST
	 * @param pix is the pixel to be added into the tree
	 */
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		bst.put(bst.getRoot(), pix);
	}

	/**
	 * Method intersects checks to see if the figure overlaps with any input graphical figure
	 * @param fig is the figure checked for overlap
	 * @return boolean true if there is overlap false if not
	 */
	public boolean intersects (GraphicalFigure fig) {
		
		// Check to see if there is any overlap between the 2 figures horizontally
		if (fig.getOffset().xCoord() > this.gfPos.xCoord() + this.getWidth() || this.gfPos.xCoord() > fig.getOffset().xCoord() + fig.getWidth()) {
			return false;
		}
		
		// Check to see if there is any overlap between the 2 figures vertically
		if (fig.getOffset().yCoord() > this.gfPos.yCoord() + this.getHeight() || this.gfPos.yCoord() > fig.getOffset().yCoord() + fig.getHeight()) {
			return false;
		}
		return true;
	}
}
