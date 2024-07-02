// https://leetcode.com/problems/binary-tree-postorder-traversal/description/

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

 import java.util.List;
 import java.util.ArrayList;
 
 class Solution {
     List<Integer> res = new ArrayList<>();
     public List<Integer> postorderTraversal(TreeNode root) {
         helper(root);
         return res;
     }
 
     private void helper(TreeNode node) {
         if (node == null) {
             return;
         }
         helper(node.left);
         helper(node.right);
         res.add(node.val);
     }
 }
 