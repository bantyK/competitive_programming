import java.util.*;

import com.sun.source.tree.Tree;
import input.*;

//1379 https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
public class GetTargetCopy {
    public static void main(String[] args) {
        GetTargetCopy obj = new GetTargetCopy();

        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(4);
        final TreeNode target = new TreeNode(9);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(19);

        root.right.left.left = new TreeNode(4);
        root.right.left.right = target;

        System.out.println(obj.getTargetCopy(root, root, target).val);
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        List<String> path = new ArrayList<>();

        findNodeInOriginal(original, target, path);

        return tracePathInCloned(cloned, path);
    }

    private TreeNode tracePathInCloned(TreeNode root, List<String> path) {
        TreeNode node = root;
        for (String dir : path) {
            if (dir.equals("L")) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private boolean findNodeInOriginal(TreeNode root, TreeNode target, List<String> path) {
        if (root == null) return false;


        if (root == target) return true;


        path.add("L");
        boolean left = findNodeInOriginal(root.left, target, path);
        if (left) {
            return true;
        } else {
            path.remove(path.size() - 1);
        }

        path.add("R");
        boolean right = findNodeInOriginal(root.right, target, path);
        if (right) {
            return true;
        } else {
            path.remove(path.size() - 1);
        }

        return false;
    }

    public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (cloned == null) return null;
        if (cloned.val == target.val) {
            return cloned;
        }
        TreeNode fromLeft = getTargetCopy(original, cloned.left, target);
        if (fromLeft != null) return fromLeft;
        TreeNode fromRight = getTargetCopy(original, cloned.right, target);
        return fromRight;
    }


}
