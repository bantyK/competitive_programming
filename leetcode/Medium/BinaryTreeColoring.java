// 1145 https://leetcode.com/problems/binary-tree-coloring-game/
public class BinaryTreeColoring {
    public static void main(String[] args) {
        BinaryTreeColoring obj = new BinaryTreeColoring();
    }

    /**
     * The highest number of nodes that the second player can choose can come only if second player
     * chooses the neighbouring nodes of the node chosen by first player.
     * <p>
     * Neighbouring nodes are: left child, right child and parent
     * <p>
     * If second choose the parent, first player can only choose all the nodes which are below it
     * itself and the count of its right and left child.
     * <p>
     * If the maximum of all these options, greater then n/2, meaning player 2 can color more than half of
     * the nodes and hence he can win.
     * <p>
     * Refer: https://www.youtube.com/watch?v=5XDmdBJtuQc
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = findNode(root, x);
        int leftNodeCount = countNode(node.left);
        int rightNodeCount = countNode(node.right);

        // left, right and the node itself
        int parentNodeCount = n - (leftNodeCount + rightNodeCount) - 1;

        int max = Math.max(parentNodeCount, Math.max(leftNodeCount, rightNodeCount));

        return max > n / 2;
    }

    private int countNode(TreeNode root) {
        if (root == null) return 0;

        return countNode(root.left) + countNode(root.right) + 1;
    }

    private TreeNode findNode(TreeNode root, int x) {
        if (root == null) return null;
        if (root.val == x) return root;
        TreeNode left = findNode(root.left, x);

        if (left != null) return left;
        return findNode(root.right, x);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
