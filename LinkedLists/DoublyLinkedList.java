public class DoublyLinkedList<T> {
    private class Node<T> {
        T val;
        Node<T> next;
        Node<T> prev;
    
        Node(T val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        // Create sentinel node
        // Sentinel nodes are specially designed nodes that do not hold or refer to any data in a DLL
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }


    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        
        Node<T> current = head.next;  // Start at the first actual node
        for (int i = 0; i < index; i++) {
            current = current.next; // Traverse until we reach the index
        }
        return current.val; // Then return the value at the desired index
    }

    public void insertFront(T val) {
        Node<T> newNode = new Node<>(val); // Create a new node with the given data
        newNode.prev = head;  // Set new node's prev to head
        newNode.next = head.next;  // Set new node's next to the current first node

        head.next.prev = newNode;  // Update the current first node's prev to the new node
        head.next = newNode;  // Set head's next to the new node
        size++;
    }

    public void insertBack(T val) {
        Node<T> newNode = new Node<>(val); // Create a new node with the given data
        newNode.next = tail;  // Set new node's next to tail
        newNode.prev = tail.prev;  // Set new node's prev to the current last node

        tail.prev.next = newNode;  // Update the current last node's next to the new node
        tail.prev = newNode;  // Set tail's prev to the new node
        size++;
    }

    public void insertAtIndex(int index, T val) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index == 0) {
            insertFront(val);
            return; 
        }

        if (index == size) {
            insertBack(val);
            return; 
        }

        Node<T> newNode = new Node<>(val); // Create a new node with the given data


        Node<T> current = head.next;  // Start at the first actual node
        for (int i = 0; i < index; i++) {
            current = current.next; // Traverse until we reach the index
        }
        newNode.next = current.next;  
        newNode.prev = current.prev;
        current.next.prev = newNode;  // The node that originally followed current now links to the newNode
        current.next = newNode;  // current now points to newNode
        size++;
    }

    public void removeFromIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }

        Node<T> current = head.next;  // Start at the first actual node
        for (int i = 0; i < index; i++) {
            current = current.next; // Traverse until we reach the index
        }
        
        current.prev.next = current.next;  // Bypass current by linking prev and next nodes
        current.next.prev = current.prev;  // Bypass current by linking next and prev nodes
        size--;
    }

    public void displayList() {
        Node<T> current = head.next;

        while (current != tail) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println(); // Print a new line after displaying all elements
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>(); // Create a new doubly linked list of integers
        list.insertBack(1);
        list.insertBack(2);
        list.insertBack(3);
        list.insertBack(4);
        list.displayList(); // Output: 1 2 3 4

        list.insertFront(0);
        list.displayList(); // Output: 0 1 2 3 4

        list.insertAtIndex(2, 5);
        list.displayList(); // Output: 0 1 5 2 3 4

        list.removeFromIndex(3);
        list.displayList(); // Output: 0 1 5 3 4

        try {
            System.out.println(list.get(2)); // Output: 5
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

        // Test error handling for negative index
        try {
            list.insertAtIndex(-1, 10);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage()); // Output: Index out of range.
        }

        // Test error handling for index out of range
        try {
            list.removeFromIndex(10);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage()); // Output: Index out of range.
        }
    }
}
