/**
 * 
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The linked list for our hash will only implement the
 * methods in the HashListI interface, a reduced set of
 * methods compared to the linked list from Assignment 1.
 * 
 * @author
 *
 */
public class LinkedList<E> implements HashListI<E> {

    @Override
    public void add(E obj) {
        addFirst(obj);
    }

    @Override
    public E remove(E obj) {
        Node<E> current = head, previous = null;
        while(current != null){
            if(((Comparable<E>)obj).compareTo(current.data)==0){
                if(current==head)
                    return removeFirst();
                if(current==tail)
                    return removeLast();
                currentSize--;
                previous.next = current.next;
                return current.data;
            }
        }
        return null;
    }

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

    public void addFirst(E obj) {
        Node<E> node = new Node<E>(obj);
        if(isEmpty())
            tail = node;
        node.next = head;
        head = node;
        currentSize++;
    }

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
    public void makeEmpty() {
        head = tail = null;
        currentSize = 0;

    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

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
