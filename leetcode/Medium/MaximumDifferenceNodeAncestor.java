package solutions.medium;

import models.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/
public class MaximumDifferenceNodeAncestor {
    public static void main(String[] args) {
        MaximumDifferenceNodeAncestor obj = new MaximumDifferenceNodeAncestor();

        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);

        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(0);
        root.left.right = new TreeNode(6);

        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);

        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        System.out.println(obj.maxAncestorDiff(root));

    }

    private int max;
    
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        max = Integer.MIN_VALUE;

        helper(root, root.val, root.val);
        return max;
    }


    // Have to pass max and min both because we are interested in absolute difference.
    // There could be a case where the top levels have higher values and lower level have lower values, in this case we need to pass the maximum
    // in cases where top level have lower values and low level have higher values, in this case we need the min values from top
    private void helper(TreeNode root, int maxSoFar, int minSoFar) {
        if (root == null) return;
        int diffMax = Math.abs(root.val - maxSoFar);
        if (diffMax > max) {
            max = diffMax;
        }
        
        int diffMin = Math.abs(root.val - minSoFar);
        if (diffMin > max) {
            max = diffMin;
        }
        
        maxSoFar = Math.max(maxSoFar, root.val);
        minSoFar = Math.min(minSoFar, root.val);
        
        helper(root.left, maxSoFar,minSoFar);
        helper(root.right, maxSoFar,minSoFar);
    }
}
