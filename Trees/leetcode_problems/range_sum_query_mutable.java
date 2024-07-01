// https://leetcode.com/problems/range-sum-query-mutable/description/

class NumArray {
    private int[] segmentTree;
    private int[] nums;
    private int n;

    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        /* Below, we allocate memory for the segmentTree
        We use 4 * n because for an array of size n the height
        will be log(n) base 2 because it's a balanced binary tree.
        Worst case, this will require 4 * n space to store all the nodes.
        */
        this.segmentTree = new int[4 * n];
        buildTree(0, 0, n - 1);
    }

    public void buildTree(int treeIndex, int low, int high) {
        // Base case: We hit a leaf node
        if (low == high) {
            // Since this is a leaf, the sum is simply the element
            // which we take from nums[low]
            segmentTree[treeIndex] = nums[low];
            return;
        }

        // Find the midpoint and child nodes
        int mid = getMid(low, high);
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);

        // Recursively build left and right subtrees
        buildTree(leftChild, low, mid);
        buildTree(rightChild, mid + 1, high);

        // Merge the results of the left and right subtrees
        segmentTree[treeIndex] = segmentTree[leftChild] + segmentTree[rightChild];
    }
    
    public void update(int index, int val) {
        updateTree(0, 0, n - 1, index, val);
    }

    // treeIndex is used to get sum in the segmentTree
    // arrayIndex is the index in the original array where the update is needed
    private void updateTree(int treeIndex, int low, int high, int arrayIndex, int val) {
        if (low == high) {
            // Left node
            segmentTree[treeIndex] = val;
            nums[arrayIndex] = val;
            return;
        }

        // Find the midpoint and child nodes
        int mid = getMid(low, high);
        int leftChild = getLeftChild(treeIndex);
        int rightChild = getRightChild(treeIndex);

        if (arrayIndex > mid) {
            updateTree(rightChild, mid + 1, high, arrayIndex, val);
        }
        else {
            updateTree(leftChild, low, mid, arrayIndex, val);
        }

        // Merge results
        segmentTree[treeIndex] = segmentTree[leftChild] + segmentTree[rightChild];

    }
    
    public int sumRange(int left, int right) {
        return queryTree(0, 0, n-1, left, right);
    }

    private int queryTree(int treeIndex, int low, int high, int queryLeft, int queryRight) {
    // If the current segment is completely outside the query range
    if (low > queryRight || high < queryLeft) {
        return 0; // It does not contribute to the sum
    }

    // If the current segment is completely inside the query range
    if (queryLeft <= low && high <= queryRight) {
        return segmentTree[treeIndex];
    }

    // If the current segment is partially inside the query range
    // Find the midpoint and child nodes
    int mid = getMid(low, high);
    int leftChild = getLeftChild(treeIndex);
    int rightChild = getRightChild(treeIndex);

    // Query subtrees
    int leftSum = queryTree(leftChild, low, mid, queryLeft, queryRight);
    int rightSum = queryTree(rightChild, mid + 1, high, queryLeft, queryRight);

    return leftSum + rightSum;
    }

    private int getMid(int low, int high) {
        return (low + high) / 2;
    }

    /* Based on the properties of binary trees, 
    the root of the tree is at index 0.
    For any node at index i:
        The left child is at index 2 * i + 1.
        The right child is at index 2 * i + 2.
    */
    private int getLeftChild(int treeIndex) {
        return 2 * treeIndex + 1;
    }

    private int getRightChild(int treeIndex) {
        return 2 * treeIndex + 2;
    }
}   

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
