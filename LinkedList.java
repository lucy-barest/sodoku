/*
 * Lucy Barest
 * CS231A
 * Lab 5/Project 5
 * LinkedList.java
 */
 

import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
//import java.util.Collections;

public class LinkedList<T> implements Iterable<T>, Queue<T>, Stack<T> {

	private static class Node<T> {

		T data;
		Node<T> next;
		Node<T> prev;


		public Node(T data) {
			this(data, null, null);
			this.prev = null;
		}
		
		public Node(T data, Node<T> next, Node<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		public T getData() {
			return data;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> getNext() {
			return next;
		}

		public Node<T> getPrev(){
			return prev;
		}

		public void setPrev(Node<T> prev){
			this.prev = prev;
		}
	}

	/**
	 * This is the first node in the list.
	 */
	private Node<T> head;
	/**
	 * This is the last node in the list.
	 */
	private Node<T> tail;
	/**
	 * This is the number of entries in the list.
	 */
	private int size;

	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

    // returns size 
    public int size() {
        return this.size; 
    }

    // returns true if list is empty otherwise false 
    public boolean isEmpty() {
        if (size == 0) {
            return true; 
        } else {
            return false; 
        }
    }

    // empties the list 
    public void clear() {
        head = null;
        size = 0; 
    }

    // insert the item to the front of the list 
    public void add(T item) {
        Node<T> newNode = new Node<T>(item); 
        newNode.setNext(head); 
        head = newNode; 
        size ++; 
    }

    // removes the first item of the list and returns it 
    public T remove() {
        if (head == null) {
            return null; 
        }
        T toReturn = head.getData(); 
        head = head.getNext(); 
        size --; 
        return toReturn; 
    }

    // returns the specified by the given index 
    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of Bounds"); 
            return null; 
        }

        if (index == 0) {
            return head.getData(); 
        }

        Node<T> walker = head; 
        for (int i = 0; i < index; i++) {
            walker = walker.getNext(); 
        }

        return walker.getData(); 
    }

    // insert the item at the specified position in the list 
    public void add(int index, T item) {
        if (index == 0) {
            add(item); 
            return; 
        }

        Node<T> walker = head; 
        for (int i = 0; i < index - 1; i++) {
            walker = walker.getNext(); 
        }

        Node<T> newNode = new Node<T>(item); 
        newNode.setNext(walker.getNext()); 
        walker.setNext(newNode); 
        size ++; 
    }

    // removes item from specified list 
    public T remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds"); 
            return null; 
        }

        if (index == 0) {
            return remove(); 
        }

        Node<T> walker = head;    
        for (int i = 0; i < index - 1; i++) {
            walker = walker.getNext(); 
        }
        T toReturn = walker.getNext().getData();   
        walker.setNext(walker.getNext().getNext()); 
        size --; 
        return toReturn; 
    }

    // return true if o is present in list 
    public boolean contains(Object o) {
        Node<T> walker = head; 
        // System.out.println("Object: " + o); 
        for (int i = 0; i < size; i++) {
            if (walker.getData().equals(o)) {
                return true; 
            } 
            walker = walker.getNext(); 
        }
        return false; 
    }

    // returns true if o is in linkedlist and contains same items in same order
    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)){
            return false;
        }
        // If I have reached this line, o must be a LinkedList
        @SuppressWarnings ("unchecked")     //BIG BIG BIG THANK YOU TO LORA LAROCHELLE FOR GETTING THIS LINE FROM PROF.BENDER IN OFFICE HOURS
        LinkedList<T> oAsALinkedList = (LinkedList<T>) o;
        // Now I have a reference to something Java knows is a LinkedList!

        if (oAsALinkedList.size() != this.size) {
            return false; 
        }

        for (int i = 0; i < size; i++) {
            Object x = oAsALinkedList.get(i);  
            if (!x.equals(get(i))) {
                return false; 
            }
        }
        return true; 
    }

    // implementing the interable interface 
    private class LLIterator implements Iterator<T> {

        private Node<T> nextNode; 

        public LLIterator(Node<T> head) {
            this.nextNode = head; 
        }

        public boolean hasNext() {
            return this.nextNode != null; 
        }

        public T next() {
            T result = nextNode.getData(); 
            nextNode = nextNode.getNext(); 
            return result;  
        }

        public void remove() {
        }
    }

    // Return a new LLIterator pointing to the head of the list
    public Iterator<T> iterator() {
        return new LLIterator(this.head);
    }

    // converts linkedlist to arraylist 
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<T>(size()); 
        Node<T> node = this.head; 
        for (int i = 0; i < size(); i++) {
            arrayList.add(node.data); 
            node = node.next; 
        }
        return arrayList; 
    }

	/**
	 * This method adds the given {@code data} to the start of the list.
	 * 
	 * @param data the data to be added into the list.
	 */
	public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data);
        if (size == 0) {
			// the new node is the only thing in the list: it is both the head and the tail
			head = newNode;
			tail = newNode;
			// it is the only item in the list, therefore there is no previous value
			newNode.setPrev(null);
		// else, there is at least one item in the list, and the head value is reset
		} else {
			// sets the node's next value to be the head
			newNode.setNext(head);
			// sets the previous of the old head to be the new node
			head.setPrev(newNode);
        	// sets the head value to be the new node
        	head = newNode;
    	}
		// increments the size
		size++;
	}

	/**
	 * This method adds the given {@code data} to the end of the list.
	 * 
	 * @param data the data to be added into the list.
	 */
	public void addLast(T data) {
        // if size is 0, addLast is the same as addFirst
		if (size == 0) {
			addFirst(data);
		} else {
			// creates a new node with the given data
			Node<T> newNode = new Node<T>(data);
			// sets the next of the tail (the last node in the list) to be the new node
			tail.setNext(newNode);
			// sets the previous field of the new node (which is now at the end of the list) to be the old tail
			newNode.setPrev(tail);
			// sets the tail to be the new node, which is now last in the list
			tail = newNode;
			// increments the size
			size++;
		}
    
    }
    
	/**
	 * This method returns and removes the first entry of the list.
	 * 
	 * @return the last entry of the list.
	 */
    public T removeFirst() {
        // if size equals 0, there's nothing to remove or return: return null
		if (size == 0) {
			return null;
		}
		// sets a variable to hold the value of the first node
        T removedHead = head.getData();
        // sets a variable newHead to equal the old node after the head
        Node<T> newHead = head.getNext();
        // sets the head value to be the newHead variables
        head = newHead;
		// sets the previous field of the head node to be null
		if (size > 1) {
            head.setPrev(null);
        } else {
            tail = null;
        }
        // decrements the size
        size--;
        // returns the value of the removed element
        return removedHead;
    }  
    
	/**
	 * This method returns and removes the last entry of the list.
	 * 
	 * @return the last entry of the list.
	 */
    public T removeLast() {
        // if size equals 0, there's nothing to remove or return: return null
		if (size == 0) {
			return null;
		}
		// set a variable equal the current tail to return
		T removedTail = tail.getData();
		// set the value of tail data to be null
		tail.data = null;
		// sets the tail to be the value before the tail
		tail = tail.getPrev();

        if (size == 1){
            head = null;
        } else {
            tail.setNext(null);
        }
		// decrement size
		size--;
		// return the value of the previous last element (the old tail)
		return removedTail;
    }    

    @Override
    public void offer(T item) { //Queue<T>
        addLast(item);
    }

    public void push(T item) { //Stack<T>
        addFirst(item);
    }

    public T pop() { //Stack<T>
        return removeFirst();
    }
    
    @Override
    public T poll() { //Queue<T>
        return removeFirst();
    }

    @Override
    public T peek() { 
        return head.getData();
    }

    public String toString() {
        String string = "["; 
        for (int i = 0; i < size; i++) {
            string += get(i).toString() + " "; 
        }
        string += " ]"; 
        return string; 
    }
}
