import input.TreeNode;

import java.util.*;

// 1676 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
public class LCA4 {
    public static void main(String[] args) {

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        // Going from top to bottom, if we find any node which is present in nodes array, we can directly return that node as LCA
        // This is possible only because of 2 reasons:
        // 1. All nodes are unique. The first root that we found which is also present in nodes, has to be the LCA. The nodes below that wont have the value of that root.
        // 2. We can directly return that root because, it is given in the question that all the nodes exist in the tree. If that was not the case, we would have to check the nodes below that
        // for other node values and mark them accordingly

        Set<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) values.add(node.val);

        return helper(root, values);
    }

    private TreeNode helper(TreeNode root, Set<Integer> values) {
        if (root == null) return root;

        if (values.contains(root.val)) {
            return root;
        }

        TreeNode left = helper(root.left, values);
        TreeNode right = helper(root.right, values);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

}