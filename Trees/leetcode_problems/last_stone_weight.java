// https://leetcode.com/problems/last-stone-weight/

import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int lastStoneWeight(int[] stones) {
        // Use Collections.reverseOrder() to create a maxHeap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int s : stones) {
            maxHeap.offer(s);
        }

        while (maxHeap.size() > 1) {
            // Extract two heaviest stones
            int first = maxHeap.poll();
            int second = maxHeap.poll();

            // If they are not equal, push the difference back into the heap
            if (first != second) {
                maxHeap.offer(first - second);
            }
        }
        // If there are no stones left, return 0. Otherwise, return the weight of the last remaining stone
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}
