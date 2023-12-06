/*
 * Lucy Barest
 * CS231A
 * Project 5
 * Board.java
 */

import java.io.*;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

// Board class 
public class Board {

    private Cell[][] board; 
    public static final int Size = 9; 
    private boolean finished; 

    // board constructor that creates board 
    public Board() {
        board = new Cell[Board.Size][Board.Size]; 
        for (int i = 0; i < Board.Size; i++) {
            for (int j = 0; j < Board.Size; j++) {
                board[i][j] = new Cell(i, j, 0); 
            }
        }
    }
    
    // board constructor that reads file 
    public Board (String filename) {
        this(); 
        read(filename); 
    }

    // board constructor that creates new Board with random numbers 
    public Board (int numLocked) {
        this(); 

        int count = 0; 
        Random rand = new Random();
        while (count < numLocked){
            int randomRow, randomCol, randomVal;
            randomRow = rand.nextInt(9);
            randomCol = rand.nextInt(9);
            randomVal = rand.nextInt(9) + 1;

            if (validValue(randomRow, randomCol, randomVal) && !board[randomRow][randomCol].locked){
                set(randomRow, randomCol, randomVal, true);
                count++;
            }

        }
        // pick numLocked cells randomly
        // give each one a random value that is valid for it
    }

    // setter for Finished 
    public void setFinished(boolean finished) {
        this.finished = finished; 
    }

    // isFinished method returns true if finished 
    public boolean isFinished() {
        return finished; 
    }

    // toString method for Board 
    public String toString() {
        StringBuilder strBuilder = new StringBuilder(); 
		
		for (int i=0; i<Board.Size; i++) {
			for (int j=0; j<Board.Size; j++) {
				strBuilder.append(board[i][j].getValue());
				if (j == 2 || j == 5) {
					strBuilder.append(" ");
				}
			}
			strBuilder.append("\n");
			if (i == 2 || i == 5) {
				strBuilder.append("\n");
			}
		}
		return strBuilder.toString();
    }
    
    // return the number of columns 
    public int getCols() {
        return Board.Size; 
    }

    // returns the number of rows
    public int getRows() {
        return Board.Size; 
    }

    // returns the Cell at location r,c 
    public Cell get(int r, int c) {
        return board[r][c]; 
    }

    // returns whether the cell at r,c is locked 
    public boolean isLocked(int r, int c) {
        return get(r, c).isLocked(); 
    }

    // returns the number of locked Cells on the board 
    public int numLocked() {
        int numLocked = 0; 
        for (int i = 0; i < Board.Size; i++) {
            for (int j = 0; j < Board.Size; j++) {
                if (isLocked(i, j)) {
                    numLocked ++; 
                }
            }
        }
        return numLocked; 
    }

    // returns the value at Cell r,c 
    public int value(int r, int c) {
        return get(r, c).getValue(); 
    }
    
    // sets the value of the Cell at r,c 
    public void set(int r, int c, int value) {
        get(r, c).setValue(value); 
    }

    // sets the value and locked fields of the Cell at r, c
    public void set(int r, int c, int value, boolean locked) {
        get(r, c).setLocked(locked);
        set(r, c, value);
    }   

    // reads in a file for Board 
    public boolean read(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            int count = 0; 
            while(true){
                String line = br.readLine();
                if(line == null){
                    break;
                }
                String[] strArray = line.split("[ ]+");
                for (int i = 0; i < strArray.length; i++){
                    int value = Integer.parseInt(strArray[i]); 
                    if (value > 0) {
                        set(count, i, value, true);
                    } else {
                        set(count, i, value, false); 
                    }
                }
                count++;
            }
            br.close();
            return true; 
        }
        
        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
    
        return false;

    }

    // tests in the row, col and square if its a valid value or not 
    public boolean validValue(int row, int col, int value) {
        if (value < 0 && value > 9) {
			return false; 
		}
		// validate row 
		for (int j=0; j<Board.Size; j++) {
			if (get(row, j).getValue() == value && col != j) {
				return false; 
			} 
		}
		// validate col 
		for (int i=0; i<Board.Size; i++) {
			if (get(i, col).getValue() == value && row != i) {
				return false; 
			}
		}
		// validate square 
		int sRow = row/3; 
		int sCol = col/3; 
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				int r = sRow*3 + i; 
				int c = sCol*3 + j; 
				Cell cell = get(r,c);
				if (cell.getValue() == value && r != row && c != col) {
					return false; 
				}
			}
		}
		return true;
    }

    // returns true fi the board is solved
    public boolean validSolution() {
        for (int i=0; i<Board.Size; i++) {
			for (int j=0; j<Board.Size; j++) {
				Cell cell = get(i,j);
				if (!validValue(i,j,cell.getValue())) {
					return false; 
				}
			}
		}
		return true;
    }

    // finds next best cell on the board with the feste valid value options 
    public Cell findNextCell() {
        Cell bestCell = null;
        int numberOfValidValueOptons = Board.Size;
    
            for (int i=0; i<Board.Size; i++) {
                for (int j=0; j<Board.Size; j++) {
                    Cell cell = get(i,j);
                    if (cell.getValue() == 0) {
                        int number = numberOfValidValueOptions(cell);
                        if (number == 0) {
                            //found one without valid value, return null.
                            return null;
                        }
                        if ( number < numberOfValidValueOptons || bestCell == null) {
                            bestCell = cell;
                            numberOfValidValueOptons = number;
                        }
                    }
                }
            }
            //Set bestCell value to the first valid value
            if (bestCell != null) {
                 for (int k=1; k<10; k++) {
                    if (validValue (bestCell.getRow(), bestCell.getCol(), k)) {
                        bestCell.setValue(k);
                        break;
                    } 
                }           
            }
            return bestCell;
        }

        // method to see number of valid value options 
        private int numberOfValidValueOptions(Cell cell) {
            int numOptions = 0;
            for (int i = 1; i < 10; i++) {
                if (validValue(cell.getRow(), cell.getCol(), i)) {
                    numOptions++;
                }
            }
            return numOptions;
    
        }

        // draw method for Board
        public void draw(Graphics g, int scale){
            for(int i = 0; i<9; i++){
                for(int j = 0; j<9; j++){
                    board[i][j].draw(g, j*scale+5, i*scale+10, scale);
                }
            } if(finished){
                if(validSolution()){
                    g.setColor(new Color(0, 127, 0));
                    g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale*3+5, scale*10+10);
                } else {
                    g.setColor(new Color(127, 0, 0));
                    g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale*3+5, scale*10+10);
                }
            }
        }

    // main 
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Need one argument"); 
            return; 
        }

        Board board = new Board(10); 
        Board board1 = new Board(); 
        board1.read(args[0]); 
        boolean x = board.validSolution(); 
        System.out.println(x); 
        System.out.println(board.toString());
        
    }
}
