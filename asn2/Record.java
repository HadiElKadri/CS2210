/**AUTHOR: HADI EL-KADRI
 * 
 * Class Record initializes a record object storing a string of tic-tac-toe configuration
 * and its respective score
 * 
 */
public class Record {
	
	//INSTANCE VARIABLES
	private String config;
	private int score;
	
	//RECORD CONSTRUCTOR
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	/**
	 * Getter method to get the configuration in the record
	 * @return the configuration
	 */
	public String getConfig() {
		return config;
	}
	
	/**
	 * Getter method to get the score in the record
	 * @return the score
	 */
	
	public int getScore() {
		return score;
	}
}
