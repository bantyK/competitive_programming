import java.util.*;

// 285 https://leetcode.com/problems/inorder-successor-in-bst/
public class InorderSuccessor {
    public static void main(String[] args) {
        InorderSuccessor obj = new InorderSuccessor();

        final Node rootNode = new Node(652);
        Node root = rootNode;
        root.left = new Node(400);
        root.right = new Node(755);
        root.right.right = new Node(933);

        System.out.println(obj.inorderSuccessor(root, rootNode).data);

    }

    public Node inorderSuccessor(Node root, Node k) {
        if (k.right != null) {
            return findMinInRightSubTree(k.right);
        } else {
            Node lowest = null;
            Node node = root;

            while (node != k) {
                if (node.data > k.data) {
                    lowest = node;
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            return lowest;
        }
    }



    private Node findMinInRightSubTree(Node root) {
        if (root.left == null) return root;
        return findMinInRightSubTree(root.left);
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
