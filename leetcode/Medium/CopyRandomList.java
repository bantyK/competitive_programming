import java.util.*;

// 138 https://leetcode.com/problems/copy-list-with-random-pointer/
public class CopyRandomList {
    public static void main(String[] args) {
        CopyRandomList obj = new CopyRandomList();
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;

        while (curr != null) {
            map.get(curr).random = map.get(curr.random);
            map.get(curr).next = map.get(curr.next);
            curr = curr.next;
        }

        return map.get(head);

    }


    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
