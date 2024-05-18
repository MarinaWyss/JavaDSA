import java.util.NoSuchElementException;

public class LinkedListQueue<T> {
    
    private static class Node<T> {
        T val;
        Node<T> next;
    
        Node(T val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(T val) {
        Node<T> newNode = new Node<>(val);
        if (tail != null) {
            // If the queue isn't empty
            tail.next = newNode;
            tail = newNode;
        }
        else {
            head = newNode;
            tail = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (head == null) {
            throw new NoSuchElementException("Queue is empty.");
        }
        
        T val = head.val;
        head = head.next;
        size--;

        // Check if queue is now empty
        if (head == null) {
            tail = null;
        }
        return val;
    }

    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return head.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }
}
