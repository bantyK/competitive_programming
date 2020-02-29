import input.TreeNode;

import java.util.*;

//1261 https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
public class FindElements {

    Set<Integer> elements;

    public FindElements(TreeNode root) {
        elements = new HashSet<>();
        recoverTree(root, 0, elements);
    }

    private void recoverTree(TreeNode root, int val, Set<Integer> set) {
        if(root == null) {
            return;
        }
        root.val = val;
        set.add(val);
        if(root.left != null) {
            recoverTree(root.left, 2 * val + 1, set);
        }

        if(root.right != null) {
            recoverTree(root.right, 2 * val + 2, set);
        }
    }

    public boolean find(int target) {
        return elements.contains(target);
    }
}