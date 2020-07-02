package solutions.easy;

import models.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
public class BottomUpLevelOrderTraversal {
    public static void main(String[] args) {
        BottomUpLevelOrderTraversal obj = new BottomUpLevelOrderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        for(List<Integer> l : obj.levelOrderBottom(root)) {
            System.out.println(l);
        }
    }

    // BFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null) return null;
        queue.offer(root);
        List<Integer> list;
        while (!queue.isEmpty()) {
            int n = queue.size();
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                list.add(current.val);

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            res.addFirst(list);
        }

        return res;
    }

    //DFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> levelMap = new TreeMap<>();
        dfs(root, 0, levelMap);

        for (int key : levelMap.keySet()) {
            res.addFirst(levelMap.get(key));
        }

        return res;
    }

    private void dfs(TreeNode root, int level, Map<Integer, List<Integer>> levelMap) {
        if (root == null) return;

        levelMap.putIfAbsent(level, new ArrayList<>());
        levelMap.get(level).add(root.val);

        dfs(root.left, level + 1, levelMap);
        dfs(root.right, level + 1, levelMap);
    }
}
