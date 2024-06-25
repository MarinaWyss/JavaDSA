// https://leetcode.com/problems/linked-list-cycle-ii/description/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 // Time: O(n)
 // Space: O(1)
 public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // If fast and slow meet each other, there must be a cycle
            if (fast == slow) {
                // Reset the slow pointer to the head and move both pointers one step until
                // they meet again. This is where the cycle starts.
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
