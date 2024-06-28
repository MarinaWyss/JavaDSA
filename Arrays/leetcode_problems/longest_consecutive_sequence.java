// https://leetcode.com/problems/longest-consecutive-sequence/description/

import java.util.Set;
import java.util.HashSet;

// Time: O(n)
// Space: O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        // Convert list to set for O(1) lookup complexity
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) {
            numSet.add(n);
        }
    
        // Longest sequence found so far
        int longest = 0;

        for (int n : nums) {
            // If n-1 is not present, n is the smallest number in the current sequence
            if (!numSet.contains(n - 1)) {
                // If n is the start of a new sequence, length from n is now 1
                int length = 1;

                // Extend the current sequence until the next consecutive num
                // is not found. Note this runs at most once per n
                while (numSet.contains(n + length)) {
                    length++;
                }
                longest = Math.max(longest, length);
            }
        }
        return longest;
    }
}