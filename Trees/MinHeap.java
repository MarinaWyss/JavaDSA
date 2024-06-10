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
        // We have a dummy value, so size 1 == null
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
        // Run while we still have a left child
        while (leftChildIndex < heap.size()) {
            int smallerChildIndex = leftChildIndex;
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(leftChildIndex)) {
                smallerChildIndex = rightChildIndex;
            }
            if (heap.get(curr) > heap.get(smallerChildIndex)) {
                int tmp = heap.get(curr);
                heap.set(curr, heap.get(smallerChildIndex));
                heap.set(smallerChildIndex, tmp);
                curr = smallerChildIndex;
                leftChildIndex = 2 * curr;
                rightChildIndex = 2 * curr + 1;
            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.push(5);
        minHeap.push(3);
        minHeap.push(8);
        minHeap.push(1);

        System.out.println(minHeap.pop()); // Should print 1
        System.out.println(minHeap.pop()); // Should print 3
    }
}
