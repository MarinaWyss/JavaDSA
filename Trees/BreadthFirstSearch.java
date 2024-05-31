import java.util.Deque;
import java.util.ArrayDeque;


public class BreadthFirstSearch {

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
    
    public static void breadthFirstSearch(TreeNode root) {
        // Create a queue to store next elements to traverse
        Deque<TreeNode> queue = new ArrayDeque<TreeNode>();


        if (root != null) {
            queue.add(root);
        }

        int level = 0;
        // As long as the queue is not empty, we remove nodes present in 
        // the queue (all the nodes on a given level) and add its children (which
        // are the nodes on the next level). 
        // Because of FIFO, we ensure we visit nodes from left to right.
        // The queue is empty once we visit all the nodes.
        while (!queue.isEmpty()) {
            System.out.print("Processing level " + level + ": ");
            int levelSize = queue.size(); // Capture the size of the queue at the start of the level
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.removeFirst(); // Get left-most node
                System.out.print(curr.val + " ");
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            level++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        breadthFirstSearch(root);
    }
}