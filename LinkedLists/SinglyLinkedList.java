class Node<T> {
    T val;
    Node<T> next;

    Node(T val) {
        this.val = val;
        this.next = null;
    }
}

public class SinglyLinkedList<T> {
    Node<T> head;  

    SinglyLinkedList() {
        this.head = null;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }
        
        if (head == null) {
            return null;
        }

        Node<T> current = head;  // Start at the head of the list
        int currentIndex = 0;

        // Traverse from head through next pointers
        while (current != null) {
            // Once we get to the index, return the value
            if (currentIndex == index) {
                return current.val;
            }
            current = current.next;
            currentIndex++;
        }
        return null;  // If the index is out of range
    }

    public void insertHead(T val) {
        Node<T> newNode = new Node<>(val); // Create a new node with the given data
        newNode.next = head;
        head = newNode;
    }

    public void insertTail(T val) {
        Node<T> newNode = new Node<>(val); // Create a new node with the given data

        if (head == null) {
            // Set the new node as the head if the list is empty
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            // Once we get to the end, add the new node
            current.next = newNode;
        }
    }

    public void insertAtIndex(int index, T val) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }

        Node<T> newNode = new Node<>(val); // Create a new node with the given data

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node<T> current = head;  // Start at the head of the list
        int currentIndex = 0;

        // Traverse the list to find the node before the target index
        while (current != null && currentIndex < index - 1) {
            current = current.next;
            currentIndex++;
        }

        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public void removeFromIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative.");
        }
        if (head == null) {
            System.err.println("Cannot remove from an empty list.");
            return;
        }

        if (index == 0) {
            // If the node to be removed is the head, move the head to the next node
            head = head.next;
            return;
        }

        Node<T> current = head;  // Start at the head of the list
        int currentIndex = 0;

        // Traverse until we find the Node before the one to be removed
        while (current != null && currentIndex < index - 1) {
            current = current.next;
            currentIndex++;
        }
        
        if (current == null || current.next == null) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }

        current.next = current.next.next;
    }

    public void displayList() {
        Node<T> current = head;

        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println(); // Print a new line after displaying all elements
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(); // Create a new linked list of integers
        list.insertTail(1);
        list.insertTail(2);
        list.insertTail(3);
        list.insertTail(4);
        list.displayList(); // Output: 1 2 3 4

        list.insertHead(0);
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
            System.err.println(e.getMessage()); // Output: Index cannot be negative.
        }

        // Test error handling for index out of range
        try {
            list.removeFromIndex(10);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage()); // Output: Index out of range.
        }
    }
}
