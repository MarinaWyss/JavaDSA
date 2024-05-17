public class StaticArray<T> {
    private T[] arr;  // Internal storage for the static array
    private int length;  // Total number of values in the array
    private int capacity;  // Total allocated size of the array

    // Constructor to initialize the array
    public StaticArray(int capacity) {
        // Create a new Object array and cast it to the generic type
        // This can cause a ClassCastException at compile time if there
        // is an error with type safety. So, could use the 
        // @SuppressWarnings("unchecked") annotation if needed
        this.arr = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.length = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLength() {
        return length;
    }

    public void printArr() {
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == capacity;
    }

    public boolean contains(T element) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element) {
        for (int i = 0; i < length; i++) {
            if (arr[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < length; i++) {
            arr[i] = null;
        }
        length = 0;
    }

    public void insertEnd(T item) {
        if (length < capacity) {
            arr[length] = item;
            length++;
        }
    }

    public void removeEnd() {
        if (length > 0) {
            arr[length - 1] = null;
            length--;
        }
    }

    // Insert item at position i in the array
    public void insertMiddle(int i, T item) {
        if (length < capacity && i >= 0 && i < length) {
            // index is the last occupied position in the array
            // Stop when index >= i because this is where the new
            // element will go   
            // Move index backwards through array 
            for (int index = length - 1; index >= i; index--) {
                // Move elements one position to the right to create
                // free space at index i
                arr[index + 1] = arr[index];
            }
            arr[i] = item;
            length++;
        }
    }

    // Remove value at index i before shifting elements to the left
    public void removeMiddle(int i) {
        // Check the index is valid
        if (i >= 0 && i < length) {
            // index is the position immediately after the the element
            // to be removed.    
            for (int index = i + 1; index < length; index++) {
                // shift each element to the left
                arr[index - 1] = arr[index];
            }
            // Overwrite the last element
            arr[length - 1] = null; 
            length--;
        }
    }

}