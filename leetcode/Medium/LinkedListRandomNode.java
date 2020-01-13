import input.ListNode;

import java.util.*;

// 382 https://leetcode.com/problems/linked-list-random-node/
public class LinkedListRandomNode {

    Map<Integer, ListNode> map = new HashMap<>();
    Random random = new Random();

    public LinkedListRandomNode(ListNode head) {
        ListNode node = head;

        while (node != null) {
            map.put(map.size(), node);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LinkedListRandomNode obj = new LinkedListRandomNode(head);

        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
        System.out.println(obj.getRandom());
    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        int index = random.nextInt(map.size());
        return map.get(index).val;
    }
}
