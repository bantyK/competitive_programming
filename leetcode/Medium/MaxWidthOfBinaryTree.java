import input.TreeNode;

import java.util.*;

//662 https://leetcode.com/problems/maximum-width-of-binary-tree/submissions/
public class MaxWidthOfBinaryTree {
    public static void main(String[] args) {
        MaxWidthOfBinaryTree obj = new MaxWidthOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        System.out.println(obj.widthOfBinaryTree(root));
    }

    /// DFS///
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Map<Integer, int[]> levelMap = new HashMap<>();
        dfs(root, levelMap, 0, 0);
        int maxWidth = Integer.MIN_VALUE;
        for (int level : levelMap.keySet()) {
            int[] pair = levelMap.get(level);
            maxWidth = Math.max(maxWidth, pair[1] - pair[0] + 1);
        }

        return maxWidth;
    }

    private void dfs(TreeNode root, Map<Integer, int[]> map, int level, int index) {
        if (root == null) return;

        int[] pair = map.getOrDefault(level, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
        pair[0] = Math.min(index, pair[0]);
        pair[1] = Math.max(index, pair[1]);
        map.put(level, pair);

        dfs(root.left, map, level + 1, 2 * index);
        dfs(root.right, map, level + 1, (2 * index) + 1);
    }


}
