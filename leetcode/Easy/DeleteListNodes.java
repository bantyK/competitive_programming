//203 https://leetcode.com/problems/remove-linked-list-elements/
public class DeleteListNodes {
	    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        
        while(head != null && head.val == val) {
            head = head.next;
        }
        
        if(head != null) {
            ListNode node = head.next;
            ListNode next = head;
            ListNode prev = head;
            while(node != null) {
                while(node != null && node.val == val) {
                    node = node.next;
                }
                prev.next = node;
                prev = node;
                if(node != null)
                    node = node.next;
            }
        }
        return head;
    }	
}
