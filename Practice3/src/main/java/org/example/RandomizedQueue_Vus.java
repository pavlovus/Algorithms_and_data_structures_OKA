package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue_Vus<Item> implements Iterable<Item> {
    private Item q[];
    private int n = 0;

    public RandomizedQueue_Vus() {
        q = (Item[])new Object[1];
    }

    public RandomizedQueue_Vus(int capacity){
        q = (Item[])new Object[capacity];
    }

    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
        if (n == q.length) resize(2 * q.length);
        q[n] = item;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int rand = StdRandom.uniform(n);
        Item item = q[rand];
        q[rand] = q[n-1];
        n--;
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[StdRandom.uniform(n)];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private int current = 0;
        private int[] rand;

        public RandomIterator() {
            rand = new int[n];
            for(int i = 0; i < n; i++) rand[i] = i;
            StdRandom.shuffle(rand);
        }

        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return q[rand[current++]];
        }
    }
}
