import java.util.*;

// 103 https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class ZigZagLevelTree {
    public static void main(String[] args) {
        ZigZagLevelTree obj = new ZigZagLevelTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        for (List<Integer> list : obj.zigzagLevelOrder(root)) {
            System.out.println(list);
        }

    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> results = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int k = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> treeValues = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                treeValues.add(current.val);

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            if (k % 2 == 1) {
                Collections.reverse(treeValues);
            }
            results.add(treeValues);
            k++;
        }
        return results;
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
