import input.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

// 1602 https://leetcode.com/problems/find-nearest-right-node-in-binary-tree/
public class NearestNodeToRight {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(10);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(8);


        System.out.println(new NearestNodeToRight().findNearestRightNode(root, 10));
    }

    public TreeNode findNearestRightNode2(TreeNode root, TreeNode node) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.val == node.val) {
                    if (queue.isEmpty() || i == size - 1) {
                        // if there are no node at the right or if the current node is the right most child, this also means
                        // that right child cannot be present
                        return null;
                    } else return queue.poll();
                }
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return null;
    }

    Integer depth;
    TreeNode res;

    public TreeNode findNearestRightNode(TreeNode root, int node) {
        depth = null;
        res = null;
        helper(root, node, 0);
        return res;
    }

    private void helper(TreeNode root, int node, int curDepth) {
        if (root == null) return;
        if (root.val == node) depth = curDepth;
        else if (depth != null && curDepth == depth && res == null) {
            // if the depth field is not null, we are visiting this level again
            // we are traversing the tree in preorder traversal, the nodes in a level will be traversed left to right
            res = root;
        } else {
            helper(root.left, node, curDepth + 1);
            helper(root.right, node, curDepth + 1);
        }

    }
}
