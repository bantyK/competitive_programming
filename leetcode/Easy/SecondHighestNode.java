package solutions.easy;

import models.TreeNode;

// https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
public class SecondHighestNode {

    public static void main(String[] args) {
        SecondHighestNode obj = new SecondHighestNode();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

//        root.left.left = new TreeNode(5);
//        root.left.right = new TreeNode(7);

//        root.right.left = new TreeNode(5);
//        root.right.right = new TreeNode(7);

        System.out.println(obj.findSecondMinimumValue(root));
    }

    public int findSecondMinimumValue(TreeNode root) {

        if (root.left == null) return -1;


        int left = root.left.val;
        if (left == root.val) {
            left = findSecondMinimumValue(root.left);
        }

        int right = root.right.val;
        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }


        if (left != -1 && right != -1) return Math.min(left, right);

        else return left != -1 ? left : right;
    }


}
