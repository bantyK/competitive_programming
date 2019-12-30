import java.util.*;

public class ConnectNextNode {
    public static void main(String[] args) {
        ConnectNextNode obj = new ConnectNextNode();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Node res = obj.connect(root);
        System.out.println(res);
    }

    public Node connect(Node root) {
        Node current = root;
        Node head = null;
        Node prev = null;

        while (current != null) {
            // processing the current level
            while (current != null) {
                if (current.left != null) {
                    if (prev != null) {
                        prev.next = current.left;
                    } else {
                        head = current.left;
                    }
                    prev = current.left;
                }

                if (current.right != null) {
                    if (prev != null) {
                        prev.next = current.right;
                    } else {
                        head = current.right;
                    }
                    prev = current.right;
                }

                current = current.next;
            }
            current = head;
            head = null;
            prev = null;
        }

        return root;
    }


    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
