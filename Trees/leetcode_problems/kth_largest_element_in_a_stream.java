// https://leetcode.com/problems/kth-largest-element-in-a-stream/description/

import java.util.PriorityQueue;

class KthLargest {
    // Use a min-heap to store the k largest elements seen so far.
    // The root of the min-heap is the smallest of the k largest elements,
    // which means it is the k-th largest element overall.
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        // Add elements from the initial array to the heap
        for (var n: nums) {
            add(n);
        }
    }
    
    public int add(int val) {
        // Add the new value to the heap
        minHeap.offer(val);
        // If the size of the heap exceeds k, remove the smallest element
        // This keeps the size of the heap at most k
        if (minHeap.size() > k) {
            minHeap.poll();  // Remove root
        }
        // The root of the heap is the k-th largest element
        return minHeap.peek();  // Return (but don't remove) root
    }
}

/*
Example to Illustrate

Suppose k = 3 and we have the following stream of numbers: [4, 5, 8, 2, 3, 10, 9].

1. Initialization:
   - Start by adding the first k elements to the heap: [4, 5, 8].
   - The min-heap is: [4, 5, 8] (root is 4, which is the 3rd largest element so far).

2. Processing Remaining Elements:
   - Add 2:
     - Since 2 is smaller than the root of the heap (4), it is ignored.
   - Add 3:
     - Similarly, 3 is smaller than 4, so it is ignored.
   - Add 10:
     - Insert 10 into the heap: [4, 5, 8, 10].
     - The heap size exceeds k, so remove the smallest element (4): [5, 10, 8].
     - The new min-heap is [5, 10, 8], with root 5 (which is now the 3rd largest element).
   - Add 9:
     - Insert 9 into the heap: [5, 9, 8, 10].
     - The heap size exceeds k, so remove the smallest element (5): [8, 9, 10].
     - The new min-heap is [8, 9, 10], with root 8 (which is the 3rd largest element).

Summary:
The smallest element in a min-heap of size k is the kth largest element in the stream because:
- The heap contains the k largest elements seen so far.
- Among these k elements, the smallest one (the root of the heap) is the kth largest in the 
overall sorted order of the stream.
- As new elements are added, the heap dynamically adjusts to maintain this property by 
ensuring it only contains the largest k elements, and the root always reflects the kth largest element.
*/
