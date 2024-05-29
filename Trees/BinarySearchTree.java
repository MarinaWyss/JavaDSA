public class BinarySearchTree {

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
    
    public static TreeNode search(TreeNode root, int target) {
        // Return true if the BST includes the target value
        if (root == null) {
            return root;
        }

        // Search the right side if the value is higher than the root
        if (target > root.val) {
            return search(root.right, target);
        }
        // Search the left if it's lower
        else if (target < root.val) {
            return search(root.left, target);
        }
        else {
            return root;
        }
    }

    // Insert a new node and return the root of the BST.
    public TreeNode insert(TreeNode root, int val) {
        // Create a new node if needed
        if (root == null) {
            return new TreeNode(val);
        }
        // Recursively insert into the left subtree if the value is smaller
        if (val > root.val) {
            root.right = insert(root.right, val);
        }
        // Recursively insert into the right subtree if the value is greater
        else if (val < root.val) {
            root.left = insert(root.left, val);
        }
        // If the value already exists in the tree, do nothing
        return root;
    }

    // Create a helper function required for removing when
    // a node has two children
    public TreeNode minValueNode(TreeNode root) {
        TreeNode curr = root;
        // Traverse the left subtree until the leftmost node is reached
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public TreeNode remove(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        // Search for the node with the specified value
        if (val > root.val) {
            root.right = remove(root.right, val);
        }
        else if (val < root.val) {
            root.left = remove(root.left, val);
        }
        else { // Node found

            // If the node has only one child or no child
            // bypass the current node if the node has only one child
            if (root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }

            // If the node has two children
            // replace the node's value with the minimum value from the right subtree
            else {
                TreeNode minNode = minValueNode(root.right);
                root.val = minNode.val;
                // Remove the minimum node from the right subtree
                root.right = remove(root.right, minNode.val);
            }
        }
        return root;
    }     
}
