/*
 * Lucy Barest
 * CS231A
 * Project 5
 * SudokuTests.java
 */
public class SudokuTests {
    public static void main(String[] args) throws InterruptedException {
        
        // test constructors 
        Sudoku sudoku = new Sudoku(); 
        Sudoku sudoku1 = new Sudoku(10); 
        Sudoku sudoku2 = new Sudoku("board1.txt"); 
        
        System.out.println(sudoku); 
        System.out.println(sudoku1); 
        System.out.println(sudoku2); 
        
        assert sudoku!=null : "Error in Sudoku::Sudoku()";
        assert sudoku1!=null : "Error in Sudoku::Sudoku()";
        assert sudoku2!=null : "Error in Sudoku::Sudoku()";


        boolean solved = sudoku.solve(0); 
        System.out.println(solved); 

    }
}
