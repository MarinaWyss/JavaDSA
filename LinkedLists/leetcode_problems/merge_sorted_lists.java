// https://leetcode.com/problems/merge-two-sorted-lists/

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                current.next = list2;
                list2 = list2.next;
            } else {
                current.next = list1;
                list1 = list1.next;
            }
            current = current.next;
        }
        // if the lists are different lengths, after the while loop
        // there may still be elements in one list
        // Check if there are remaining elements in list1
        if (list1 != null) {
            // If there are remaining elements in list1, link list1 to cur.next
            current.next = list1;
        } else {
            // If list1 is null (no remaining elements in list1), link list2 to cur.next
            current.next = list2;
        }

        return dummy.next;
    }
}
