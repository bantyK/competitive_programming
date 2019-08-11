package solutions;

import models.ListNode;

public class RemoveNthFromLast {
    public static void main(String[] args) {
        RemoveNthFromLast obj = new RemoveNthFromLast();
        ListNode head = new ListNode(1);

        ListNode node2 = new ListNode(2);
        head.next = node2;

        ListNode node3 = new ListNode(3);
        node2.next = node3;

        ListNode node4 = new ListNode(4);
        node3.next = node4;

        ListNode node5 = new ListNode(5);
        node4.next = node5;

        obj.printList(head);

        head = obj.removeNthFromEnd(head, 1);
        System.out.println();

        obj.printList(head);

    }

    private void printList(ListNode head) {
        if (head == null)
            return;
        System.out.print(head.val + "  ");
        printList(head.next);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = lengthOfList(head); //1
        int positionFromStart = length - n; // 0
        ListNode node = head;
        ListNode prev = head;
        if (positionFromStart == 0) {
            head = head.next;
        } else {
            for (int i = 0; i < positionFromStart; i++) {
                prev = node;
                node = node.next;
            }
            prev.next = node.next;
        }
        return head;
    }

    public int lengthOfList(ListNode head) {
        if (head == null) {
            return 0;
        } else {
            return 1 + lengthOfList(head.next);
        }
    }
}
