import input.ListNode;

import java.util.PriorityQueue;

// 23 https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKLists {
    public static void main(String[] args) {
        MergeKLists obj = new MergeKLists();

        ListNode head = new ListNode(1);
        head.next = new ListNode(-1);
        head.next.next = new ListNode(-1);
        head.next.next.next = new ListNode(-1);

        ListNode head2 = null;
        ListNode res = obj.mergeKLists2(new ListNode[]{null});
        ListNode temp = res;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>((n1, n2) -> {
            if (n1.node.val != n2.node.val) return n1.node.val - n2.node.val;
            else {
                return n1.listIndex - n2.listIndex;
            }
        });

        ListNode[] ptr = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            if (head != null) {
                ptr[i] = head;
                minHeap.add(new HeapNode(head, i));
            }
        }

        ListNode res = null;
        ListNode last = null;
        while (!minHeap.isEmpty()) {
            HeapNode current = minHeap.poll();
            int listIndex = current.listIndex;
            ListNode listNode = current.node;
            if (listNode.next != null) {
                minHeap.add(new HeapNode(listNode.next, listIndex));
            }
            ListNode node = new ListNode(current.node.val);
            if (last == null) {
                res = node;
                last = res;
            } else {
                last.next = node;
                last = last.next;
            }
        }

        return res;
    }

    /**
     * Brut force
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (ListNode head : lists) {
            addNodesIntoHeap(head, minHeap);
        }

        ListNode res = null;
        ListNode last = null;
        while (minHeap.size() > 0) {
            ListNode node = new ListNode(minHeap.poll());
            if (last == null) {
                res = node;
                last = node;
            } else {
                last.next = node;
                last = last.next;
            }
        }

        return res;
    }

    public void addNodesIntoHeap(ListNode head, PriorityQueue<Integer> heap) {
        while (head != null) {
            heap.add(head.val);
            head = head.next;
        }
    }

    private static class HeapNode {
        ListNode node;
        int listIndex;

        public HeapNode(ListNode node, int listIndex) {
            this.node = node;
            this.listIndex = listIndex;
        }
    }
}
