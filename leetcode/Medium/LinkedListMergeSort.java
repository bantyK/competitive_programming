import input.ListNode;

// 148 https://leetcode.com/problems/sort-list/
public class LinkedListMergeSort {
    public static void main(String[] args) {
        LinkedListMergeSort obj = new LinkedListMergeSort();

    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMiddleNode(head);
        ListNode middleNext = middle.next;
        middle.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(middleNext);

        ListNode merged = merge(left, right);
        return merged;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode last = null;
        ListNode root = null;

        while (left != null && right != null) {
            if (left.val > right.val) {
                if (last == null) {
                    last = right;
                    root = last;
                } else {
                    last.next = right;
                    last = last.next;
                }
                right = right.next;
                last.next = null;
            } else {
                if (last == null) {
                    last = left;
                    root = left;
                } else {
                    last.next = left;
                    last = last.next;
                }
                left = left.next;
                last.next = null;
            }
        }

        if (right != null) {
            last.next = right;
        } else if (left != null) {
            last.next = left;
        }

        return root;
    }

    public ListNode getMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
