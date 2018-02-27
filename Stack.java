package data_structures;

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {
	private LinkedList<E> llist;

	public Stack() {
		llist = new LinkedList<>();
	}                      
	public void push(E obj) {
		llist.addFirst(obj);
	}
	public E pop() {
		return llist.removeFirst();
	}        
	public int size() {
		return llist.size();
	}
	public boolean isEmpty() {
		return llist.isEmpty();
	}
	public boolean isFull() {
		return false;
	}
	public E peek() {
		return llist.peekFirst();
	}
	public boolean contains(E obj) {
		return llist.contains(obj);
	}   
	public void makeEmpty() {
		llist.makeEmpty();
	}
	public Iterator<E> iterator() {
		return null;
	}
}    