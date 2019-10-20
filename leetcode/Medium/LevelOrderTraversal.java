package solutions.medium;

import models.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-level-order-traversal/
public class LevelOrderTraversal {
    public static void main(String[] args) {
        LevelOrderTraversal obj = new LevelOrderTraversal();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        List<List<Integer>> result = obj.levelOrder(root);
        System.out.println(result);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        queue.offer(root);

        while(!queue.isEmpty()) {
            int n = queue.size();

            List<Integer> nodes = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                TreeNode temp = queue.poll();
                nodes.add(temp.val);

                if(temp.left != null) queue.add(temp.left);
                if(temp.right != null) queue.add(temp.right);
            }

            result.add(nodes);
        }

        return result;
    }
}
