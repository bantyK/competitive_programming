import input.TreeNode;

import java.util.*;

// 99 https://leetcode.com/problems/recover-binary-search-tree/
public class RecoverBST {
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inorder(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }


    private void inorder(TreeNode root) {
        if (root == null)
            return;

        inorder(root.left);

        if (prev != null && prev.val >= root.val) {
            if (first == null) first = prev;
            second = root;
        }

        prev = root;

        inorder(root.right);
    }

    /**
     * Brute force
     *
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        List<Integer> inorder = new LinkedList<>();
        inorderTraversal(root, inorder);
        List<Integer> sorted = new ArrayList<>(inorder);
        Collections.sort(sorted);
        int val1 = 0, val2 = 0;
        for (int i = 0; i < inorder.size(); i++) {
            if (!inorder.get(i).equals(sorted.get(i))) {
                val1 = inorder.get(i);
                val2 = sorted.get(i);
                break;
            }
        }

        traversTree(root, val1, val2);
    }


    private void traversTree(TreeNode root, int val1, int val2) {
        if (root.val == val1) root.val = val2;
        if (root.val == val2) root.val = val1;

        traversTree(root.left, val1, val2);
        traversTree(root.right, val1, val2);
    }

    private void inorderTraversal(TreeNode root, List<Integer> inorder) {
        if (root == null) return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }
}
