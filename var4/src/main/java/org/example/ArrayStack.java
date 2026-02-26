package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item> {
    protected Item[] stack;
    protected int n;

    public ArrayStack() {
        stack = (Item[]) new Object[2];
        n = 0;
    }

    // O(1) у звичайному випадку (додавання елемента за індексом),
    // O(n) лише у момент розширення масиву, тому амортизовано — O(1)
    public void push(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (n == stack.length) {
            resize(2 * stack.length);
        }
        stack[n++] = item;
    }

    // O(1) у звичайному випадку (просто отримання елемента за індексом),
    // O(n) лише у момент звуження масиву, тому амортизовано — O(1)
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = stack[--n];
        stack[n] = null;
        if (n < stack.length/4) {
            resize(stack.length/2);
        }
        return item;
    }

    // O(1) — отримання елемента за індексом
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return stack[n-1];
    }

    // O(1) — повернення значення змінної
    public int size() { return n; }

    // O(1) — проста перевірка рівності
    public boolean isEmpty() { return n == 0; }

    // O(n) — потрібно скопіювати всі елементи в новий масив
    private void resize(int capacity) {
        Item[] newStack = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    // O(1) — створення нового об'єкта ітератора
    @Override
    public Iterator<Item> iterator() { return new StackIterator(); }

    private class StackIterator implements Iterator<Item> {
        private int i = n;

        // O(1) — проста перевірка умови
        @Override
        public boolean hasNext() { return i > 0; }

        // O(1) — доступ за індексом та декремент
        @Override
        public Item next() { return stack[--i]; }
    }
}