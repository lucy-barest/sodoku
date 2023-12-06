/*
 * Lucy Barest
 * CS231A
 * Project 5
 * Sudoku.java
 */
public class Sudoku {
    Board board; 
    int unscpecifiedCellNumber; 
    LandscapeDisplay ld; 
    
    // sudoku constructor creating new board with some random values 
    public Sudoku() {
        board = new Board(); 
        unscpecifiedCellNumber = Board.Size * Board.Size; 
        ld = new LandscapeDisplay(board); 
    }

    // sudoku constructor that gives random number of set values 
    public Sudoku(int numLocked) {
        board = new Board(numLocked); 
        unscpecifiedCellNumber = Board.Size * Board.Size - numLocked; 
        ld = new LandscapeDisplay(board);
    }

    // sudoku constructor that reads filename 
    public Sudoku(String filename) {
        board = new Board();
        board.read(filename);
        int numLocked = 0;
        for(int i=0; i<Board.Size; i++) {
            for (int j=0; j<Board.Size; j++) {
                if (board.get(i, j).getValue() > 0) {
                    numLocked++;
                }
            }
        }
        unscpecifiedCellNumber = Board.Size * Board.Size - numLocked;
        ld = new LandscapeDisplay(board);

    }

    // sovle method that allows for backtracking when stuck 
    public boolean solve(int delay) throws InterruptedException{
        CellStack stack = new CellStack(); 

        while (stack.size() < unscpecifiedCellNumber) {
            if (delay > 0) {
                Thread.sleep(delay);
            }
            if (ld != null) {
                ld.repaint();
            }
            
            Cell bestCell = board.findNextCell(); 

            if (bestCell != null) {
                stack.push(bestCell); 
            } else {
                while (stack.size() > 0) {
                    Cell popCell = stack.pop(); 
                    if (popCell.getValue() < 9) {
                        int untested = popCell.getValue() + 1; 
                        boolean newValidFound = false; 
                        for (int i = untested; i < 10; i++) {
                            if (board.validValue(popCell.getRow(), popCell.getCol(), i)) {
                                popCell.setValue(i);
                                stack.push(popCell); 
                                newValidFound = true; 
                                break; 
                            } 
                        }
                        if (newValidFound) {
                            break; 
                        } else {
                            popCell.setValue(0);
                        }
                    } else {
                        popCell.setValue(0);
                    }
                }
                if (stack.size() == 0) {
                    board.setFinished(true);
                    return false; 
                }
            }

        }
        board.setFinished(true);
        return true; 

    }

    // to string for Sudoku
    public String toString() { 
		return board.toString();
	}

    // main 
    public static void main(String[] args) {
        Sudoku sudoku = null; 

        if (args.length == 0) {
            sudoku = new Sudoku(); 
        } else {
            String argument = args[0]; 
            try {
                int x = Integer.parseInt(argument); 
                sudoku = new Sudoku(x); 
            } catch (NumberFormatException e) {
                sudoku = new Sudoku(argument); 
            }
        }
        
        // Sudoku sudoku = new Sudoku(0); 
        System.out.println(sudoku);  
        try {
            long startTime = System.currentTimeMillis();
            boolean solved = sudoku.solve(0); 
            long endTime = System.currentTimeMillis();

            System.out.println(solved); 
            System.out.println(sudoku);

            long timeElapsed = endTime - startTime;
            System.out.println("Execution time in milliseconds: " + timeElapsed);

            

        } catch (InterruptedException e) {
        }

    }
}
