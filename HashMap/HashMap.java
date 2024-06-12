// Super simple HashMap implementation
// This ignores a lot of optimizations/complexity

import java.util.NoSuchElementException;

public class HashMap<T> {

    private static class Pair<T> {
        String key;
        T value;

        public Pair(String key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    int size;
    int capacity;
    Pair<T>[] map;

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.size = 0;
        this.capacity = 2;
        this.map = (Pair<T>[]) new Pair[capacity];

    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public int hash(String key) {
        int index = 0;
        for (int i = 0; i < key.length(); i++) {
            index += (int) key.charAt(i);
        }
        return index % this.capacity;
    }

    public T get(String key) {
        int index = hash(key);

        // Use open addressing to handle collisions
        while (this.map[index] != null) {
            if (this.map[index].key.equals(key)) {
                return this.map[index].value;
            }
            // If we didn't find the key, try the next index
            index++;
            index = index % this.capacity;
        } 
        // Key doesn't exist in HashMap
        return null;
    }

    public void put(String key, T value) {
        int index = hash(key);

        while (true) {
            // Is the index empty? If so, insert there
            if (this.map[index] == null) {
                this.map[index] = new Pair<>(key, value);
                this.size++;

                // Check if we are filling up the array and need to rehash
                if (this.size >= this.capacity / 2) {
                    rehash();
                }
                return;
            }
            // If it isn't empty, we check if the key is the same so we can overwrite
            else if (this.map[index].key.equals(key)) {
                this.map[index].value = value;
                return;
            }
            // If it isn't empty and taken by another key, use open addressing to handle
            // the collision. Increment by one and check everything again.
            index++;
            index = index % this.capacity;
        }
    }

    public void remove(String key) {
        // Is the index empty? If so, raise an error
        if (get(key) == null) {
            throw new NoSuchElementException("Key does not exist in the HashMap");
        }

        int index = hash(key);
        while (true) {
            // If it isn't empty, see if the key matches and remove if so
            // Note that removing elements using open-addressing causes a bug
            // because we can make a hole in the array, which could cause get() 
            // to stop searching if it gets to this hole.
            // A better way to handle this is to mark the spot as deleted (using a special marker)
            // and perform a rehashing of subsequent elements in the same cluster.
            // This ensures that the search does not stop prematurely at the hole.
            if (this.map[index].key.equals(key)) {
                this.map[index] = null;
                this.size--;
                return;
            }
            index++;
            index = index % this.capacity;
        }
    }

    @SuppressWarnings("unchecked")
    public void rehash() {
        // Ideally we'd resize to the next prime number
        // For now I'm just going to double the capacity.
        this.capacity = this.capacity * 2;
        Pair<T>[] newMap = (Pair<T>[]) new Pair[capacity];

        Pair<T>[] oldMap = this.map;
        this.map = newMap;
        this.size = 0;
        
        for (Pair<T> pair : oldMap) {
            if (pair != null) {
                put(pair.key, pair.value);
            }
        } 
    }

    public void print() {
        for (Pair<T> pair : this.map) {
            if (pair != null) {
                System.out.println(pair.key + " " + pair.value);
            }
        }
    }

    public static void main(String[] args) {
        // Create a new HashMap instance
        HashMap<Integer> hashMap = new HashMap<>();

        // Test adding elements
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        System.out.println("After adding elements:");
        hashMap.print();

        // Test getting elements
        System.out.println("\nGet elements:");
        System.out.println("Key: one, Value: " + hashMap.get("one"));
        System.out.println("Key: two, Value: " + hashMap.get("two"));
        System.out.println("Key: three, Value: " + hashMap.get("three"));
        System.out.println("Key: four, Value: " + hashMap.get("four")); // should return null

        // Test updating elements
        hashMap.put("two", 22);
        System.out.println("\nAfter updating key 'two':");
        hashMap.print();

        // Test removing elements
        hashMap.remove("two");
        System.out.println("\nAfter removing key 'two':");
        hashMap.print();

        // Test removing non-existing element
        try {
            hashMap.remove("four");
        } catch (NoSuchElementException e) {
            System.out.println("\nTrying to remove key 'four' which does not exist: " + e.getMessage());
        }

        // Test rehashing
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        System.out.println("\nAfter adding more elements to trigger rehashing:");
        hashMap.print();
    }
}