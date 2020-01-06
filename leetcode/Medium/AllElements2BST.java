import java.util.*;

// 1305 https://leetcode.com/problems/all-elements-in-two-binary-search-trees/
public class AllElements2BST {

    /**
     * Brute Force
     *
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> values1 = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();

        inorder(root1, values1);
        inorder(root2, values2);

        if (root1 == null) return values2;
        else if (root2 == null) return values1;
        List<Integer> result = new ArrayList<Integer>();

        int index1 = 0, index2 = 0;

        while (index1 < values1.size() && index2 < values2.size()) {
            if (values1.get(index1) < values2.get(index2)) {
                result.add(values1.get(index1));
                index1++;
            } else {
                result.add(values2.get(index2));
                index2++;
            }
        }

        while (index1 < values1.size()) {
            result.add(values1.get(index1));
            index1++;
        }

        while (index2 < values2.size()) {
            result.add(values2.get(index2));
            index2++;
        }

        return result;
    }

    private void inorder(TreeNode root, List<Integer> values) {
        if (root != null) {
            inorder(root.left, values);
            values.add(root.val);
            inorder(root.right, values);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
