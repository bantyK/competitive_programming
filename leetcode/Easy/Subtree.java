package solutions.easy;

import models.TreeNode;

// https://leetcode.com/problems/subtree-of-another-tree/
public class Subtree {
    public static void main(String[] args) {
        Subtree obj = new Subtree();

        TreeNode s = getS();
        TreeNode t = getT();

        System.out.println(
                obj.isSubtree(s, t)
        );
    }

    private static TreeNode getT() {
        TreeNode root = new TreeNode(15);
        /*root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.right.right = new TreeNode(15);
*/
        return root;
    }

    private static TreeNode getS() {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(17);
        root.right.right = new TreeNode(25);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(12);
        root.left.right.right = new TreeNode(15);

        return root;
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) return false;
        if(areNodesSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean areNodesSame(TreeNode s, TreeNode t) {
        if(s == null && t == null) return true;
        if(s == null || t == null) return  false;

        if(s.val != t.val) return false;

        return areNodesSame(s.left, t.left) && areNodesSame(s.right, t.right);
    }
}
