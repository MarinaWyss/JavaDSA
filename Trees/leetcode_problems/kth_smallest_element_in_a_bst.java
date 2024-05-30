// https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

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
class Solution {
    private int count = 0;
    private int result = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        inorderHelper(root, k);
        return result;
    }
    
    private void inorderHelper(TreeNode node, int k) {
        // Use in-order traversal to sort elements
        if (node == null) {
            return;
        }
        
        inorderHelper(node.left, k);
        
        // Count each node visited so far
        count++;

        // Once we reach k, return the value and stop traversing
        if (count == k) {
            result = node.val;
            return;
        }
        
        inorderHelper(node.right, k);
    }
}
