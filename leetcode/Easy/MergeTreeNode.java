package com.company.leet;


public class MergeTreeNode {

    public static void main(String[] args) {
        MergeTreeNode obj = new MergeTreeNode();
        TreeNode t1 = obj.addNode(null, 10);
        t1 = obj.addNode(t1, 1);
        t1 = obj.addNode(t1, 5);
        t1 = obj.addNode(t1, 20);
        /*obj.inorder(t1);
        System.out.println();*/

        TreeNode t2 = obj.addNode(null, 5);
        t2 = obj.addNode(t2, 10);
        t2 = obj.addNode(t2, 4);
        t2 = obj.addNode(t2, 3);
        t2 = obj.addNode(t2, 7);
        t2 = obj.addNode(t2, 15);
        t2 = obj.addNode(t2, 8);
        obj.inorder(t2);
        System.out.println();

        /*TreeNode resultNode = obj.mergeTrees(t1, t2);
        obj.inorder(resultNode);*/

        TreeNode node = obj.searchBST(t2, 100);
        obj.inorder(node);


    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }


    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode root = nodeHelper(t1, t2);

        if (t1 != null && t2 != null) {
            root.left = mergeTrees(t1.left, t2.left);
            root.right = mergeTrees(t1.right, t2.right);
        } else if (t1 != null) {
            root.left = mergeTrees(t1.left, null);
            root.right = mergeTrees(t1.right, null);
        } else if (t2 != null) {
            root.left = mergeTrees(null, t2.left);
            root.right = mergeTrees(null, t2.right);
        } else {
            return null;
        }
        return root;
    }


    private TreeNode nodeHelper(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null)
            return new TreeNode(root1.val + root2.val);
        else if (root1 != null) return root1;
        else return root2;
    }

    private TreeNode addNode(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else if (root.val > value) {
            root.left = addNode(root.left, value);
        } else {
            root.right = addNode(root.right, value);
        }
        return root;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
}
