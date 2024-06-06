/* 
Q: Determine if a path exists from the root of the tree to a leaf node. 
It may not contain any zeroes.
Return the path.
*/

import java.util.ArrayList;

public class Backtracking {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    
        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
    
    public boolean leafPath(TreeNode root, ArrayList<Integer> path) {
        // If the root doesn't exist or is a zero, no path exists
        if (root == null || root.val == 0) {
            return false;
        }

        // Add current root to path
        path.add(root.val);

        // If we reach a leaf, return true
        if (root.left == null && root.right == null) {
            return true;
        }

        // recursively try to find a path on the left and right
        if (leafPath(root.left, path)) {
            return true;
        }
        if (leafPath(root.right, path)) {
            return true;
        }

        // If neither subtree contains a valid path, remove the current node from the 
        // path (backtrack) and return false since no path to a leaf was 
        // found from the current node.
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Backtracking bt = new Backtracking();

        // Test case where a valid path exists
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        ArrayList<Integer> path1 = new ArrayList<>();
        if (bt.leafPath(root1, path1)) {
            System.out.println("Test 1: A path to a leaf exists: " + path1);
        } else {
            System.out.println("Test 1: No valid path to a leaf exists.");
        }

        // Test case where no valid path exists
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(0);
        root2.right.right = new TreeNode(0);

        ArrayList<Integer> path2 = new ArrayList<>();
        if (bt.leafPath(root2, path2)) {
            System.out.println("Test 2: A path to a leaf exists: " + path2);
        } else {
            System.out.println("Test 2: No valid path to a leaf exists.");
        }
    }
}