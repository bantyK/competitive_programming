import java.util.*;

//426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class BinaryTreeToDLL {

    Node first = null;
    Node last = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        first.left = last;
        last.right = first;
        return first;
    }

    private void helper(Node node) {
        if (node == null) return;

        helper(node.left);

        if (last != null) {
            node.left = last;
            last.right = node;
        } else {
            first = node;
        }

        last = node;

        helper(node.right);
    }


    // Inorder traversal and then create a DLL from the values.
    public Node treeToDoublyListInorder(Node root) {
        if (root == null) return null;
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        return createDLL(values);
    }

    private void inorder(Node root, List<Integer> values) {
        if (root == null) return;
        inorder(root.left, values);
        values.add(root.val);
        inorder(root.right, values);
    }

    private Node createDLL(List<Integer> values) {
        Node head = null;
        Node tail = null;
        for (int i = 0; i < values.size(); i++) {
            Node node = new Node(values.get(i));

            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.right = node;
                node.left = tail;
                tail = node;
            }
        }
        tail.right = head;
        head.left = tail;
        return head;
    }

    private static class Node {
        int val;
        Node right;
        Node left;

        public Node(int x) {
            val = x;
        }
    }
}
