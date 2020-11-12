import input.TreeNode;

import java.util.*;

//1457 https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
public class PseudoPalindromicPath {
    public static void main(String[] args) {
        PseudoPalindromicPath obj = new PseudoPalindromicPath();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(1);

        System.out.println(obj.pseudoPalindromicPaths(root));
    }

    public int pseudoPalindromicPaths(TreeNode root) {
        if (root == null) return 0;
        Map<Integer, Integer> count = new HashMap<>();
        return helper(root, count);
    }

    private int helper(TreeNode root, Map<Integer, Integer> countMap) {
        if (root == null) return 0;

        int countPseudoPalindrome = 0;

        countMap.put(root.val, countMap.getOrDefault(root.val, 0) + 1);


        int fromLeft = helper(root.left, countMap);
        int fromRight = helper(root.right, countMap);

        if (root.left == null && root.right == null && atMostOneOddCount(countMap)) {
            countPseudoPalindrome = 1;
        }

        countPseudoPalindrome += fromLeft + fromRight;

        countMap.put(root.val, countMap.get(root.val) - 1);
        if (countMap.get(root.val) == 0) countMap.remove(root.val);

        return countPseudoPalindrome;
    }

    private boolean atMostOneOddCount(Map<Integer, Integer> count) {
        boolean oddFound = false;
        for (int key : count.keySet()) {
            if (count.get(key) % 2 == 1) {
                if (oddFound) return false;
                else oddFound = true;
            }
        }

        return true;
    }
}

