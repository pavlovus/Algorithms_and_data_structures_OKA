package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack_Vus<Item> implements Iterable<Item> , Stack_Vus<Item> {
	private Item[] s;
	private int n = 0;
	
	public ArrayStack_Vus(){
		s = (Item[])new Object[1];
	}

    public ArrayStack_Vus(int capacity){
        s = (Item[])new Object[capacity];
    }

    @Override
	public void push(Item item) {
        if(item == null) throw new NullPointerException();
		if (n == s.length) resize(2 * s.length);
		s[n++] = item;
	}

    @Override
	public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length/4) resize(s.length/2);
        return item;
	}

    @Override
	public boolean isEmpty() {
		return n == 0;
	}

    @Override
	public int size() {
		return n;
	}

	private void resize(int capacity){
		Item[] copy = (Item[])new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = s[i];
		s = copy;
	}

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = n;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[--i];
        }
    }
}