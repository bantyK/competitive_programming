package solutions.easy;

import java.util.*;
import models.TreeNode;

//872 https://leetcode.com/problems/leaf-similar-trees/
public class LeafSimilarTrees {
    public static void main(String[] args) {
        LeafSimilarTrees obj = new LeafSimilarTrees();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(17);

        System.out.println(obj.leafSimilar(root, root));

    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        }

        List<Integer> leafSequence1 = new ArrayList<>();
        List<Integer> leafSequence2 = new ArrayList<>();

        findSequence(root1, leafSequence1);
        findSequence(root2, leafSequence2);

        if(leafSequence1.size() != leafSequence2.size()) return false;

        for(int i = 0; i < leafSequence1.size(); i++) {
            if(leafSequence1.get(i) != leafSequence2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void findSequence(TreeNode root, List<Integer> leafSequence) {
        if(root.left == null && root.right == null) {
            leafSequence.add(root.val);
            return;
        }
        if(root.left != null) {
            findSequence(root.left, leafSequence);
        }
        if(root.right != null) {
            findSequence(root.right, leafSequence);
        }
    }

}
