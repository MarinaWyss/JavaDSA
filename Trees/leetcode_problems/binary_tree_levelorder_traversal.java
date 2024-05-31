// https://leetcode.com/problems/binary-tree-level-order-traversal/description/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
    // Method to perform level order traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        // List of lists to store the result of level order traversal
        List<List<Integer>> result = new ArrayList<>();
        
        // If the root is null, return the empty result list
        if (root == null) {
            return result;
        }

        // Deque to store nodes for BFS
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        // While there are nodes to process in the queue
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int levelSize = queue.size();
            // List to store the values of nodes at the current level
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process each node at the current level
            for (int i = 0; i < levelSize; i++) {
                // Remove the node from the front of the queue
                TreeNode curr = queue.removeFirst();
                // Add the node's value to the current level list
                currentLevel.add(curr.val);

                // If the left child exists, add it to the queue to process next
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                // If the right child exists, add it to the queue to process next
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            // Add the current level list to the result list
            result.add(currentLevel);
        }

        // Return the list of level order traversal
        return result;
    }
}