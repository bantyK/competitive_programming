import input.TreeNode;

//549 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/
public class LongestConsecutive2 {

    private int maxLen;

    public static void main(String[] args) {
        LongestConsecutive2 obj = new LongestConsecutive2();
    }

    public int longestConsecutive(TreeNode root) {
        maxLen = 0;
        helper(root);
        return maxLen;
    }

    // {dec, inc}
    private int[] helper(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int inc = 1, dec = 1;

        if (root.left != null) {
            if (root.val == root.left.val + 1) dec += left[0];
            if (root.val == root.left.val - 1) inc += left[1];
        }

        if (root.right != null) {
            if (root.val == root.right.val + 1) dec = Math.max(right[0] + 1, dec);
            if (root.val == root.right.val - 1) inc = Math.max(right[1] + 1, inc);
        }

        maxLen = Math.max(maxLen, inc + dec - 1);
        return new int[]{dec, inc};
    }
}
