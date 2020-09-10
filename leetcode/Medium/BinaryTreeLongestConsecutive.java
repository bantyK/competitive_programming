import input.TreeNode;

//298 https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
public class BinaryTreeLongestConsecutive {
    int max = 0;

    public static void main(String[] args) {
        BinaryTreeLongestConsecutive obj = new BinaryTreeLongestConsecutive();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(6);
        root.left.left.left.left.left = new TreeNode(7);


        System.out.println(obj.longestConsecutive(root));
    }

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        max = 0;
        helper(root,0, root.val);
        return max;
    }

    private void helper(TreeNode root, int current, int target) {
        if (root == null) return;
        if (root.val == target) {
            current++;
        } else {
            current = 1;
        }

        max = Math.max(max, current);
        helper(root.left, current, root.val + 1);
        helper(root.right, current, root.val + 1);
    }
}
