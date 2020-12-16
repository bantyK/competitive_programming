import input.TreeNode;

//865 https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
public class DeepestNodeSubTree {
    public static void main(String[] args) {

        DeepestNodeSubTree obj = new DeepestNodeSubTree();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(obj.subtreeWithAllDeepest(root).val);

    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return helper(root).node;
    }

    private Node helper(TreeNode root) {
        if (root == null) return new Node(0, null);
        Node left = helper(root.left);
        Node right = helper(root.right);

        int rootHeight = Math.max(left.height, right.height) + 1;
        if (left.height > right.height) {
            return new Node(rootHeight, left.node);
        } else if (right.height > left.height) {
            return new Node(rootHeight, right.node);
        } else {
            return new Node(rootHeight, root);
        }
    }

    public static class Node {
        int height;
        TreeNode node;

        public Node(int height, TreeNode node) {
            this.height = height;
            this.node = node;
        }
    }

}