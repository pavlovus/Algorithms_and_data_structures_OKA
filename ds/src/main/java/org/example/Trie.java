package org.example;

import java.util.*;

public class Trie {
    private static class Node {
        private final Node[] next = new Node[26];
        private boolean isWord;
    }

    private final Node root = new Node();

    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.next[index] == null)
                node.next[index] = new Node();
            node = node.next[index];
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        Node node = findNode(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private Node findNode(String s) {
        Node node = root;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (node.next[index] == null)
                return null;
            node = node.next[index];
        }
        return node;
    }

    public List<String> getAllWords() {
        List<String> result = new ArrayList<>();
        collect(root, new StringBuilder(), result);
        return result;
    }

    private void collect(Node node, StringBuilder prefix, List<String> result) {
        if (node == null) return;
        if (node.isWord) result.add(prefix.toString());
        for (char c = 'a'; c <= 'z'; c++) {
            Node nextNode = node.next[c - 'a'];
            if (nextNode != null) {
                prefix.append(c);
                collect(nextNode, prefix, result);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apply");

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("ap"));
        System.out.println(trie.search("apt"));

        System.out.println(trie.getAllWords());
    }
}