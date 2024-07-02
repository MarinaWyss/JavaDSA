// https://leetcode.com/problems/binary-tree-preorder-traversal/description/

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
 
     public List<Integer> preorderTraversal(TreeNode root) {
         helper(root);
         return res;
     }
 
     private void helper(TreeNode node) {
         if (node == null) {
             return;
         }
         res.add(node.val);
         helper(node.left);
         helper(node.right);
     }
 }