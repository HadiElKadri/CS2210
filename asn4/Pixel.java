/**
 * @author Hadi El-Kadri
 * Public class Pixel is a data item to be stored in a binary search tree
 */

public class Pixel {
	private Location p;
	private int color;
	
	/**
	 * Pixel object constructor composed of pixels location and color
	 * @param p location object of pixel
	 * @param color color of pixel
	 */
	public Pixel(Location p, int color) {
		this.p = p;
		this.color = color;
	}
	
	
	/**
	 * Get location of the pixel
	 * @return location coordinates
	 */
	public Location getLocation() {
		return p;
	}
	
	/**
	 * Get color of the pixel
	 * @return color of pixel
	 */
	public int getColor() {
		return color;
	}
}
