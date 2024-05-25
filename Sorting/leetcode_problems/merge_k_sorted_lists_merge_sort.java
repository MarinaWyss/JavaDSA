// https://leetcode.com/problems/merge-k-sorted-lists/

// NOTE: A min-heap priority queue would be more efficient. 
// I'm just practicing with merge sort.

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) {
            return null;
        }

        // If there is only one LinkedList, return that
        if (lists.length == 1) {
            return lists[0];
        }

        // Divide list of LinkedLists into two halves
        int midIndex = lists.length / 2;
        // Recursively merge left half
        ListNode left = mergeKLists(Arrays.copyOfRange(lists, 0, midIndex));
        // Recursively merge right half
        ListNode right = mergeKLists(Arrays.copyOfRange(lists, midIndex, lists.length));

        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Initialize a dummy node to start the merged linked-list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Merge the two lists by comparing their node values
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1; // Attach l1 if its value is smaller
                l1 = l1.next; // Move to the next node in l1
            }
            else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next; // Move the current pointer
        }

        // Add any remaining nodes from l1 or l2 if they exist
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }
        // Return the head of the merged linked-list
        return dummy.next;
    }
}