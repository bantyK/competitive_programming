import input.TreeNode;

import java.util.*;

// 173 https://leetcode.com/problems/binary-search-tree-iterator/
public class BSTIterator {
    List<TreeNode> path = new ArrayList<>();

    public static void main(String[] args) {
        BSTIterator obj = new BSTIterator();
    }

    /**
     * Find the next smallest element, stop when a node does not have a left child
     *
     * @param root
     * @param path
     */
    public void dfs(TreeNode root, List<TreeNode> path) {
        if (root == null) return;
        path.add(root);
        dfs(root.left, path);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode lastElement = path.remove(path.size() - 1);
        dfs(lastElement.right, path);
        return lastElement.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return path.size() > 0;
    }
}
