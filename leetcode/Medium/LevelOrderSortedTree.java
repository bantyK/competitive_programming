import input.TreeNode;

import java.util.*;

public class LevelOrderSortedTree {
    public static void main(String[] args) {
        LevelOrderSortedTree obj = new LevelOrderSortedTree();
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);

        System.out.println(findNode(root, 12));
    }

    private static boolean findNode(TreeNode root, int target) {
        int level = findLevel(root, target);
        System.out.println(level);
        int low = 0;
        int high = (int) Math.pow(2, level) - 1;
        while (low <= high) {
            TreeNode curr = root;
            int mid = (low + high) / 2;
            String path = generateGrayCode(mid, level);
            int i = 0;
            while (i < path.length()) {
                char dir = path.charAt(i);
                if (dir == '1') {
                    if (curr != null) {
                        curr = curr.right;
                    }
                } else {
                    if (curr != null) {
                        curr = curr.left;
                    }
                }

                i++;
            }
            if (curr == null) {
                high = mid - 1;
            } else if (curr.val == target) {
                return true;
            } else if (curr.val > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;

    }

    private static int findLevel(TreeNode root, int target) {
        int level = 0;
        while (root.val < target && root.left != null) {
            root = root.left;
            level += 1;
        }
        return level;
    }

    private static String generateGrayCode(int num, int level) {
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            builder.insert(0, num % 2);
            num /= 2;
        }
        int len = builder.length();

        for (int i = 0; i < level - len; i++) {
            builder.insert(0, "0");
        }

        return builder.toString();
    }
}
