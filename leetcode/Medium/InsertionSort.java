// 147 https://leetcode.com/problems/insertion-sort-list/
public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort obj = new InsertionSort();

        final int[] arr = {-1, 5, 3, 4, 0};
        obj.insertionSort(arr);


        ListNode head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);

        final ListNode listNode = obj.insertionSortList(head);

        print(listNode);
    }


    private static void print(ListNode head) {
        if (head == null) return;
        System.out.print(head.val + " ");
        print(head.next);
    }

    // simple array insertion sort
    public void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;

                j--;
            }
        }
    }

    // convert the list into array, sort the list and then convert back to list
	public ListNode insertionSortList(ListNode head) {
	        List<Integer> values = new ArrayList<>();
	        getNodeValues(head, values);
	        values.sort((i1, i2) -> i1 - i2);
	        return buildNode(values);
	    }
    
	    private void getNodeValues(ListNode head, List<Integer> values) {
	        if(head == null) return;
	        values.add(head.val);
	        getNodeValues(head.next, values);
	    }
    
	    private ListNode buildNode(List<Integer> values) {
	        int index = 0;
	        ListNode head = null;
	        ListNode tail = null;
        
	        for(int i = 0; i < values.size(); i++) {
	            if(tail == null) {
	                head = new ListNode(values.get(i));
	                tail = head;
	            } else {
	                tail.next = new ListNode(values.get(i));
	                tail = tail.next;
	            }
	        }
        
	        return head;
	    } 
		
		// in-place insertion sort
		
		public ListNode insertionSortList(ListNode head) {
		        ListNode curr = head, next = null;
		        ListNode fake = new ListNode(0);

		        while (curr != null) {
		            next = curr.next;

		            ListNode p = fake;
		            while (p.next != null && p.next.val < curr.val) {
		                p = p.next;
		            }
		            curr.next = p.next;
		            p.next = curr;

		            curr = next;
		        }
		        return fake.next;
		    }
    
}
