// https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Time: O(n)
// Space: O(1) since reversal is in-place
class Solution {
    public int pairSum(ListNode head) {
        
        // If the list is empty, return 0
        if (head == null) {
            return 0;
        }

        int max = 0;
        ListNode fast = head;
        ListNode slow = head;

        // Use fast and slow pointers to find the middle of the list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow is now at the middle of the list
        // Reverse the second half of the linked list starting from slow
        ListNode secondHalf = reverse(slow);

        // Initialize pointer to the start of the list
        ListNode firstHalf = head;
        
        // Iterate through the first half and reversed second half lists
        // Calculate sums of the current pairs of nodes, keeping track of the max sum
        while (secondHalf != null) {
            max = Math.max(firstHalf.val + secondHalf.val, max);
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return max;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        // Iterate through the list and reverse the links
        while (cur != null) {
            ListNode next = cur.next; // Store the next node
            cur.next = prev; // Reverse the link
            prev = cur; // Move previous node to current node
            cur = next; // Move current node to next node
        }
        return prev; // Return the new head of the reversed list
    }
}
