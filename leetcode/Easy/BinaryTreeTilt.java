import input.TreeNode;

//563 https://leetcode.com/problems/binary-tree-tilt/¬
public class BinaryTreeTilt {

    public int findTilt(TreeNode root) {
        return helper(root)[1];
    }

    //return array of {sum, tilt}
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }


        int[] left = helper(root.left);
        int[] right = helper(root.right);

        int sumAtThisNode = left[0] + right[0] + root.val;
        int tiltAtThisNode = left[1] + right[1] + Math.abs(left[0] - right[0]);

        return new int[]{sumAtThisNode, tiltAtThisNode};
    }
}
