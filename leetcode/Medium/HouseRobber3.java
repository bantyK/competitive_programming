import input.TreeNode;

import java.util.*;

//337 https://leetcode.com/problems/house-robber-iii/
public class HouseRobber3 {
    public static void main(String[] args) {
        HouseRobber3 obj = new HouseRobber3();

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        System.out.println(obj.rob(root));

    }

    public int rob(TreeNode root) {
        Map<TreeNode, Integer> cache = new HashMap<>();
        return rob(root, cache);
    }


    public int rob(TreeNode root, Map<TreeNode, Integer> cache) {
        if (root == null) return 0;

        if (cache.containsKey(root)) return cache.get(root);

        // this calculate the root's left and right child, since we have not considered root, we will consider its left and right child
        int withoutRoot = rob(root.left, cache) + rob(root.right, cache);

        // this calculates the sum of grandchildren of root. We can't take the children values because we have taken root's value.
        int total = 0;
        if (root.left != null) {
            total += rob(root.left.left, cache) + rob(root.left.right, cache);
        }
        if (root.right != null) {
            total += rob(root.right.left, cache) + rob(root.right.right, cache);
        }

        int withRoot = root.val + total;

        cache.put(root, Math.max(withoutRoot, withRoot));

        return cache.get(root);
    }

}
