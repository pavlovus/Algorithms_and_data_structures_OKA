import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class Tester {

    @Test
    public void testArrayStack() {
        ArrayStack_Vus<Integer> stack = new ArrayStack_Vus<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
        stack.push(3);
        Iterator<Integer> it = stack.iterator();
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testLinkedStack() {
        LinkedStack_Vus<String> stack = new LinkedStack_Vus<>();
        assertTrue(stack.isEmpty());
        stack.push("a");
        stack.push("b");
        assertEquals(2, stack.size());
        assertEquals("b", stack.pop());
        assertEquals("a", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testArrayQueue() {
        ArrayQueue_Vus<Integer> queue = new ArrayQueue_Vus<>();
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testLinkedQueue() {
        LinkedQueue_Vus<String> queue = new LinkedQueue_Vus<>();
        assertTrue(queue.isEmpty());
        queue.enqueue("x");
        queue.enqueue("y");
        assertEquals(2, queue.size());
        assertEquals("x", queue.dequeue());
        assertEquals("y", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDeque() {
        Deque_Vus<Integer> deque = new Deque_Vus<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        assertEquals(3, deque.size());
        assertEquals(0, deque.removeFirst());
        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.size());
        assertEquals(1, deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testRandomizedQueue() {
        RandomizedQueue_Vus<Integer> rq = new RandomizedQueue_Vus<>();
        assertTrue(rq.isEmpty());
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        assertEquals(3, rq.size());

        int sizeBefore = rq.size();
        int item = rq.dequeue();
        assertTrue(item >= 1 && item <= 3);
        assertEquals(sizeBefore - 1, rq.size());

        int sample = rq.sample();
        assertTrue(sample >= 1 && sample <= 3);

        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) {
            int x = it.next();
            assertTrue(x >= 1 && x <= 3);
        }
    }
}
