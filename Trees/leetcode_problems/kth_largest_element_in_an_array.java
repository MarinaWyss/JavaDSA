// https://leetcode.com/problems/kth-largest-element-in-an-array/description/

import java.util.PriorityQueue;

class Solution {
    private PriorityQueue<Integer> heap = new PriorityQueue<>();

    public int findKthLargest(int[] nums, int k) {
        // Iterate over each element in the nums array
        for (int num : nums) {
            // Add the current number to the heap
            heap.offer(num);
            // If the size of the heap exceeds k, remove the smallest element
            // This ensures the heap only contains the k largest elements seen so far
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // The root of the heap (the smallest element in the heap) is the kth largest element
        return heap.peek();
    }
}