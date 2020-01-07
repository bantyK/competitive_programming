// https://practice.geeksforgeeks.org/problems/find-k-th-smallest-element-in-bst/1/
public class KthSmallestElementInBST {
    private static int value = -1;
    private static int count = 0;
    public static void main(String[] args) {
        KthSmallestElementInBST obj = new KthSmallestElementInBST();

        Node root = new Node(15);
        root.left = new Node(10);
        root.right = new Node(25);
        root.left.left = new Node(5);
        root.left.left.left = new Node(2);

        root.left.right = new Node(12);
        root.left.right.left = new Node(11);
        root.left.right.right = new Node(14);

        root.right.left = new Node(20);
        root.right.right = new Node(30);

        System.out.println(obj.kthSmallestElement(root, 7));
    }

    public int kthSmallestElement(Node root, int k) {
        if (root == null || k < 0) return -1;
        count = k;
        helper(root);
        return value;
    }

    private void helper(Node root) {
        if (root.left != null) helper(root.left);

        count--;

        if (count == 0) {
            value = root.data;
            return;
        }

        if (root.right != null) helper(root.right);
    }

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }
}
