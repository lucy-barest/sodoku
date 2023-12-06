/*
 * Lucy Barest
 * CS231A
 * Project 5
 * CellStack.java
 */
public class CellStack {
    
    // Node based stack 
    private class Node {
        Cell cell; 
        Node next; 

        // constructor 
        public Node(Cell cell) {
            next = null; 
            this.cell = cell; 
        }

        // getter method 
        public Cell getCell() {
            return cell; 
        }

        // setter for node 
        public void setCell(Node n) {
            this.next = n; 
        
        }

        // get next cell 
        public Node getNextCell() {
            return this.next; 
        }

    }

    Node head;  // the top of my stack 
    int size;  // the number of items in my stack 
    // constructor intialize the stack's fields 
    public CellStack() {
        head = null; 
        size = 0; 
    }

    // pusch c onto the stack 
    public void push(Cell c) {
        // should look very similar to an add method for linked list 
        Node newNode = new Node(c); 
        newNode.setCell(head);
        head = newNode; 
        size ++; 
    }

    // return the top Cell on the stack 
    public Cell peek() {
        if (size == 0) {
            return null; 
        }
        return head.getCell(); 
    }

    // remove and return the top element from the stack; return null if the stack is empty 
    public Cell pop() {
        if (size == 0) {
            return null; 
        } else {
            Cell toReturn = head.getCell(); 
            head = head.getNextCell(); 
            size --; 
            return toReturn; 
        }
    }

    // return the number of elemetns in the stack 
    public int size() {
        return size; 
    }

    // return true if the stack is empty 
    public boolean empty() {
        if (size == 0) {
            return true; 
        } else {
            return false; 
        }
    }

    // main 
    public static void main (String[] args) {
        CellStack cellStack = new CellStack(); 
        System.out.println(cellStack.peek()); 
    }
    
}