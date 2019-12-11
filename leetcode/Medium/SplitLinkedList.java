import java.util.*;

// 725 https://leetcode.com/problems/split-linked-list-in-parts/
public class SplitLinkedList {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] parts = new ListNode[k];
        int[] counts = new int[k];

        Arrays.fill(parts, null);
        ListNode node = root;
        int totalNodes = totalNodes(root);
        int equal = totalNodes / k;
        Arrays.fill(counts, equal);
        int remaining = totalNodes - (equal * k);

        for (int i = 0; i < remaining; i++) {
            counts[i] += 1;
        }

        for (int i = 0; i < parts.length; i++) {
            int a = counts[i];
            ListNode tail = null;
            while (a-- > 0) {
                if (tail == null) {
                    parts[i] = new ListNode(node.val);
                    tail = parts[i];
                } else {
                    tail.next = new ListNode(node.val);
                    tail = tail.next;
                }
                node = node.next;
            }
        }


        return parts;
    }

    private int totalNodes(ListNode head) {
        int count = 0;
        while (head != null) {
            count += 1;
            head = head.next;
        }
        return count;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
