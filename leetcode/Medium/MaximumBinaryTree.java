package solutions.medium;

//https://leetcode.com/problems/maximum-binary-tree/

import models.TreeNode;

import java.util.List;

//https://leetcode.com/problems/maximum-binary-tree/
public class MaximumBinaryTree {
    public static void main(String[] args) {
        MaximumBinaryTree obj = new MaximumBinaryTree();
        TreeNode treeNode = obj.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        obj.inorderTraversal(treeNode);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeUtil(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTreeUtil(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) return null;

        int maxIndex = findMaxIndex(nums, startIndex, endIndex);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = constructMaximumBinaryTreeUtil(nums, startIndex, maxIndex - 1);
        root.right = constructMaximumBinaryTreeUtil(nums, maxIndex + 1, endIndex);
        return root;
    }

    private int findMaxIndex(int[] nums, int startIndex, int endIndex) {
        int maxIndex = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.value + " ");
            inorderTraversal(root.right);
        }
    }
}
