package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {
    private Item q[];
    private int first = 0;
    private int last = 0;
    private int n = 0;

    public ArrayQueue() {
        q = (Item[])new Object[1];
    }

    public ArrayQueue(int capacity){
        q = (Item[])new Object[capacity];
    }

    //O(1) у звичайному випадку (просто додавання елемента), O(n) лише у момент розширення масиву (але це трапляється рідко), тому амортизовано — O(1).
    public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
        if (n == q.length) resize(2 * q.length);
        q[last] = item;
        last = (last  + 1) % q.length;
        n++;
    }

    // Так само як enqueue амортизована константна складність(бо є випадки, коли масив звужується)
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = q[first];
        q[first] = null;
        first = (first + 1) % q.length;
        n--;
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return item;
    }

    // Констатний час, бо просто доступ до елемента масиву за індексом
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[first];
    }

    // O(1), пряме посилання на n
    public boolean isEmpty() {
        return n == 0;
    }

    // O(1), пряме посилання на n
    public int size() {
        return n;
    }

    // Змінює розмір внутрішнього масиву q до нового capacity, складність - O(n), бо копіюємо з одного масиву в інший
    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = q[(first + i) % q.length];

        q = copy;
        first = 0;
        last = n;
    }

    // Кожна операція next() — O(1).
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
            Item item = q[(first + i) % q.length];
            i++;
            return item;
        }
    }
}