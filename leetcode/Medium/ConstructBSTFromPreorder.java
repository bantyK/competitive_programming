package solutions.medium;

import models.TreeNode;
import solutions.util.BinaryTreeUtils;

// https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/submissions/
public class ConstructBSTFromPreorder {
    public static void main(String[] args) {
        ConstructBSTFromPreorder obj = new ConstructBSTFromPreorder();
        TreeNode root = obj.bstFromPreorder(new int[]{10, 5, 2, 8, 15, 12, 20});

        BinaryTreeUtils.inorderTraversal(root);
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return constructTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode constructTree(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(preorder[start]);
        }

        int rootData = preorder[start];
        TreeNode root = new TreeNode(rootData);

        int leftTreeStartIndex = start + 1;
        int leftTreeEndIndex = leftTreeStartIndex;

        while (leftTreeEndIndex <= end && preorder[leftTreeEndIndex] < rootData) {
            leftTreeEndIndex++;
        }

        root.left = constructTree(preorder, leftTreeStartIndex, leftTreeEndIndex - 1);
        root.right = constructTree(preorder, leftTreeEndIndex, end);

        return root;
    }
}
