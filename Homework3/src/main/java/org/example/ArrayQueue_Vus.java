package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue_Vus<Item> implements Iterable<Item>, Queue_Vus<Item> {
    private Item q[];
    private int first = 0;
    private int last = 0;
    private int n = 0;

    public ArrayQueue_Vus() {
        q = (Item[])new Object[1];
    }

    public ArrayQueue_Vus(int capacity){
        q = (Item[])new Object[capacity];
    }

    @Override
    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
        if (n == q.length) resize(2 * q.length);
        q[last] = item;
        last = (last  + 1) % q.length;
        n++;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = q[first];
        q[first] = null;
        first = (first + 1) % q.length;
        n--;
        if (n > 0 && n == q.length/4) resize(q.length/2);
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

    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[first];
            first = (first + 1) % q.length;
        }
        q = copy;
        first = 0;
        last = n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>{
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            i++;
            return q[(first + 1) % q.length];
        }
    }
}
