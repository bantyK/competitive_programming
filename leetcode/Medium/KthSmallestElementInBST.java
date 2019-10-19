package solutions.medium;

import models.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST {
    public static void main(String[] args) {
        KthSmallestElementInBST obj = new KthSmallestElementInBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        int kthSmallest = obj.kthSmallest(root, 2);
        System.out.println(kthSmallest);
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list.get(k-1);
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if(root != null) {
            inorderTraversal(root.left, list);
            list.add(root.val);
            inorderTraversal(root.right, list);
        }
    }
}
