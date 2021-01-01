import java.util.*;

// 1171 https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
public class RemoveZeroSumConsecutiveNodes {
    public static void main(String[] args) {
        RemoveZeroSumConsecutiveNodes obj = new RemoveZeroSumConsecutiveNodes();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(-2);
        head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(3);
        // head.next.next.next.next.next = new ListNode(4);

        ListNode res = obj.removeZeroSumSublists(head);
        System.out.println(res);
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        int sum = 0;
        ListNode curr = dummyNode;

        while (curr != null) {
            sum += curr.val;
            map.put(sum, curr);
            curr = curr.next;
        }

        sum = 0;
        curr = dummyNode;

        while (curr != null) {
            sum += curr.val;
            curr.next = map.get(sum).next;
            curr = curr.next;
        }

        return dummyNode.next;
    }

    public ListNode removeZeroSumSublists2(ListNode head) {
        Map<Integer, ListNode> prefixSumMap = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int runningSum = 0;
        ListNode currentNode = dummy;

        while (currentNode != null) {
            runningSum += currentNode.val;
            if (prefixSumMap.containsKey(runningSum)) {
                // the sum of nodes from the map node to the current node is not adding any
                // value to the sum, meaning the nodes
                // must all be adding into 0. We found a zero sum consecutive set of nodes.
                // Remove them from the list

                // resetting the runningSum and prefixSumMap, because we are going to traverse
                // the nodes again from the start
                prefixSumMap.get(runningSum).next = currentNode.next;
                runningSum = 0;
                prefixSumMap.clear();
                currentNode = dummy;
            } else {
                prefixSumMap.put(runningSum, currentNode);
                currentNode = currentNode.next;
            }

        }

        return dummy.next;
    }
}