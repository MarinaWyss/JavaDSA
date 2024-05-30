public class DepthFirstSearch {

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
    
    // Prints the values in sorted order (for a BST)
    public static void inOrderTraversal(TreeNode root) {
        // Base case where we're done with a sub-tree
        if (root == null) {
            return;
        }

        // First, recurse left to get smaller values than the current node
        inOrderTraversal(root.left);
        // Record the current value
        System.out.println(root.val);
        // Then recurse right to get values larger than current but smaller than the parent
        inOrderTraversal(root.right);
    }

    // Biases towards giving data near the root faster
    // Unique representation of a tree if it is a BST
    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // Record current value first
        System.out.println(root.val);
        // Recurse left
        preOrderTraversal(root.left);
        // Recurse right
        preOrderTraversal(root.right);
    }

    // Biases towards giving data near the leaves faster
    // Unique representation of a tree if it is a BST
    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        // Recurse left
        postOrderTraversal(root.left);
        // Recurse right
        postOrderTraversal(root.right);
        // Record current value last
        System.out.println(root.val);
    }

    public static void main(String[] args) {
        // Creating a sample binary search tree:
        //        3
        //       / \
        //      1   4
        //       \
        //        2
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println("In-Order Traversal:");
        inOrderTraversal(root);

        System.out.println("\nPre-Order Traversal:");
        preOrderTraversal(root);

        System.out.println("\nPost-Order Traversal:");
        postOrderTraversal(root);
    }
}