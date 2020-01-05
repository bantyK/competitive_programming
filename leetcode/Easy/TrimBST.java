import java.util.*;

// 699 https://leetcode.com/problems/trim-a-binary-search-tree/
public class TrimBST {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val >= L && root.val <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        } else if (root.val < L) {
            root.left = null;
            return trimBST(root.right, L, R);
        } else {
            root.right = null;
            return trimBST(root.left, L, R);
        }
        return root;
    }
}
