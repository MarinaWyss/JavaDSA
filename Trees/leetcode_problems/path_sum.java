// https://leetcode.com/problems/path-sum/description/

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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // If there is no tree, the answer is false.
        if (root == null) {
            return false;
        }

        // If the node is a leaf node, check whether its value
        // equals the remaining target sum
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // Recursively look for a valid path 
        // Subtract value of current node from targetSum first
        boolean leftSum = hasPathSum(root.left, targetSum - root.val);
        boolean rightSum = hasPathSum(root.right, targetSum - root.val);

        // If either subtree has a valid path, a valid path exists
        return leftSum || rightSum;
    }
}

/**
 * Example Tree:
 *      5
 *     / \
 *    4   8
 *   /   / \
 *  11  13  4
 * /  \      \
 * 7    2      1
 *
 * Example Target Sum:
 * targetSum = 22
 * 
 * 1. Initial Call: hasPathSum(root, 22)
 *    - root.val = 5
 *    - targetSum = 22 - 5 = 17
 *    - Check left subtree: hasPathSum(root.left, 17)
 *
 * 2. Node 4: root.left.val = 4
 *    - targetSum = 17 - 4 = 13
 *    - Check left subtree: hasPathSum(root.left.left, 13)
 *
 * 3. Node 11: root.left.left.val = 11
 *    - targetSum = 13 - 11 = 2
 *    - Check left subtree: hasPathSum(root.left.left.left, 2)
 *    - Check right subtree: hasPathSum(root.left.left.right, 2)
 *
 * 4. Node 7 (Leaf): root.left.left.left.val = 7
 *    - targetSum = 2 - 7 = -5
 *    - Not equal to targetSum, return false
 *
 * 5. Node 2 (Leaf): root.left.left.right.val = 2
 *    - targetSum = 2 - 2 = 0
 *    - Equal to targetSum, return true
 *
 * 6. Back to Node 11:
 *    - Left subtree result: false
 *    - Right subtree result: true
 *    - Return true
 *
 * 7. Back to Node 4:
 *    - Left subtree result: true
 *    - Return true
 *
 * 8. Back to Root (Node 5):
 *    - Left subtree result: true
 *    - Check right subtree: hasPathSum(root.right, 17)
 *
 * 9. Node 8: root.right.val = 8
 *    - targetSum = 17 - 8 = 9
 *    - Check left subtree: hasPathSum(root.right.left, 9)
 *    - Check right subtree: hasPathSum(root.right.right, 9)
 *
 * 10. Node 13 (Leaf): root.right.left.val = 13
 *     - targetSum = 9 - 13 = -4
 *     - Not equal to targetSum, return false
 *
 * 11. Node 4: root.right.right.val = 4
 *     - targetSum = 9 - 4 = 5
 *     - Check right subtree: hasPathSum(root.right.right.right, 5)
 *
 * 12. Node 1 (Leaf): root.right.right.right.val = 1
 *     - targetSum = 5 - 1 = 4
 *     - Not equal to targetSum, return false
 *
 * 13. Back to Node 8:
 *     - Left subtree result: false
 *     - Right subtree result: false
 *     - Return false
 *
 * 14. Back to Root (Node 5):
 *     - Right subtree result: false
 *     - Return true
 *
 * The method hasPathSum(root, 22) returns true, indicating a root-to-leaf path 
 * (5 -> 4 -> 11 -> 2) that sums to 22.
 */
