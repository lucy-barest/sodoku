/*
 * Lucy Barest
 * CS231A
 * Project 5
 * BoardTests.java
 */
public class BoardTests {
    
    public static void main(String[] args) {

        // test constructors 
        Board board = new Board(); 
        Board board1 = new Board("board1.txt"); 
        Board board2 = new Board(10); 

        System.out.println(board); 
        System.out.println(board1); 
        System.out.println(board2); 
        
        assert board!=null : "Error in Board::Board()";
        assert board1!=null : "Error in Board::Board()";
        assert board2!=null : "Error in Board::Board()";

        // test getCols
        System.out.println(board.getCols() + " == 9 "); 
        
        // test getRows
        System.out.println(board.getRows() + " == 9 "); 

        // valid solution 
        boolean x = board.validSolution(); 
        System.out.println(x); 

        // valid value 
        boolean y = board.validValue(0, 0, 0); 
        System.out.println(y); 



    }


}
