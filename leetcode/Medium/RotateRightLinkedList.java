import java.util.*;

//61 https://leetcode.com/problems/rotate-list/
public class RotateRightLinkedList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0 || head.next == null) return head;

        int totalNodes = 1;
        ListNode tail = head;

        while(tail.next != null) {
            totalNodes += 1;
            tail = tail.next;
        }

        k = k % totalNodes;

        if(k == 0) return head;

        k = totalNodes - k;

        ListNode newHead = head;
        ListNode prev = head;

        while(k > 0) {
            prev = newHead;
            newHead = newHead.next;
            k--;
        }

        prev.next = null;
        tail.next = head;
        head = newHead;

        return head;
    }
}
