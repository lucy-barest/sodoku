/*
 * Lucy Barest
 * CS231A
 * Project 5
 * Cell.java
 */
import java.awt.Graphics;
import java.awt.Color;

// Cell Class
public class Cell {

    int row; 
    int col; 
    int val; 
    boolean locked; 

    // Constructor for the cell class intializing row, col, and val to 0 and locked to false 
    public Cell() {
        row = 0; 
        col = 0; 
        val = 0; 
        locked = false; 
    }

    // intialize row, col, and val to parameter values, and locked to false 
    public Cell(int row, int col, int value) {
        this.row = row; 
        this.col = col; 
        this.val = value; 
        locked = false; 
    }

    // intialize row, col, and val to parameters 
    public Cell(int row, int col, int value, boolean locked) {
        this.row = row; 
        this.col = col; 
        this.val = value; 
        this.locked = locked; 
    }

    // return Cell's row index
    public int getRow() {
        return row; 
    }

    // return Cell's column index 
    public int getCol() {
        return col; 
    }

    // return Cell's value 
    public int getValue() {
        return val; 
    }

    // set Cell's value 
    public void setValue(int newval) {
        val = newval; 
    }

    // return the value of the locked field 
    public boolean isLocked() {
        return locked; 
    }

    // set Cell's locked field to the new value 
    public void setLocked(boolean lock) {
        this.locked = lock; 
    }

    // toString function for Cell 
    public String toString() {
        return "" + row + ", " + col + ", " + val; 
    }

    // draw method for Cell Class 
    public void draw(Graphics g, int x, int y, int scale){
        char toDraw = (char) ((int) '0' + getValue());
        g.setColor(isLocked()? Color.BLUE : Color.RED);
        g.drawChars(new char[] {toDraw}, 0, 1, x, y);
    }


    //CELL TESTS
    public static void main(String[] args) {
        // case 1: Constructor
        {
            // setup
            Cell c1 = new Cell();
            Cell c2 = new Cell(1, 2, 3);
            Cell c3 = new Cell(1, 2, 3, true);

            // verify
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);

            // test
            assert c1!=null : "Error in Cell::Cell()";
            assert c2!=null : "Error in Cell::Cell()";
            assert c3!=null : "Error in Cell::Cell()";
        }

        // case 2: value
        {
            // setup
            Cell c1 = new Cell();
            Cell c2 = new Cell(1, 2, 3);
            Cell c3 = new Cell(1, 2, 3, true);

            // verify
            System.out.println(c1.getValue() + " == 0");
            System.out.println(c2.getValue() + " == 3");
            System.out.println(c3.getValue() + " == 3");

            // test
            assert c1.getValue() == 0 : "Error in Cell::Cell()";
            assert c2.getValue() == 3 : "Error in Cell::Cell()";
            assert c3.getValue() == 3 : "Error in Cell::Cell()";
        }

        // case 3: locked
        {
            // setup
            Cell c1 = new Cell();
            Cell c2 = new Cell(1, 2, 3);
            Cell c3 = new Cell(1, 2, 3, true);

            // verify
            System.out.println(c1.isLocked() + " == false");
            System.out.println(c2.isLocked() + " == false");
            System.out.println(c3.isLocked() + " == true");

            // test
            assert c1.isLocked() == false : "Error in Cell::Cell()";
            assert c2.isLocked() == false : "Error in Cell::Cell()";
            assert c3.isLocked() == true : "Error in Cell::Cell()";
        }
    }

}