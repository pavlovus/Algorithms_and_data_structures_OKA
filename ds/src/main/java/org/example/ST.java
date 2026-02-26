package org.example;

import java.util.Iterator;

public class ST<Key extends Comparable<Key>, Value> {
    private Node<Key,Value>[] map;
    private int n;

    public ST() {
        map = (Node<Key,Value>[]) new Node[10];
        n = 0;
    }

    public void put(Key key, Value val) {
        if (key == null) return;
        int i = rank(key);
        if (isEmpty()){
            Node t = new Node();
            t.key = key;
            t.val = val;
            map[n++] = t;
            return;
        }
        if (i < n && map[i].key.compareTo(key) == 0)
            map[i].val = val;
        else{
            if (n == map.length) resize(2 * map.length);
            for (int j = n; j > i; j--){
                map[j] = map[j-1];
            }
            map[i] = new Node();
            map[i].key = key;
            map[i].val = val;
            n++;
        }
    }

    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
        else return null;
    }

    public void delete(Key key){
        if (isEmpty()) return;
        int i = rank(key);
        if (i >= n || map[i].key.compareTo(key)!=0) return;
        for (int j = i; j < n; j++){
            map[j] = map[j+1];
        }
        n--;
        map[n] = null;
        if (n > 0 && n == map.length / 4) resize(map.length / 2);
    }

    public boolean contains(Key key){ return get(key) != null; }

    public boolean isEmpty() {return n == 0;}

    public int size() {return n;}

    public Key min() {return map[0].key;}

    public Key max() {return map[n - 1].key;}

    public Key floor(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) return map[i].key;
        if (i == 0) return null;
        else return map[i - 1].key;
    }

    public Key ceiling(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) return map[i].key;
        if (i >= n-1) return null;
        else return map[i].key;
    }

    private int rank(Key key){
        int lo = 0, hi = n - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(map[mid].key);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public Key select(int k) {
        if (k < 0 || k >= n) return null;
        return map[k].key;
    }

    public void deleteMin(){
        if (isEmpty()) return;
        delete(min());
    }

    public void deleteMax(){
        if (isEmpty()) return;
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (lo == null || hi == null || lo.compareTo(hi)>0 || rank(lo) == n) return 0;

        if (lo.compareTo(hi) == 0) {
            int i = rank(lo);
            if (i < n && map[i].key.compareTo(lo) == 0) return 1;
            else return 0;
        }

        int from = rank(lo);
        int hiRank = rank(hi);
        int to = (hiRank < n && map[hiRank].key.compareTo(hi) == 0) ? hiRank : hiRank - 1;
        return to - from + 1;
    }

    private void resize(int capacity){
        Node<Key,Value>[] copy = (Node<Key,Value>[]) new Node[capacity];
        if (n >= 0) System.arraycopy(map, 0, copy, 0, n);
        map = copy;
    }

    public Iterable<Key> keys(){ return new KeyIterator(); }

    public Iterator<Key> keys(Key lo, Key hi){
        if (lo == null || hi == null || lo.compareTo(hi) > 0 || rank(lo) == n) return new KeyIterator(0, -1);;
        int from = rank(lo);
        int hiRank = rank(hi);
        int to = (hiRank < n && map[hiRank].key.compareTo(hi) == 0) ? hiRank : hiRank - 1;
        return new KeyIterator(from, to);
    }

    private class KeyIterator implements Iterator<Key>, Iterable<Key>{
        private int current;
        private int last;

        public KeyIterator(){
            current = 0;
            last = n;
        }

        public KeyIterator(int lo, int hi){
            current = lo;
            last = hi + 1;
        }
        @Override
        public boolean hasNext() {
            return current < last;
        }

        @Override
        public Key next() {
            return map[current++].key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Iterator<Key> iterator() {
            return this;
        }
    }

    private static class Node<Key,Value>{
        Key key;
        Value val;
    }
}