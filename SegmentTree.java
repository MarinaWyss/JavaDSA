public class SegmentTree {
    int sum;  // Used to keep track of the sum at each node
    SegmentTree left, right;  // Pointers to left and right children
    int L, R;  // At any given node, L and R denote the boundaries of the contained range

    public SegmentTree(int sum, int L, int R) {
        this.sum = sum;
        this.left = null;
        this.right = null;
        this.L = L;
        this.R = R;
    }

    // Recursively build the tree
    // O(n)
    public static SegmentTree build(int[] nums, int L, int R) {
        // Base case: We've exhausted the range
        if (L == R) {
            // Create a node
            return new SegmentTree(nums[L], L, R);
        }

        // At each level, we calculate the midpoint M, which splits the current range in half
        int M = (L + R) / 2;
        // Start with a temp root node with a sum of 0
        SegmentTree root = new SegmentTree(0, L, R);
        // Recursively calculate the sum at each node and return the ultimate sum.
        // The left sub-tree has indices L, M
        root.left = SegmentTree.build(nums, L, M);
        // The right sub-tree has indices M+1, R.
        root.right =  SegmentTree.build(nums, M + 1, R);
        root.sum = root.left.sum + root.right.sum;
        return root;
    }

    // O(logn)
    public void update(int index, int val) {
        // Base case: We have reached a leaf node so do the update
        if (L == R) {
            this.sum = val;
            return;
        }

        // Find the midpoint
        int M = (L + R) / 2; 
        // If the midpoint is smaller than the index, recursively go down right subtree
        if (M < index) {
            this.right.update(index, val);
        }
        // Otherwise, recursively go down left subtree
        else {
            this.left.update(index, val);
        }
        this.sum = this.right.sum + this.left.sum;
    }

    // O(logn)
    public int rangeQuery(int L, int R) {
        // If the current node's range matches the query range, return the sum
        if (L == this.L && R == this.R) {
            return this.sum;
        }

        int M = (this.L + this.R) / 2;  // Get the mid value
        // If the query range is completely within the right subtree, query the right subtree
        if (L > M) {
            return this.right.rangeQuery(L, R);
        // If the query range is completely within the left subtree, query the left subtree
        } else if (R <= M) {
            return this.left.rangeQuery(L, R);
        // If the query range spans both subtrees, query both subtrees and combine the results
        } else {
            return (this.left.rangeQuery(L, M) +
                    this.right.rangeQuery(M + 1, R));
        }
    }

}
