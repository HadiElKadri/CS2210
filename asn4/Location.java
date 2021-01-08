/**
 * @author Hadi El-Kadri 
 * Public class Location represents the (x,y) coordinates of a pixel
 */

public class Location {
	private int x; 
	private int y;
	
	/**
	 * Constructs a location object of a pixel
	 * @param x x coordinate of a pixel
	 * @param y y coordinate of a pixel
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x coordinate of the object
	 * @return x
	 */
	public int xCoord() {
		return x;
	}
	
	/**
	 * Get the y coordinate of the object
	 * @return y
	 */
	public int yCoord() {
		return y;
	}
	
	/**
	 * Method compareTo uses column order to determine if this location is < or > a given location p
	 * @param p another location object of a pixel
	 * @return -1, 0, or 1
	 */
	public int compareTo (Location p) {
		
		if(this.x == p.xCoord()) {
			if(this.y < p.yCoord()) return -1;
			else if (this.y == p.yCoord()) return 0;
			else return 1;
		}
		
		else if(this.x < p.xCoord()) return -1;
		
		else return 1;
	}
}
