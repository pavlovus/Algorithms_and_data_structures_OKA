package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue_Vus<Item> implements Iterable<Item>, Queue_Vus<Item> {
	private Node first, last;
	private int count = 0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		count++;
		if (isEmpty())
			first = last;
		else
			oldLast.next = last;
	}

	public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		count--;
		if (isEmpty()) last = null;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}