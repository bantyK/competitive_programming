package solutions.medium;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

// https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
public class BinaryGreaterSearchTree {
    public static void main(String[] args) {
        BinaryGreaterSearchTree obj = new BinaryGreaterSearchTree();

        TreeNode root = new TreeNode(4);

        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);

        BinaryTreeUtils.inorderTraversal(root);

        root = obj.bstToGst(root);
        System.out.println();

        BinaryTreeUtils.inorderTraversal(root);


    }

    public TreeNode bstToGst(TreeNode root) {
        recBstToGst(root, 0);
        return root;
    }

    private int recBstToGst(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        int result = recBstToGst(root.right, sum);
        root.val += result;
        return recBstToGst(root.left, root.val);
    }

}
