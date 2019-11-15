package solutions.easy;

import java.util.*;
import models.TreeNode;


// 112. https://leetcode.com/problems/path-sum/
public class HasPathSum {
    public static void main(String[] args) {
        HasPathSum obj = new HasPathSum();

        TreeNode root = new TreeNode(-2);
        root.left = new TreeNode(4);
        root.right = new TreeNode(-3);

        System.out.println(obj.hasPathSum(root, -5));
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.val == sum && root.left == null && root.right == null) return true;
        else return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum-root.val);
    }
}
