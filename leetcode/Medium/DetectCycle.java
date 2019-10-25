package solutions.medium;

import models.ListNode;

//https://leetcode.com/problems/linked-list-cycle-ii/
public class DetectCycle {
    public static void main(String[] args) {
        DetectCycle obj = new DetectCycle();

        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(0);
//        ListNode node4 = new ListNode(4);
        head.next = node2;
        node2.next = head;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node2;

        if (obj.detectCycle(head) != null) {
            System.out.println(obj.detectCycle(head).val);
        } else {
            System.out.println("No cycle");
        }

    }

    // 1 -> 2 -> 3 -> 4
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode fast = head;
        ListNode slow = head;

        boolean cycleFound = false;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            // if the slow and the fast pointer meets, then the list has a pointer
            if (slow == fast) {
                cycleFound = true;
                break;
            }
        }


        if (cycleFound) {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        return null;
    }
}





















