import input.*;

//92 https://leetcode.com/problems/reverse-linked-list-ii/
public class ReverseLinkedList2 {
    public static void main(String[] args) {
        ReverseLinkedList2 obj = new ReverseLinkedList2();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = obj.reverseBetween(head, 1, 1);

        Util.printList(res);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;

        int i = 1;
        ListNode start = head;
        ListNode prev = start;

        while (i < m) {
            i++;
            prev = start;
            start = start.next;
        }

        ListNode temp = start;
        ListNode node = start.next;
        ListNode nextNode = null;
        if (node != null) {
            nextNode = node.next;
        }

        for (int idx = i; idx < n; idx++) {
            node.next = start;
            start = node;
            node = nextNode;
            if (nextNode != null)
                nextNode = nextNode.next;
        }

        if (m == 1) {
            temp.next = node;
            head = start;
        } else {
            prev.next = start;
            temp.next = node;
        }

        return head;
    }

}
