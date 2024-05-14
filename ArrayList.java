public class ArrayList <T> {

    private static final int initialCapacity = 9;  // size in memory
    private int size = 0;  //  number of elements currently in the array
    // T[] is an array of generic type T, which can be replaced with any object type
    private T[] backingArray;  // internal array to hold list elements

    public ArrayList() {
        // Initialize the backing array with the initial capacity
        backingArray = (T[]) new Object[initialCapacity];
        // Initialize size to 0 as the list starts empty
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return backingArray.length;
    }

    public T getItemAtIndex(int i) {
        return backingArray[i];  // no error handling here
    }

    private void resize() {
        // Create a new array with double the current capacity
        T[] newArray = (T[]) new Object[backingArray.length * 2];
        // Copy elements from the old array to the new one
        for (int i = 0; i < size; i++) {
            newArray[i] = backingArray[i];
        }
        // Set the backing array to the new array
        backingArray = newArray;
    }

    public void addToFront(T data) { // use T since data can be any type, defined when instantiated
        // Check if the data is null and throw an exception if it is
        if (data == null) {
            // have to instantiate a instance of the IllegalArgumentException
            // -- that's why it says new here.
            throw new IllegalArgumentException("Data cannot be null");
        }
        // Resize the array if there is no room for new elements
        if (size == backingArray.length) {
            resize();
        }
        // Shift existing elements to the right to make room at the front
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        // Insert the new element at the front of the array
        backingArray[0] = data;
        // Increment the size after adding the new element
        size++;
    }
    
    public void addToBack(T data) {
        // Check if the data is null and throw an exception if it is
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        // Resize the array if there is no room for new elements
        if (size == backingArray.length) {
            resize();
        }
        // Add the new element at the back of the array
        backingArray[size] = data;
        // Increment the size after adding the new element
        size++;
    }

    public void addToIndex(int i, T data) {
        // Check if the data is null and throw an exception if it is
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        // Check if the index is out of bounds
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // Resize the array if there is no room for new elements
        if (size == backingArray.length) {
            resize();
        }
        // index is the last occupied position in the array
        // Stop when index >= i because this is where the new
        // element will go   
        // Move index backwards through array 
        for (int index = size - 1; index >= i; index--) {
            // Move elements one position to the right to create
            // free space at index i
            backingArray[index + 1] = backingArray[index];
        }
        backingArray[i] = data;
        size++;
    }

    public T removeFromFront() {
        // Return null if the list is empty
        if (size == 0) {
            return null;
        }
        // Store the front element to return later
        T removedData = backingArray[0];
        // Shift elements to the left to fill the gap
        for (int i = 0; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        // Set the last element to null after the shift
        backingArray[size - 1] = null;
        // Decrement the size after removing the element
        size--;
        return removedData;
    }
    
    public T removeFromBack() {
        // Return null if the list is empty
        if (size == 0) {
            return null;
        }
        // Store the last element to return later
        T removedData = backingArray[size - 1];
        // Set the last element to null
        backingArray[size - 1] = null;
        // Decrement the size after removing the element
        size--;
        return removedData;
    }

    public void removeFromIndex(int i) {
        // Check if the index is out of bounds
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // index starts as the element to be removed. We loop until
        // we get to the second-to-last element in the array. We move
        // from the index to be removed to the end of the array.
        for (int index = i; index < size - 1; index++) {
            // shift each element one position to the left.
            backingArray[index] = backingArray[index + 1];
        }
        backingArray[size - 1] = null;
        size--;
    }
    
}
