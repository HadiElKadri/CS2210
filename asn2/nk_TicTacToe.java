/**
 * 
 * @author Hadi El-Kadri
 * class nk_TicTacToe
 *
 */
public class nk_TicTacToe {
	
	//INSTANCE VARIABLES
	private int board_size;
	private int inline;
	private int max_levels;
	private char[][] gameBoard;
	
	/**
	 * Constructor method initializes a n x n tic tac toe board
	 * @param board_size the desired size of the board (n)
	 * @param inline how many symbols in a row are needed to win
	 * @param max_levels the max level of game trees explored by the program (difficulty) 
	 */
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		this.gameBoard = new char[board_size][board_size];
		//A gameboard is initialized with nothing inside of it
		for(int i = 0; i < board_size; i ++) {
			for(int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' '; 
			}
	}
	}
	
	/**
	 * Method createDictionary creates a new dictionary with a specified size
	 * @return the new dictionary
	 */
	public Dictionary createDictionary() {
		Dictionary gameDict = new Dictionary(7001);
		return gameDict;
	}
	
	/**
	 * Method insertConfig inserts a configuration into the board and its score
	 * @param configurations a dictionary that is filled with configurations and their scores
	 * @param score the score of the configuration
	 */
	public void insertConfig(Dictionary configurations, int score) {
		String config = " ";
		for(int i = 0; i < board_size; i ++) {
			for(int j = 0; j < board_size; j++) {
				config = config + gameBoard[i][j];
	}	
}
		Record newRecord = new Record(config, score);
		configurations.insert(newRecord);
	}
	
	/**
	 * Method storePlay stores a symbol in the game board
	 * @param row the row the symbol is being stored in
	 * @param col the column the symbol is being stored in
	 * @param symbol the symbol that is being stored in the spot
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * Method squareIsEmpty returns true if the spot on the game board is empty ' ' 
	 * @param row the row being checked
	 * @param col the column being checked
	 * @return true if the coordinate is empty false if not
	 */
	public boolean squareIsEmpty (int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Method repeatedConfig takes the configuration from the game board and the score of it is returned
	 * @param configurations the dictionary of configurations
	 * @return the score of the configuration once found in the dictionary
	 */
	public int repeatedConfig(Dictionary configurations) {
		String config = " ";
		for(int i = 0; i < board_size; i ++) {
			for(int j = 0; j < board_size; j++) {
				config = config + gameBoard[i][j];
				}
			}
		return configurations.get(config);
	}

	/**
	 * Method wins checks to see if the input symbol has won on the board
	 * @param symbol the symbol checked to see if it won
	 * @return true if the symbol won, false if not
	 */
	
	public boolean wins(char symbol) {
		//Vertical check
        for (int i = 0; i < board_size; i++) {
        	int counter = 0;
            for (int j = 0; j < board_size; j++) {
            	if (gameBoard[j][i] == symbol) {
            		counter ++;
            	}
            	else {
            		counter = 0;
            	}
            	if (counter >= inline) return true;
            }
        }
		
		//Horizontal check
        for (int i = 0; i < board_size; i++) {
        	int counter = 0;
            for (int j = 0; j < board_size; j++) {
            	if (gameBoard[i][j] == symbol) {
            		counter ++;
            	}
            	else {
            		counter = 0;
            	}
            	if (counter >= inline) return true;
            }
        }
        return false;
	}
	
        
	/**
	 * Method isDraw checks to see if nobody won to declare a draw
	 * @return true if the board is full and no one won, if not return false
	 */
	public boolean isDraw() {
		
		 if(wins('O')==true || wins('X')==true) {
			 return false;
		 }
		 else if (wins('O')==false && wins('X')==false) {
		 
			 for (int i = 0; i < board_size; i++) {
				 for (int j = 0; j <board_size; j++) {
					 if (gameBoard[i][j]!='X' && gameBoard[i][j]!='O')
						 return false;
			  }
		    }
		 }
		 return true;
	 }
	 
	/**
	 * Returns scores based on what has occurred on the board
	 * @return 0 if the human won, 3 if the computer won, 2 if it was a draw, 1 if the 
	 * game is undecided and still going
	 */
	 public int evalBoard() {
		 if (wins('X') == true) {
			 return 0;
		 }
		 else if (wins('O') == true) {
			 return 3;
		 }
		 else if (isDraw() == true) {
			 return 2;
		 }
		 else {
			 return 1;
		 }
	 }

}

