// https://leetcode.com/problems/find-the-duplicate-number/description/

/**
 * This approach uses Floyd's Tortoise and Hare (Cycle Detection) to find the duplicate.
 * 
 * The idea is to treat the array as a linked list where each element points to the index of the next element.
 * The duplicate number will cause a cycle in this linked list. Using two pointers (slow and fast),
 * we detect the cycle and then find the entry point of the cycle, which is the duplicate number.
 * Time: O(n)
 * Space: O(1)
 */

 class Solution {
    public int findDuplicate(int[] nums) {
        int fast = nums[0];
        int slow = nums[0];

        // Phase 1: Find the intersection point of the two pointers
        while (true) {
            slow = nums[slow]; // Move slow pointer one step
            fast = nums[nums[fast]]; // Move fast pointer two steps
            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // Phase 2: Find the entry point to the cycle
        int slow2 = nums[0]; // Initialize a new pointer to the start
        while (slow != slow2) { // Move both pointers one step at a time until they meet
            slow = nums[slow];
            slow2 = nums[slow2];
        }

        return slow; // The point of intersection is the duplicate number
    }
}
