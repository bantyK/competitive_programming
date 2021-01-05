
import input.ListNode;

// 82 https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
public class RemoveDuplicates2 {
    public static void main(String[] args) {
        RemoveDuplicates2 obj = new RemoveDuplicates2();

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);

        ListNode res = obj.deleteDuplicates(head);
        System.out.println(res);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-200);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode first = head;
        ListNode second = head.next;

        while (second != null) {
            if (first.val == second.val) {
                while (second != null && second.val == first.val) {
                    second = second.next;
                }
                prev.next = second;
                first = second;
                if (first != null) {
                    second = second.next;
                } else {
                    second = null;
                }
            } else {
                prev = first;
                first = second;
                second = second.next;
            }
        }

        return dummy.next;
    }
}