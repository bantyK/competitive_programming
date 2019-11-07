package solutions.easy;

import models.TreeNode;

// https://leetcode.com/problems/sum-of-left-leaves/
public class SumLeftLeaves {
    public static void main(String[] args) {
        SumLeftLeaves obj = new SumLeftLeaves();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.left.left = new TreeNode(4);

        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(2);

        System.out.println(obj.sumOfLeftLeaves(root));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return calculateSum(root, false);
    }

    private int calculateSum(TreeNode root, boolean fromLeft) {
        if (root == null) return 0;

        if (root.left == null && root.right == null && fromLeft) {
            return root.val;
        }

        return calculateSum(root.left, true)
                + calculateSum(root.right, false);
    }
}
