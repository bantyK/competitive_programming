import input.TreeNode;

//1644 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
public class LowestCommonAncestor2 {
    private int count = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(new LowestCommonAncestor2().lowestCommonAncestor(root, 5, 4).val);
        System.out.println(new LowestCommonAncestor2().lowestCommonAncestor(root, 5, 8).val);
        System.out.println(new LowestCommonAncestor2().lowestCommonAncestor(root, 5, 10));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        count = 0;
        TreeNode res = helper(root, p, q);
        return count == 2 ? res : null;
    }

    private TreeNode helper(TreeNode root, int p, int q) {
        if (root == null) return null;

        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);

        if (root.val == p || root.val == q) {
            count++;
            return root;
        }

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}