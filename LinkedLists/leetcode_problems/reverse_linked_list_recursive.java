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


// T: O(n) M: O(n)
 
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        
        // Base case: Empty list or only one node
        if (head == null || head.next == null) {
            return head;
        }

        newHead = reverseList(head.next);
        // After the recursive call returns, head.next is now 
        // pointing to the last node of the reversed sublist.
        // head.next.next = head makes the next node (the last node 
        // of the reversed sublist) point back to the current head, 
        // effectively reversing the link.
        head.next.next = head;
        // This sets the current head node’s next pointer to None 
        // to avoid a cycle. Now, the current head becomes the 
        // last node of the reversed list.
        head.next = null;
        return newHead;  // New head of the reversed list.
    }
}




 /*   ** EXPLANATION **
Consider a linked list with the following nodes: 1 -> 2 -> 3 -> 4 -> 5 -> None.

We want to reverse this list so that it becomes 5 -> 4 -> 3 -> 2 -> 1 -> None.

Let's follow the code step-by-step to see how it works:

Initial Call
reverseList(head)  # head points to the node with value 1

Step-by-Step Execution

First Call:
head points to node 1.
Since head is not None and head.next is not None, we proceed with the recursive call.
new_head = reverseList(head.next) which means new_head = reverseList(2 -> 3 -> 4 -> 5 -> None).

Second Call:
head points to node 2.
We make another recursive call: new_head = reverseList(head.next), which means new_head = reverseList(3 -> 4 -> 5 -> None).

Third Call:
head points to node 3.
We make another recursive call: new_head = reverseList(head.next), which means new_head = reverseList(4 -> 5 -> None).

Fourth Call:
head points to node 4.
We make another recursive call: new_head = reverseList(head.next), which means new_head = reverseList(5 -> None).

Fifth Call:
head points to node 5.
Since head.next is None, we hit the base case and return head, which is node 5.

Unwinding the Recursion
Now, we start returning from the recursive calls, adjusting the links as we go:

Returning from Fourth Call:
head points to node 4, new_head points to node 5.
We execute head.next.next = head:
head.next is 5.
head.next.next = 4, so the link is reversed: 5 -> 4.
We execute head.next = None:
head.next = None, so 4 -> None.
We return new_head, which is node 5. Now, the list looks like 5 -> 4 -> None.

Returning from Third Call:
head points to node 3, new_head points to node 5.
We execute head.next.next = head:
head.next is 4.
head.next.next = 3, so the link is reversed: 4 -> 3.
We execute head.next = None:
head.next = None, so 3 -> None.
We return new_head, which is node 5. Now, the list looks like 5 -> 4 -> 3 -> None.

Returning from Second Call:
head points to node 2, new_head points to node 5.
We execute head.next.next = head:
head.next is 3.
head.next.next = 2, so the link is reversed: 3 -> 2.
We execute head.next = None:
head.next = None, so 2 -> None.
We return new_head, which is node 5. Now, the list looks like 5 -> 4 -> 3 -> 2 -> None.

Returning from First Call:
head points to node 1, new_head points to node 5.
We execute head.next.next = head:
head.next is 2.
head.next.next = 1, so the link is reversed: 2 -> 1.
We execute head.next = None:
head.next = None, so 1 -> None.
We return new_head, which is node 5. Now, the list looks like 5 -> 4 -> 3 -> 2 -> 1 -> None.

Final Reversed List
The final reversed list is: 5 -> 4 -> 3 -> 2 -> 1 -> None.
  */