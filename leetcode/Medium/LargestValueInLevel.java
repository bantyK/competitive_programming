import java.util.*;

// 515 https://leetcode.com/problems/find-largest-value-in-each-tree-row/
public class LargestValueInLevel {
    public static void main(String[] args) {
        LargestValueInLevel obj = new LargestValueInLevel();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);

        root.right.right = new TreeNode(9);

        List<Integer> integers = obj.largestValues(root);
        System.out.println(integers);
    }

    /**
     * DFS Solution. Beats 100%
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        helper(root, 0, results);
        return results;
    }

    private void helper(TreeNode node, int level, List<Integer> results) {
        if (node == null) return;
        if (results.size() <= level) {
            results.add(node.val);
        } else if (results.get(level) < node.val) {
            results.set(level, node.val);
        }

        helper(node.left, level + 1, results);
        helper(node.right, level + 1, results);
    }


    /**
     * BFS Solution
     *
     * @param root
     * @return
     */
    public List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            int maxSoFar = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                maxSoFar = Math.max(current.val, maxSoFar);

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            result.add(maxSoFar);
        }

        return result;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
