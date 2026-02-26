package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item> {
    private Node first = null;
    private int count = 0;
    private class Node {
        Item item;
        Node next;
    }

    // Додає елемент на верхівку стеку.Часова складність: O(1), створення нового вузла і перенаправлення одного посилання не залежать від кількості елементів у структурі.
    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        count++;
    }

    //Видаляє і повертає елемент із верхівки стеку.Часова складність: O(1), потрібно лише змінити посилання first → first.next і зменшити лічильник.
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }

    //Повертає елемент з верхівки стеку без видалення. Часова складність: O(1), просто звертаємось до first.item
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    // Часова складність: O(1). Доведення: перевіряється лише одне посилання first.
    public boolean isEmpty() {
        return first == null;
    }

    // Часова складність: O(1), бо є пряме посилання на count, який оновлюється в методах.
    public int size() {
        return count;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null; // O(1)
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