// 430 https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
public class FlattenDoublyLinkedList {
    public static void main(String[] args) {
        FlattenDoublyLinkedList obj = new FlattenDoublyLinkedList();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.next = node2;
        node2.prev = node1;

        node2.next = node5;
        node5.prev = node2;

        node2.child = node3;
        node3.next = node4;
        node4.prev = node3;

        Node head = obj.flatten(node1);

        printList(head);
    }

    private static void printList(Node head) {
        if (head == null) return;
        System.out.print(head.val + " ");
        printList(head.next);
    }


    public Node flatten(Node head) {
        if(head == null) return head;

        if(head.child == null) {
            Node n = flatten(head.next);
            head.next = n;
            if(n != null)
                n.prev = head;
        } else {
            Node temp = head;
            Node next = temp.next;
            Node node = flatten(head.child);
            temp.child = null;
            node.prev = temp;
            temp.next = node;
            Node n = node;
            while(n.next != null) {
                n = n.next;
            }
            n.next = next;
            if(next != null)
                next.prev = n;
        }

        return head;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    ;
}
