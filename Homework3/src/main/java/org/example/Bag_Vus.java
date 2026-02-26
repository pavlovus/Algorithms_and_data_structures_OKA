package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag_Vus<Item> implements Iterable<Item> {
    private Item[] s;
    private int n = 0;

    public Bag_Vus(){
        s = (Item[])new Object[1];
    }

    public Bag_Vus(int capacity){
        s = (Item[])new Object[capacity];
    }

    public void add(Item item) {
        if(item == null) throw new NullPointerException();
        if (n == s.length) resize(2 * s.length);
        s[n++] = item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity){
        if (capacity < 1) capacity = 1;
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = s[i];
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<Item>{
        private int i = 0;
        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[i++];
        }
    }
}
