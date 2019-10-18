package solutions.medium;

import models.BinaryTreeUtils;
import models.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/maximum-binary-tree-ii/
public class MaximumBinaryTree2 {
    public static void main(String[] args) {
        MaximumBinaryTree2 obj = new MaximumBinaryTree2();
        TreeNode input = new TreeNode(4);
        input.left = new TreeNode(1);
        input.right = new TreeNode(3);
        input.right.left = new TreeNode(2);

        TreeNode root = obj.insertIntoMaxTree(input, 5);
        BinaryTreeUtils.inorderTraversal(root);
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = root;
        TreeNode temp = root;
        while (temp.val > val) {
            parent = temp;
            temp = temp.right;
        }

        TreeNode node = new TreeNode(val);
        node.left = parent.right;
        parent.right = node;

        return root;
    }

    // this solution is more intuitive and inspired from MaximumBinaryTree.java
    // but it is more time consuming
    public TreeNode insertIntoMaxTree_2(TreeNode root, int val) {
        List<Integer> values = new ArrayList<>();
        inorderArraySequence(root, values);
        int[] nums = new int[values.size() + 1];
        int i;
        for (i = 0; i < values.size(); i++) {
            nums[i] = values.get(i);
        }
        nums[i] = val;

        return constructMaximumBinaryTree(nums);
    }

    public void inorderArraySequence(TreeNode root, List<Integer> values) {
        if (root != null) {
            inorderArraySequence(root.left, values);
            values.add(root.val);
            inorderArraySequence(root.right, values);
        }
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
}
