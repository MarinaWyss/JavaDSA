import java.util.Stack;

public class DepthFirstSearchIterative {
    // Recursion makes use of a stack under the hood. In the iterative version, 
    // we declare our own stacks to perform the same operations.

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
    
    // Time and space: O(n)
    public static void inOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        // Loop as long as there are nodes to be processed
        while (curr != null || !stack.isEmpty()) {
            // Traverse left if we haven't reached a leaf node
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // If we get to a leaf, pop it and traverse right
            else {
                curr = stack.pop();
                System.out.println(curr.val);
                curr = curr.right;
            }
        }
    }

    public static void preOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        // Loop as long as there are nodes to be processed
        while (curr != null || !stack.isEmpty()) {
            // Process the current node if it is not null
            if (curr != null) {
                // Print the value of the current node (pre-order: process node first)
                System.out.println(curr.val);
                // If there is a right child, push it onto the stack to process later
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                // Move to the left child
                curr = curr.left;
            }
            // If the current node is null, pop it
            else {
                // This is the right child of a previously-processed node
                curr = stack.pop();
            }
        }
    }

    public static void postOrderTraversal(TreeNode root) {
        /*
         * In a recursive post-order traversal, the function stack inherently 
         * handles the order of operations (visiting left, right, and then root). 
         * When converting this to an iterative process, we need a way to remember 
         * the state of each node (whether it has been processed or not) and the 
         * order in which nodes should be processed.
         * The first stack (stack) keeps track of the nodes to be processed, and the 
         * second stack (visit) keeps track of whether the node has been visited before. 
         * This helps in determining whether to process the node or to push its children onto the stack.
         */
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        Stack<Boolean> visit = new Stack<>();
        visit.push(false);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            boolean visited = visit.pop();
            if (curr != null) {
                if (visited) {
                        // If the node has been visited, print its value (post-order: process node last)
                    System.out.println(curr.val);
                }
                // If the node has not been visited
                else {
                    // Push the node back onto the stack and mark it as visited
                    stack.push(curr);
                    visit.push(true);
                    // Push the right child onto the stack and mark it as not visited
                    stack.push(curr.right);
                    visit.push(false);
                    // Push the left child onto the stack and mark it as not visited
                    stack.push(curr.left);
                    visit.push(false);
                }
            }
        }
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