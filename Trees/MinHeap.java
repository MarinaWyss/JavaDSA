import java.util.List;
import java.util.ArrayList;

public class MinHeap {
    List<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
        heap.add(0); // We don't use the zeroth index in heaps
    }

    public void push(int val) {
        // First, put the new value at the end of the heap
        heap.add(val);

        // Then "percolate up," checking if the child is <= the parent. Swap if so.
        int curr = heap.size() - 1;  // Index we just inserted at
        int parentIndex = curr / 2;
        while (curr > 1 && heap.get(curr) < heap.get(parentIndex)) {
            int tmp = heap.get(curr);
            // Move parent into child position
            heap.set(curr, heap.get(parentIndex));
            // Move child into parent position
            heap.set(parentIndex, tmp);
            // Increment to next parent
            curr = parentIndex;
            parentIndex = curr / 2;
        }
    }

    public Integer pop() {
        // We have a dummy value, so size 1 means the tree is empty
        if (heap.size() == 1) {
            return null;
        }

        // If there is just one value, return that
        if (heap.size() == 2) {
            return heap.remove(1);
        } 

        int res = heap.get(1);

        // First, swap the root with the last value to satisfy shape property
        heap.set(1, heap.remove(heap.size() - 1));

        // Then, "percolate down," checking if the parent is >= child. Swap if so.
        int curr = 1;
        int leftChildIndex = 2 * curr;
        int rightChildIndex = 2 * curr + 1;
        percolateDown(curr, leftChildIndex, rightChildIndex);
        return res;
    }

    public void heapify(ArrayList<Integer> arr) {
        // Ignore the zero-th position
        heap = new ArrayList<>(arr.size() + 1);
        heap.add(0);
        heap.addAll(arr);

        // Start at first node with children, leaf nodes can't violate min-heap properties
        int curr = (heap.size() - 1) / 2;
        while (curr > 0) {
            int leftChildIndex = 2 * curr;
            int rightChildIndex = 2 * curr + 1;
            percolateDown(curr, leftChildIndex, rightChildIndex);
            // Go back up the tree
            curr--;
        }
        return;
    }

    public void percolateDown(int curr, int leftChildIndex, int rightChildIndex) {
        // Run while we still have a left child
        while (leftChildIndex < heap.size()) {
            // Assume left child is the smaller child
            int smallerChildIndex = leftChildIndex;
            
            // If right child exists and is smaller than left child, update smallerChildIndex
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(leftChildIndex)) {
                smallerChildIndex = rightChildIndex;
            }

            // If the current node is greater than the smaller child, swap them
            if (heap.get(curr) > heap.get(smallerChildIndex)) {
                // Swap the current node with the smaller child
                int tmp = heap.get(curr);
                heap.set(curr, heap.get(smallerChildIndex));
                heap.set(smallerChildIndex, tmp);
                // Update the current node index to the smaller child index
                curr = smallerChildIndex;
                // Update left and right child indices for the new current node
                leftChildIndex = 2 * curr;
                rightChildIndex = 2 * curr + 1;
            } else {
                // If the current node is smaller than or equal to the smaller child, we're done
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        // Create a MinHeap instance
        MinHeap minHeap = new MinHeap();

        // Test the push method
        minHeap.push(5);
        minHeap.push(3);
        minHeap.push(8);
        minHeap.push(1);
        System.out.println("Heap after pushes: " + minHeap.heap); // Should print heap structure

        // Test the pop method
        System.out.println("Pop: " + minHeap.pop()); // Should print 1
        System.out.println("Pop: " + minHeap.pop()); // Should print 3
        System.out.println("Heap after pops: " + minHeap.heap); // Should print heap structure

        // Test the heapify method
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(4);
        arr.add(10);
        arr.add(3);
        arr.add(5);
        arr.add(1);
        minHeap.heapify(arr);
        System.out.println("Heap after heapify: " + minHeap.heap); // Should print heap structure

        // Test the pop method after heapify
        System.out.println("Pop after heapify: " + minHeap.pop()); // Should print 1
        System.out.println("Pop after heapify: " + minHeap.pop()); // Should print 3
        System.out.println("Heap after pops post-heapify: " + minHeap.heap); // Should print heap structure
    }
}
