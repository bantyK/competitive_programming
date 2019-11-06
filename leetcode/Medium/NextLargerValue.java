package solutions.medium;

import models.ListNode;
import solutions.util.LinkedListUtil;

import java.util.*;

// https://leetcode.com/problems/next-greater-node-in-linked-list/
public class NextLargerValue {
    public static void main(String[] args) {
        NextLargerValue obj = new NextLargerValue();

        ListNode head = LinkedListUtil.createLinkedListFromArray(new int[]{1, 7, 5, 1, 9, 2, 5, 1});

        int[] ints = obj.nextLargerNodes(head);

        for (int i : ints) {
            System.out.print(i + " ");
        }
    }

    public int[] nextLargerNodes(ListNode head) {
        if(head == null) return null;

        List<Integer> vals = new ArrayList<>();

        while(head != null) {
            vals.add(head.val);
            head = head.next;
        }

        int[] res = new int[vals.size()];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < vals.size(); ++i) {
            while(!stack.isEmpty() && vals.get(stack.peek()) < vals.get(i)) {
                res[stack.pop()] = vals.get(i);
            }
            stack.push(i);
        }

        return res;
    }
}
