// https://leetcode.com/problems/binary-search-tree-iterator/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import java.util.Stack;

class BSTIterator {
 
    private Stack<TreeNode> stack = new Stack<TreeNode>();
 
    public BSTIterator(TreeNode root) {
        // Push all nodes from root to the leftmost node onto the stack
        pushAll(root);
    }
     
    public int next() {
        // Pop the top node from the stack
        TreeNode tmp = stack.pop();
        // Push all nodes from the right child of the popped node 
        // to its leftmost descendant onto the stack
        pushAll(tmp.right);
        return tmp.val;
    }
     
    public boolean hasNext() {
        // If the stack is not empty, there are more nodes to visit
        return !stack.isEmpty();
    }
 
    private void pushAll(TreeNode node) {
        // Continue looping until node is null
        while (node != null) {
            // Push the current node onto the stack
            stack.push(node);
            // Move to the left child of the current node
            node = node.left;
        }
    }
}

/*
* 
* Example:
* 
*     4
*    / \
*   2   6
*  / \ / \
* 1  3 5  7
* 
* Initialization:
* - `pushAll(root)` pushes 4, then 2, then 1 onto the stack.
* - Stack: [4, 2, 1]
* 
* First Call to `next()`:
* - Pops 1 (smallest node).
* - Stack: [4, 2]
* - `pushAll(1.right)` (1 has no right child, so no change).
* - Returns 1.
* 
* Second Call to `next()`:
* - Pops 2.
* - Stack: [4]
* - `pushAll(2.right)` pushes 3.
* - Stack: [4, 3]
* - Returns 2.
* 
* Third Call to `next()`:
* - Pops 3.
* - Stack: [4]
* - `pushAll(3.right)` (3 has no right child, so no change).
* - Returns 3.
* 
* Fourth Call to `next()`:
* - Pops 4.
* - Stack: []
* - `pushAll(4.right)` pushes 6, then 5.
* - Stack: [6, 5]
* - Returns 4.
* 
* And so on...
*/
