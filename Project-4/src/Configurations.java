/**
 * Configurations class implements all the methods needed by algorithm computerPlay .
 * 
 * @author Parth Hasmukh Kathiria
 * 
 */


public class Configurations {
	
	//Declaring the necessary instance variables.
	private char[][] board;
	private int lengthToWin;
	private int board_size;
	private int max_levels;
	
	//The constructor of the class which intializes the board_size, lengthToWin, max_levels and the board.
	public Configurations(int board_size, int lengthToWin, int max_levels) {
		this.board_size = board_size;
		this.lengthToWin = lengthToWin;
		this.max_levels = max_levels;
		this.board = new char[board_size][board_size];
		
		//Initializing the board with empty spaces using the for loop.
		for(int i=0; i<board_size; i++) {
			for(int j=0; j<board_size; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	//createDictionary method creates the dictionary of a specific size.
	public HashDictionary createDictionary() {
		return new HashDictionary(7919);
	}
	
	//private boardInAString method is responsible for converting the board into a string format which is used for further dictionary operations.
	private String boardInAString(){
		String boardString = "";
		for(int i=0; i<board_size; i++) {
			for(int j=0; j<board_size; j++) {
				boardString += board[i][j];
			}
		}
		return boardString;
	}
	
	//repeatedConfiguration method checks if the current board configuration string is already present in the hashTable or not.
	public int repeatedConfiguration(HashDictionary hashTable) {
		//Storing the board in a string using the boardInAString method.
		String boardToString = boardInAString();
		//returns the score associated with the given configuration.
		int score = hashTable.get(boardToString);
		//if the score is not present in the dictionary, -1 is returned, otherwise the score is returned.
		if(score == -1) {
			return -1;
		} else return score;	
	}
	
	//addConfiguration method adds the current board configuration to the hashTable with a specific score.
	public void addConfiguration(HashDictionary hashDictionary, int score) {
		String boardToString = boardInAString();
		//creating a new record object of Data class storing the configuration and score.
		Data record = new Data(boardToString,score);
		int data = hashDictionary.get(boardToString);
		//if data is not present in the dictionary, it is added to the dictionary using the .put method.
		if(data == -1) {
			hashDictionary.put(record);
		}
	}
	
	//savePlay method updates the game board with a player's move.
	public void savePlay(int row, int col, char symbol) {
		board[row][col] = symbol;
	}
	
	//squareIsEmpty method checks if a specific tile on the board is empty.
	public boolean squareIsEmpty(int row, int col) {
		return board[row][col] == ' ';
	}
	
	//wins method returns true if there is an X-shape or a +shape of length at least k formed by tiles of the kind symbol on the board, where k is the length of the X-shape or +shape needed to win the game.
	public boolean wins(char symbol) {
		//Iterating through the tiles of the board using a for loop.
        for (int i = 1; i < board_size - 1; i++) {
            for (int j = 1; j < board_size - 1; j++) {
            	//If the current tile contains the given symbol, checking for the cross shape using the isCross helper method and plus shape using the isPlus helper method.
                if (board[i][j] == symbol) {
                    if (isCross(i, j, symbol) ||  isPlus(i, j, symbol)) {
                    	//If there is a valid shape formed returning true.
                        return true;
                    }
                }
            }
        }
        //Otherwise returning false.
        return false;
    }
	
	//private isPlus method which helps in checking if a valid plus shape is formed using the given symbol.
	private boolean isPlus(int row, int col, char symbol) {
		//Initializing the count variable of integer data type to the value 1(center tile) initially.
        int count = 1; 
        //Iterating twice using the for loop to cover both directions.
        for (int i = -1; i <= 1; i += 2) {
        	//Initializing the row and column variables.
            int r = row + i;
            int c = col;
            //Checking vertically along the current direction
            while (r >= 0 && r < board_size && board[r][c] == symbol) {
                count++;
                //Moving to the next row in the current direction.
                r += i;
                
            }
            
            //Resetting the row back to original position.
            r = row;
            //Setting the initial column for the horizontal check in the current direction.
            c = col + i;
            //Checking horizontally along the current direction.
            while (c >= 0 && c < board_size && board[r][c] == symbol) {
            	//Incrementing the count as a valid symbol is encountered
                count++;
                //Moving to the next column in the current direction
                c += i;
            }
        }
        //Making sure that consecutive tiles with the same symbol do not return true.
        if(board[row+1][col] != symbol || board[row-1][col] != symbol || board[row][col+1] != symbol || board[row][col-1] != symbol) {
        	return false;
        }
        //Returning true if the plus shape is of the required lengthToWin.
        return count >= lengthToWin;
    }
	
	//private isCross which helps in checking if a valid cross shape is formed using the given symbol.
    private boolean isCross(int row, int col, char symbol) {
    	//Initializing the count variable of integer data type to the value 1(center tile) initially.
        int count = 1; 
        //Iterating twice to check for both horizontal and vertical directions.
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
            	//Setting both the initial row and column for the current diagonal direction.
                int r = row + i;
                int c = col + j;
                while (r >= 0 && r < board_size && c >= 0 && c < board_size && board[r][c] == symbol) {
                	//Incrementing the count as a valid symbol is encountered
                    count++;
                    //Moving to the next row in the current diagonal direction.
                    r += i;
                    //Moving to the next row in the current diagonal direction.
                    c += j;
                }
            }
        }
        //Making sure that diagonally adjacent cells are not part of the cross shape so they return false;
        if(board[row+1][col-1] != symbol || board[row+1][col+1] != symbol || board[row-1][col+1] != symbol || board[row-1][col-1] != symbol) {
        	return false;
        }
        //Returning true is the cross shape is of the required lengthToWin.
        return count >= lengthToWin;
    }
	
    //isDraw method is responsible for checking if the game has been a draw.
    public boolean isDraw() {
    	//If either the human(X) or the computer (O) wins, its not a draw and return false.
    	if(wins('X') || wins('O')) {
    		return false;
    	}
    	
    	//using nested for loops to check if there is an empty tile in the board.
    	for(int i=0; i<board_size; i++) {
    		for(int j=0; j<board_size; j++) {
    			//if there is an empty tile in the board, returning false.
    			if(board[i][j] == ' '){
    				return false;
    			}
    		}
    	}
    	//otherwise returning true.
    	return true;
    }
    
    //evalBoard method is responsible for evaluating the current state of the board.
    public int evalBoard() {
    	//If computer(O) wins, return 3.
    	if(wins('O')) {
    		return 3;
    	}
    	//else if human(X) wins, return 0.
    	else if(wins('X')) {
    		return 0;
    	}
    	//else if none of them wins and its a draw, return 2.
    	else if(isDraw()) {
    		return 2;
    	}
    	//else if the current state of the board is undecided/unpredictable, return 1.
    	else return 1;
    } 
}
