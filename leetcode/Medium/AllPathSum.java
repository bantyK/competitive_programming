import java.util.*;
// 113 https://leetcode.com/problems/path-sum-ii/
public class AllPathSum {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, sum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode root, int sum, List<Integer> currentList, List<List<Integer>> allLists) {
        if (root.left == null && root.right == null && sum == root.val) {
            allLists.add(new ArrayList<>(currentList));
            allLists.get(allLists.size() - 1).add(root.val);
            return;
        }

        currentList.add(root.val);
        int remaining = sum - root.val;
        if (root.left != null) {
            dfs(root.left, remaining, currentList, allLists);
        }

        if (root.right != null) {
            dfs(root.right, remaining, currentList, allLists);
        }

        currentList.remove(currentList.size() - 1);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
