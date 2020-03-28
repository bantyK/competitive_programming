import input.TreeNode;

//979 https://leetcode.com/problems/distribute-coins-in-binary-tree/
public class DistributeCoins {
    int ans = 0;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left); // moves required in the left subtree
        int right = dfs(root.right); // moves required in the right subtree
        ans += Math.abs(left) + Math.abs(right);

        // moves required from the root = moves req in left + moves req in right -1(because root needs to have atleast 1 coin)
        return root.val + left + right - 1;
    }
}
