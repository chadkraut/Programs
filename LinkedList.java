package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements ListI<E> {

	class IteratorHelper implements Iterator<E>{
		Node<E> index;
		public IteratorHelper(){
			index = head;
		}

		public boolean hasNext(){
			return index != null;
		}

		public E next(){
			if(!hasNext())
				throw new NoSuchElementException();
			E val = index.data;
			index =index.next;
			return val;
		}

		public void remove(){
		throw new UnsupportedOperationException();
		}
	}

	class	Node<E> {
		E data;
		Node<E> next;
		public Node(E obj){
			data = obj;
			next = null;
		}
	}

	private Node<E> head, tail;
	private int currentSize;

	public	LinkedList(){
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public void addFirst(E obj) {
		Node<E> node = new Node<E>(obj);
        if(isEmpty())
            tail = node;
		node.next = head;
		head = node;
		currentSize++;
	}

	@Override
	public void addLast(E obj) {
		Node<E> node = new Node<E>(obj);
		if(head==null){
			head = tail = node;
			currentSize++;
			return;
		}
		tail.next = node;
		tail = node;
		currentSize++;
	}

	@Override
	public E removeFirst() {
		if(head==null){
			return null;
		}
		E tmp = head.data;
		if(head==tail)
			head = tail = null;
		else
			head = head.next;
		currentSize--;

		return tmp;
	}

	@Override
	public E removeLast() {
		if(head==null)
			return null;
		if(head==tail)
			return removeFirst();
		Node<E> current = head;
		Node<E> previous = null;
		while(current != tail){
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;
		currentSize--;
		return current.data;
	}

	@Override
	public E peekFirst() {
		if(head==null)
			return null;
		return head.data;
	}

	@Override
	public E peekLast() {
		if(tail==null)
			return null;
		return tail.data;
	}

	@Override
	public void makeEmpty() {
		head = tail = null;
		currentSize = 0;

	}

	@Override
	public boolean isEmpty() {
		return head==null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean contains(E obj) {
		Node<E> current = head;
		while(current!= null){
			if(((Comparable<E>) obj).compareTo(current.data)==0)
				return true;
			current = current.next;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

}
