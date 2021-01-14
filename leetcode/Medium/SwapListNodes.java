import java.util.*;

import input.ListNode;

//1721 https://leetcode.com/problems/swapping-nodes-in-a-linked-list/ 
public class SwapListNodes {
    public static void main(String[] args) {
        SwapListNodes obj = new SwapListNodes();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next = new ListNode(9);
        ListNode res = obj.swapNodes2(head, 7);
        System.out.println(res);
    }

    // single pass solution
    public ListNode swapNodes2(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        int n = 1;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
            n++;
        }

        System.out.println(n);

        ListNode node1 = null;
        ListNode node2 = null;

        int count = 1;
        while (!stack.isEmpty()) {
            ListNode top = stack.pop();
            if (count == k) {
                node1 = top;
            } else if (count == n - k + 1) {
                node2 = top;
            }
            if (node1 != null && node2 != null)
                break;
            count++;
        }

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;

        return head;
    }

    // Double pass
    public ListNode swapNodes(ListNode head, int k) {
        ListNode curr = head;

        int count = 0;
        while (curr != null && count < k - 1) {
            curr = curr.next;
            count++;
        }
        ListNode fromLeft = curr;

        ListNode fromRight = getKLastNode(head, k);

        int temp = fromLeft.val;
        fromLeft.val = fromRight.val;
        fromRight.val = temp;

        return head;
    }

    private ListNode getKLastNode(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        ListNode fromRight = null;

        while (k > 0) {
            fromRight = stack.pop();
            k--;
        }

        return fromRight;
    }

}