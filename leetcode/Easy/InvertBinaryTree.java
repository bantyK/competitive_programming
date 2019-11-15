package solutions.easy;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

// 226 https://leetcode.com/problems/invert-binary-tree/
public class InvertBinaryTree {
    public static void main(String[] args) {
        InvertBinaryTree obj = new InvertBinaryTree();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode res = obj.invertTree(root);
        BinaryTreeUtils.inorderTraversal(res);
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        invertTree(root.right);
        invertTree(root.left);

        return root;
    }
}
