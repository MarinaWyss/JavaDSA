// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preorderStart = 0;
        int inorderStart = 0;
        int inorderEnd = inorder.length - 1;
        return helper(preorder, inorder, preorderStart, inorderStart, inorderEnd);
    }

    public TreeNode helper(int[] preorder, int[] inorder, int preorderStart, int inorderStart, int inorderEnd) {

        // Base case: if there are no elements to construct the tree
        if (preorderStart > preorder.length-1 || inorderStart > inorderEnd){
            return null;
        }
        
        // First, identify the root. This is the first element of the preorder array.
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);

        // Locate index of the root in the inorder array
        int inorderRootIndex = inorderStart;
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == rootVal) {
                inorderRootIndex = i;
            }
        }

        // Recursively build left and right sub-trees
        // First, find the size of the left sub-tree. This is the number
        // of elements to the left of the root in the inorder array
        int leftTreeSize = inorderRootIndex - inorderStart;

        // Pass slices (start and end indices) of preorder and inorder arrays to recursively build the tree.
        // preorderStart + 1: After the first element of the preorder array is used to construct the root node, move onto the next element by incrementing the index by 1.
        // inorderRootIndex - 1: inorderRootIndex is the index of the root in the inorder array, so the left tree in the inorder array is from 0 to inorderRootIndex - 1.
        root.left = helper(preorder, inorder, preorderStart + 1, inorderStart, inorderRootIndex - 1);

        // preorderStart + leftTreeSize + 1: The right subtree in the preorder array will start from preorderStart + length of the left subtree.
        // inorderRootIndex + 1: inorderRootIndex is the index of the root in the inorder array, the right tree in the inorder starts from inorderRootIndex + 1 to the end.
        root.right = helper(preorder, inorder, preorderStart + leftTreeSize + 1, inorderRootIndex + 1, inorderEnd);
        return root;
    }
}

/** EXAMPLE
 * Input:
 * int[] preorder = {3, 9, 20, 15, 7};
 * int[] inorder = {9, 3, 15, 20, 7};
 *
 * Step-by-Step Execution:
 *
 * 1. Initial Call:
 *    helper(preorder, inorder, 0, 0, inorder.length - 1)
 *    - preorderStart = 0
 *    - inorderStart = 0
 *    - inorderEnd = 4
 *
 * 2. First Call to Helper:
 *    - rootVal = preorder[0] = 3
 *    - Create TreeNode with value 3.
 *    - Find 3 in inorder array, index 1.
 *    - leftTreeSize = inorderRootIndex - inorderStart = 1 - 0 = 1
 *
 * 3. Construct Left Subtree of 3:
 *    helper(preorder, inorder, 1, 0, 0)
 *    - preorderStart = 1
 *    - inorderStart = 0
 *    - inorderEnd = 0
 *
 * 4. Second Call to Helper (Left Subtree of 3):
 *    - rootVal = preorder[1] = 9
 *    - Create TreeNode with value 9.
 *    - Find 9 in inorder array, index 0.
 *    - leftTreeSize = inorderRootIndex - inorderStart = 0 - 0 = 0
 *
 * 5. Construct Left Subtree of 9:
 *    helper(preorder, inorder, 2, 0, -1)
 *    - preorderStart = 2
 *    - inorderStart = 0
 *    - inorderEnd = -1
 *    - Base case: return null.
 *
 * 6. Construct Right Subtree of 9:
 *    helper(preorder, inorder, 2, 1, 0)
 *    - preorderStart = 2
 *    - inorderStart = 1
 *    - inorderEnd = 0
 *    - Base case: return null.
 *
 *    - Now, 9 is a leaf node.
 *
 * 7. Return to First Call, Construct Right Subtree of 3:
 *    helper(preorder, inorder, 2, 2, 4)
 *    - preorderStart = 2
 *    - inorderStart = 2
 *    - inorderEnd = 4
 *
 * 8. Third Call to Helper (Right Subtree of 3):
 *    - rootVal = preorder[2] = 20
 *    - Create TreeNode with value 20.
 *    - Find 20 in inorder array, index 3.
 *    - leftTreeSize = inorderRootIndex - inorderStart = 3 - 2 = 1
 *
 * 9. Construct Left Subtree of 20:
 *    helper(preorder, inorder, 3, 2, 2)
 *    - preorderStart = 3
 *    - inorderStart = 2
 *    - inorderEnd = 2
 *
 * 10. Fourth Call to Helper (Left Subtree of 20):
 *     - rootVal = preorder[3] = 15
 *     - Create TreeNode with value 15.
 *     - Find 15 in inorder array, index 2.
 *     - leftTreeSize = inorderRootIndex - inorderStart = 2 - 2 = 0
 *
 * 11. Construct Left Subtree of 15:
 *     helper(preorder, inorder, 4, 2, 1)
 *     - preorderStart = 4
 *     - inorderStart = 2
 *     - inorderEnd = 1
 *     - Base case: return null.
 *
 * 12. Construct Right Subtree of 15:
 *     helper(preorder, inorder, 4, 3, 2)
 *     - preorderStart = 4
 *     - inorderStart = 3
 *     - inorderEnd = 2
 *     - Base case: return null.
 *
 *     - Now, 15 is a leaf node.
 *
 * 13. Return to Third Call, Construct Right Subtree of 20:
 *     helper(preorder, inorder, 4, 4, 4)
 *     - preorderStart = 4
 *     - inorderStart = 4
 *     - inorderEnd = 4
 *
 * 14. Fifth Call to Helper (Right Subtree of 20):
 *     - rootVal = preorder[4] = 7
 *     - Create TreeNode with value 7.
 *     - Find 7 in inorder array, index 4.
 *     - leftTreeSize = inorderRootIndex - inorderStart = 4 - 4 = 0
 *
 * 15. Construct Left Subtree of 7:
 *     helper(preorder, inorder, 5, 4, 3)
 *     - preorderStart = 5
 *     - inorderStart = 4
 *     - inorderEnd = 3
 *     - Base case: return null.
 *
 * 16. Construct Right Subtree of 7:
 *     helper(preorder, inorder, 5, 5, 4)
 *     - preorderStart = 5
 *     - inorderStart = 5
 *     - inorderEnd = 4
 *     - Base case: return null.
 *
 *     - Now, 7 is a leaf node.
 *
 * Final Tree Structure:
 *        3
 *       / \
 *      9   20
 *         /  \
 *        15   7
 */
