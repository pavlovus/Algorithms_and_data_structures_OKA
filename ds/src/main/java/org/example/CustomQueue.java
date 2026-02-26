package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomQueue implements Iterable<Integer> {
    private Node first, last;
    private int count = 0;

    private class Node{
        int item;
        Node next;
    }

    public void enqueue(int item) {
        if(item > 0){
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
    }

    public int dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
        int item = first.item;
        first = first.next;
        count--;
        if (isEmpty()) last = null;
        if(item % 3 !=0){
            return item;
        }
        return first.item;
    }

    public boolean isEmpty() { return first == null; }

    public int size() { return count; }

    public int peek() {
        if(isEmpty()) throw new NoSuchElementException();
        int item = first.item;
        if(item % 3 !=0){
            return item;
        }
        return first.next.item;
    }

    @Override
    public Iterator<Integer> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Integer> {
        private Node current = first;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int item = current.item;
            current = current.next;
            return item;
        }
    }
}