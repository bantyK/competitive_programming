// 116 https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
public class ConnectNextNode1 {
    public static void main(String[] args) {
        ConnectNextNode1 obj = new ConnectNextNode1();
    }

    public Node connect(Node root) {
        Node curr = root;
        Node head = null;
        Node prev = null;

        while(curr != null) {
            while(curr != null) {
                if(curr.left != null) {
                    if(prev != null) {
                        prev.next = curr.left;
                    } else {
                        head = curr.left;
                    }
                    prev = curr.left;
                }

                if(curr.right != null) {
                    if(prev != null) {
                        prev.next = curr.right;
                    } else {
                        head = curr.right;
                    }
                    prev = curr.right;
                }

                curr = curr.next;
            }

            curr = head;
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
