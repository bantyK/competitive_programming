//https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/531/week-4/3313/
import java.util.*;
public class FirstUnique {
    public static void main(String[] args) {
        // ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
        // [[[2,3,5]],[],[5],[],[2],[],[3],[]]

        FirstUnique obj = new FirstUnique(new int[]{2,3,5});
        System.out.println(obj.showFirstUnique());
        
        obj.add(5);
        obj.add(2);
        
        System.out.println(obj.showFirstUnique());

    }
    
    Map<Integer, ListNode> keyNodeMap;    
    ListNode head;
    ListNode tail;
    Set<Integer> seen;
    public FirstUnique(int[] nums) {
        keyNodeMap = new HashMap<>();    
        head = null;
        tail = null;
        seen = new HashSet<>();

        for(int num : nums) {
            add(num);
        }
    }
    
    public int showFirstUnique() {
        if(keyNodeMap.size() == 0 || head == null) return -1;
        return head.val;
    }
    
    public void add(int value) {
        if(seen.contains(value)) {
            // remove from double linked list
            ListNode node = keyNodeMap.get(value);
            if(node != null) {
                if(node.prev == null) {
                    if(node.next != null) {
                        node.next.prev = null;
                        head = node.next;
                    } else {
                        head = null;
                        tail = null;
                    }
                } else if(node.next == null) {
                    tail = node.prev;
                    node.prev.next = null;
                } else {
                    // node somewhere in between
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                node = null;
                keyNodeMap.put(value, null);
            }
        } else {
            seen.add(value);
            ListNode node = new ListNode(value);
            if(tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            keyNodeMap.put(value, node);
        }
    }

    private static class ListNode {
        int val;
        ListNode next, prev;

        public ListNode(int x) {
            val = x;
            next = prev = null;
        }
    }
}