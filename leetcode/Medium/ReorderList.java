import java.util.*;

//143 https://leetcode.com/problems/reorder-list/
public class ReorderList {
    public static void main(String[] args) {
        ReorderList obj = new ReorderList();
        ListNode head = obj.prepareList(5, 1);
        printList(head);
        System.out.println();
        obj.reorderListOptimised(head);
        printList(head);
    }

    private static void printList(ListNode head) {
        if (head == null) return;
        System.out.print(head.val + " ");
        printList(head.next);
    }

    private ListNode prepareList(int N, int current) {
        if (N == 0) return null;

        ListNode head = new ListNode(current);
        head.next = prepareList(N - 1, current + 1);
        return head;
    }


    // Divide the list in 2 halfs and connect the nodes of both the halves with each other

    public void reorderListOptimised(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head1 = head;
        ListNode head2;
        if (fast != null) {
            head2 = reverse(slow.next);
            slow.next = null;
        } else {
            prev.next = null;
            head2 = reverse(slow);
        }

        ListNode next1 = head1.next;
        ListNode next2 = head2.next;

        while (head1 != null && head2 != null) {
            head1.next = head2;
            head2.next = next1;
            head1 = next1;
            if (next1 != null) next1 = next1.next;
            head2 = next2;
            if (next2 != null) next2 = next2.next;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode reverse = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }


    public void reorderList(ListNode head) {
        if (head == null) return;
        Map<Integer, ListNode> nodesMap = new HashMap<>();
        int totalNodes = getNodes(head, nodesMap, 0);

        ListNode newHead = null;
        ListNode temp = null;
        int i;
        for (i = 0; i < totalNodes / 2; i++) {
            ListNode node1 = nodesMap.get(i);
            ListNode node2 = nodesMap.get(totalNodes - i - 1);
            node1.next = node2;
            node2.next = null;
            if (newHead == null) {
                newHead = node1;
            } else {
                temp.next = node1;
            }
            temp = node2;
        }

        if (totalNodes > 1 && totalNodes % 2 == 1) {
            ListNode next = nodesMap.get(i);
            temp.next = next;
            next.next = null;
        }

        head = newHead;
    }

    private int getNodes(ListNode head, Map<Integer, ListNode> nodesMap, int index) {
        if (head == null) return 0;

        nodesMap.put(index, head);
        return 1 + getNodes(head.next, nodesMap, index + 1);
    }

    private static class ListNode {
        final int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }
}
