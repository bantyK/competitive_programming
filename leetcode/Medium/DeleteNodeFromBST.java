package solutions.medium;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

// https://leetcode.com/problems/delete-node-in-a-bst/
public class DeleteNodeFromBST {
    public static void main(String[] args) {
        DeleteNodeFromBST obj = new DeleteNodeFromBST();
        TreeNode root = BinaryTreeUtils.createTreeFromArray(new int[]{5, 3, 6, 2, 4, 7, 1});
//        BinaryTreeUtils.inorderTraversal(root);
        root = obj.deleteNode(root, 3);
        BinaryTreeUtils.inorderTraversal(root);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val > key) root.left = deleteNode(root.left, key);
        else if (root.val < key) root.right = deleteNode(root.right, key);

        else {
            // The node to delete is a leaf node.
            if (root.left == null && root.right == null) {
                root = null;
            }
            // The node to delete has only one child
            else if (root.left == null) {
                TreeNode temp = root;
                root = temp.right;
                temp = null;
            } else if (root.right == null) {
                TreeNode temp = root;
                root = root.left;
                temp = null;
            }
            // The node to delete has 2 children
            else {
                TreeNode temp = findMin(root.right);
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        }

        return root;
    }

    private TreeNode findMin(TreeNode root) {
        if (root.left == null) return root;
        else return findMin(root.left);
    }

}
