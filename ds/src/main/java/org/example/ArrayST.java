package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayST<Key extends Comparable<Key>, Value> {
    private int N;
    private Node[] st;

    private class Node {
        Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public ArrayST() {
        st = (Node[]) new Object[2];
        N = 0;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException();
        for (int i = 0; i < N; i++) {
            if (st[i].key.equals(key)) {
                st[i].value = val;
                return;
            }
        }
        if (N == st.length) resize(2 * st.length);
        st[N++] = new Node(key, val);
    }

    // Частіше запитувані елементи переміщуються ближче до початку
    public Value get(Key key) {
        if (isEmpty()) return null;
        for (int i = 0; i < N; i++) {
            if (st[i].key.equals(key)) {
                if (i > 0) {
                    Node temp = st[i];
                    st[i] = st[i - 1];
                    st[i - 1] = temp;
                    return temp.value;
                }
                return st[i].value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (isEmpty()) throw new NoSuchElementException();
        for (int i = 0; i < N; i++) {
            if (st[i].key.equals(key)) {
                st[i] = st[N - 1];
                st[N - 1] = null;
                N--;
                if (N > 0 && N == st.length / 4) resize(st.length / 2);
                return;
            }
        }
    }

    public boolean contains(Key key) {return get(key) != null;}

    public boolean isEmpty() {return N == 0;}

    public int size() {return N;}

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        Key min = st[0].key;
        for (int i = 1; i < N; i++)
            if (st[i].key.compareTo(min) < 0) min = st[i].key;
        return min;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        Key max = st[0].key;
        for (int i = 1; i < N; i++)
            if (st[i].key.compareTo(max) > 0) max = st[i].key;
        return max;
    }

    public Key floor(Key key) {
        if (isEmpty()) throw new NoSuchElementException();
        Key floor = null;

        for (int i = 0; i < N; i++)
            if (st[i].key.compareTo(key) <= 0)
                if (floor == null || st[i].key.compareTo(floor) > 0) floor = st[i].key;

        if (floor == null) throw new NoSuchElementException();
        return floor;
    }

    public Key ceiling(Key key) {
        if (isEmpty()) throw new NoSuchElementException();
        Key ceiling = null;

        for (int i = 0; i < N; i++)
            if (st[i].key.compareTo(key) >= 0)
                if (ceiling == null || st[i].key.compareTo(ceiling) < 0) ceiling = st[i].key;

        if (ceiling == null) throw new NoSuchElementException();
        return ceiling;
    }

    public int rank(Key key) {
        int rank = 0;
        for (int i = 0; i < N; i++)
            if (st[i].key.compareTo(key) < 0) rank++;
        return rank;
    }

    public Key select(int k) {
        if (k < 0 || k >= N) throw new NoSuchElementException();
        return st[k].key;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        delete(max());
    }

    private void resize(int capacity) {
        Node[] newST = (Node[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            newST[i] = st[i];
        st = newST;
    }

    public Iterable<Key> keys() {
        ArrayList<Key> keys = new ArrayList<Key>();
        for (int i = 0; i < N; i++) keys.add(st[i].key);
        return keys;
    }
}