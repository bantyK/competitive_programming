package solutions.easy;

import models.TreeNode;

import java.util.*;

// 1022 https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
public class SumOfRootToLeaf {
    public static void main(String[] args) {
        SumOfRootToLeaf obj = new SumOfRootToLeaf();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        int i = obj.sumRootToLeaf(root);
        System.out.println(i);
    }

    public int sumRootToLeaf(TreeNode root) {
        List<String> numbers = new ArrayList<>();
        getBinaryNumbers(root, "", numbers);
        int result = 0;

        for(String s : numbers) {
            result += Integer.parseInt(s, 2);
        }

        return result;
    }

    private void getBinaryNumbers(TreeNode root, String s, List<String> numbers) {
        if(root == null) return;

        s += root.val;
        if(root.left == null && root.right == null) {
            numbers.add(s);
            return;
        }

        getBinaryNumbers(root.left, s, numbers);
        getBinaryNumbers(root.right, s, numbers);
    }

}
