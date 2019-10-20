package solutions.medium;

import models.BinaryTreeUtils;
import models.TreeNode;

//https://leetcode.com/problems/insert-into-a-binary-search-tree/
public class InsertIntoBST {
    public static void main(String[] args) {
        InsertIntoBST obj = new InsertIntoBST();
        TreeNode root = null;
        root = obj.insertIntoBST(root, 10);
        root = obj.insertIntoBST(root, 5);
        root = obj.insertIntoBST(root, 15);
        root = obj.insertIntoBST(root, 4);
        root = obj.insertIntoBST(root, 20);

        BinaryTreeUtils.inorderTraversal(root);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        } else if(root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}
