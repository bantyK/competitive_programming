import input.TreeNode;

// 124 https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class BinaryTreeMaxPathSum {
    private int maxSum = 0;

    public static void main(String[] args) {
        BinaryTreeMaxPathSum obj = new BinaryTreeMaxPathSum();
//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(6);
//
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(3);
//        root.right.right.left = new TreeNode(7);

//        TreeNode root = new TreeNode(-10);
//        root.left = new TreeNode(-9);
//        root.right = new TreeNode(10);
//        root.right.left = new TreeNode(1);
//        root.right.right = new TreeNode(-1);

//        TreeNode root = new TreeNode(10);
//        root.right = new TreeNode(7);
//
//        root.right.left = new TreeNode(1);
//        root.right.right = new TreeNode(-6);
//
//        root.right.right.left = new TreeNode(40);
//        root.right.right.right = new TreeNode(-9);

        TreeNode root = new TreeNode(-2);
        root.left = new TreeNode(-1);
//        root.right = new TreeNode(-2);

        System.out.println(obj.maxSumPath(root));
    }


    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        int val = recurse(root);
        return Math.max(val, maxSum);
    }

    public int recurse(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;

        int leftSum = recurse(root.left);
        int rightSum = recurse(root.right);

        int maxLeftRight = Math.max(leftSum, rightSum);
        maxSum = Math.max(maxLeftRight, maxSum);

        int rootLeftRight = 0;
        int rootLeft = root.val;
        int rootRight = root.val;

        if (leftSum != Integer.MIN_VALUE) {
            rootLeft += leftSum;
            rootLeftRight += rootLeft;
        }
        if (rightSum != Integer.MIN_VALUE) {
            rootRight += rightSum;
            rootLeftRight += rootRight;
        }

        rootLeftRight -= root.val;

        maxSum = Math.max(maxSum, Math.max(rootLeftRight, Math.max(rootLeft, rootRight)));

        // return either the sum of root + right OR root + left OR only root
        return Math.max(root.val, Math.max(rootRight, rootLeft));
    }

    /**
     * Cleaner code
     * @param root
     * @return
     */
    public int maxSumPath(TreeNode root) {
        if (root == null) return 0;
        int left = maxSumPath(root.left);
        int right = maxSumPath(root.right);

        int maxLeftRight = Math.max(left, right);
        int maxSinglePath = Math.max(maxLeftRight + root.val, root.val);
        int maxAll = Math.max(maxSinglePath, left + right + root.val);
        maxSum = Math.max(maxSum, maxAll);
        return maxSinglePath;
    }
}
