import input.*;

import java.util.*;
//653 https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
public class BSTTwoSum {
    public static void main(String[] args) {
        BSTTwoSum obj = new BSTTwoSum();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);
//
//        root.right.right = new TreeNode(7);

        System.out.println(obj.findTarget(root, 4));
    }

    // faster implementation
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root,root, k);
    }

    public boolean dfs(TreeNode root, TreeNode current, int k) {
        if (current == null) return false;
        return search(root, current, k - current.val) || dfs(root, current.left, k) || dfs(root, current.right, k);
    }

    private boolean search(TreeNode root, TreeNode current, int target) {
        if (root == null) return false;
        if (root.val == target && root != current) return true;
        else if (root.val > target) return search(root.left, current, target);
        else return search(root.right, current, target);
    }

    //Time: O(n), Space O(n)
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> nodes = new ArrayList<>();

        inorder(root, nodes);

        int left = 0;
        int right = nodes.size() - 1;

        while (left < right) {
            int currentSum = nodes.get(left) + nodes.get(right);

            if (currentSum == k) return true;
            else if (currentSum > k) right--;
            else left++;
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> nodes) {
        if (root == null) return;

        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
    }
}
