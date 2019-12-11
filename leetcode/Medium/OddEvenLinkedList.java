import java.util.*;

// 328 https://leetcode.com/problems/odd-even-linked-list/

public class OddEvenLinkedList {
    public static void main(String[] args) {
        OddEvenLinkedList obj = new OddEvenLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(7);
//        head.next.next.next.next = new ListNode(8);
//        head.next.next.next.next.next = new ListNode(9);
//        head.next.next.next.next.next.next = new ListNode(10);

        ListNode res = obj.oddEvenList(head);
        obj.print(res);
    }

    private void print(ListNode head) {
        if (head != null) {
            System.out.print(head.val + " ");
            print(head.next);
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        else if (head.next == null) return head;
        else if (head.next.next == null) return head;

        ListNode node = head;
        ListNode even = head.next;
        ListNode odd = even.next;
        ListNode next = odd.next;

        while (even != null && even.next != null) {
            next = odd.next;
            odd.next = node.next;
            node.next = odd;
            even.next = next;
            even = next;

            if (even != null) {
                odd = even.next;
            }
            node = node.next;
        }

        return head;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
