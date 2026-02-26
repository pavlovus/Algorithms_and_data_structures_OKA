package org.example;

public interface Queue_Vus<Item> {

	public void enqueue(Item item);
	public Item dequeue();
	public boolean isEmpty();
	public int size();
}
