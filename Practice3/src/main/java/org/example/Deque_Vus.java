package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque_Vus<Item> implements Iterable<Item> {
    private Node first, last;
    private int count = 0;

    private class Node{
        Item item;
        Node next;
        Node previous;
    }

    public void addFirst(Item item) {
        if(item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty())
            last = first;
        else
            oldFirst.previous = first;
        count++;
    }

    public void addLast(Item item) {
        if(item == null) throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        count++;
    }

    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        count--;
        if (isEmpty())
            last = null;
        else
            first.previous = null;
        return item;
    }

    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        count--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return item;
    }

    public boolean isEmpty() { return count == 0; }

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
