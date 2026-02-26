package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {
	private Node first, last;
	private int count = 0;
	
	private class Node{
		Item item;
		Node next;
	}

    // Додає елемент у кінець черги. Часова складність: O(1) — маємо пряме посилання на "last".
	public void enqueue(Item item) {
        if(item == null) throw new NullPointerException();
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

    // Видаляє і повертає елемент з початку черги.Часова складність: O(1) — видаляємо "first" без проходження списку.
	public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		count--;
		if (isEmpty()) last = null;
		return item;
	}

    // Перевіряє, чи порожня черга. Часова складність: O(1) — просто перевірка посилання.
	public boolean isEmpty() { return first == null; }

    //Повертає кількість елементів у черзі.Часова складність: O(1) — поле count оновлюється при кожній зміні.
	public int size() { return count; }

    // Повертає перший елемент черги без видалення. Часова складність O(1) - пряме посилання на first
    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException();
        return first.item;
    }

    //Ітератор, який проходить елементи у порядку FIFO. Ітерація має час O(n), як і у будь-якій зв’язній структурі.
    @Override
    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}