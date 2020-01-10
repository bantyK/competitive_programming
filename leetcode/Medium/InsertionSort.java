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

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode ptr = head.next;
        while (ptr != null) {
            ListNode node = head;
            ListNode prev = node;
            while (node.val < ptr.val && node != ptr) {
                prev = node;
                node = node.next;
            }
            if (node == ptr) {
                ptr = ptr.next;
                continue;
            }
            ListNode temp = ptr;
            ptr = ptr.next;
            ListNode x = head;
            while (x.next != temp) {
                x = x.next;
            }

            if (node == head) {
                x.next = ptr;
                temp.next = head;
                head = temp;
            } else {
                prev.next = temp;
                x.next = temp.next;
                temp.next = node;
            }
        }

        return head;

    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
