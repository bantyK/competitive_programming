//1669 https://leetcode.com/problems/merge-in-between-linked-lists/
class MergeInBetweenLL {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode node = list1;
        ListNode prev = null;
        while(node != null && node.val != a) {
            prev = node;
            node = node.next;
        }
        
        ListNode last = node;
        while(last != null && last.val != b) {
            last = last.next;
        }
        
        ListNode lastB = list2;
        while(lastB.next != null) {
            lastB = lastB.next;
        }
        
        if(prev != null && last != null && lastB != null) {
            prev.next = list2;
            lastB.next = last.next;
        }
        return list1;
    }
}
