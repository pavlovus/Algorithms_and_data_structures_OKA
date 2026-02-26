package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack_Vus<Item> implements Iterable<Item>, Stack_Vus<Item> {
	private Node first = null;
	private int count = 0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void push(Item item) {
        if(item == null) throw new NullPointerException();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		count++;
	}

	public Item pop() {
        if(isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		count--;
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
