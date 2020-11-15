//#938 https://leetcode.com/problems/range-sum-of-bst/

public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        int sum = (root.val >= low && root.val <= high) ? root.val : 0;

        if(root.val >= low && root.val <= high) {
            sum += rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else if(root.val >= high) {
            sum += rangeSumBST(root.left, low, high);
        } else if(root.val <= low) {
            sum += rangeSumBST(root.right, low, high);
        }
        return sum;
    }
}