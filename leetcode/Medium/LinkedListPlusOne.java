import input.ListNode;

// 369 https://leetcode.com/problems/plus-one-linked-list/
public class LinkedListPlusOne {
    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        head.next = new ListNode(9);
        head.next.next = new ListNode(9);

        System.out.println(new LinkedListPlusOne().plusOne(head));
    }

    public ListNode plusOne(ListNode head) {
        int finalCarry = helper(head);
        if (finalCarry == 1) {
            ListNode newHead = new ListNode(1);
            newHead.val = 1;
            newHead.next = head;
            head = newHead;
        }
        return head;
    }

    private int helper(ListNode head) {
        if (head.next == null) {
            if (head.val + 1 == 10) {
                head.val = 0;
                return 1;
            } else {
                head.val += 1;
                return 0;
            }
        }

        int carry = helper(head.next);

        if (head.val + carry == 10) {
            head.val = 0;
            return 1;
        } else {
            head.val += carry;
            return 0;
        }
    }
}