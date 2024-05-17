// https://leetcode.com/problems/reverse-linked-list/

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
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            // We need to preserve the reference to the next node before we modify it
            ListNode next = current.next;
            // Reverse pointer direction
            current.next = prev;
            // Move forward in the new list
            prev = current;
            // Move forward in the original list
            current = next;
        }
        return prev;  // new head of the reversed list
    }
}
