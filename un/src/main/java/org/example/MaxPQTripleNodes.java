package org.example;


public class MaxPQTripleNodes<Key extends Comparable<Key>> {
    private Node root;
    int n;

    private class Node {
        private Key key;
        private Node left;
        private Node right;
        private Node parent;
        Node(Key key) {
            this.key = key;
        }
    }

    public boolean isEmpty(){
        return n ==0;
    }

    public int size(){ return n; }

    public void insert(Key key){
        if (key == null) throw new NullPointerException();
        n++;
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = getNode(n / 2);
        newNode.parent = parent;

        if (n % 2 == 0) parent.left = newNode;
        else parent.right = newNode;

        swim(newNode);
    }

    public Key delMax(){
        if (isEmpty()) throw new IllegalStateException();
        Key max = root.key;
        if (n == 1) {
            root = null;
            n = 0;
            return max;
        }

        Node last = getNode(n);
        root.key = last.key;

        if (last.parent.left == last) last.parent.left = null;
        else last.parent.right = null;

        n--;
        sink(root);
        return max;
    }

    private Node getNode(int index) {
        String path = Integer.toBinaryString(index).substring(1);
        Node node = root;
        for (char c : path.toCharArray()) {
            if (c == '0') node = node.left;
            else node = node.right;
        }
        return node;
    }

    private void swim(Node node){
        while (node.parent != null && less(node.parent, node)){
            exch(node,node.parent);
            node = node.parent;
        }
    }

    private void sink(Node node){
        while(node.left != null){
            Node larger = node.left ;
            if (node.right != null && less(node.left, node.right)) larger = node.right;
            if (!less(larger, node)) break;
            exch(node, larger);
            node = larger;
        }
    }

    private boolean less(Node first, Node second){ return first.key.compareTo(second.key) < 0; }

    private void exch(Node a, Node b) {
        Key temp = a.key;
        a.key = b.key;
        b.key = temp;
    }
}