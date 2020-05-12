// 707 https://leetcode.com/problems/design-linked-list/
public class MyLinkedList {
    ListNode head;
    ListNode tail;
    int listSize;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        listSize = 0;
    }


    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < listSize) {
            return getNode(head, index, 0).val;
        }

        return -1;
    }

    private ListNode getNode(ListNode node, int index, int current) {
        if (current == index) {
            return node;
        }
        return getNode(node.next, index, current + 1);
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        incrementListSize();
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    private void incrementListSize() {
        listSize++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        incrementListSize();
        ListNode node = new ListNode(val);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if(index > listSize) return;
        if (index == 0) {
            addAtHead(val);
        } else if (index == listSize) {
            addAtTail(val);
        } else {
            ListNode prev = getNode(head, index - 1, 0);
            ListNode node = new ListNode(val);
            node.next = prev.next;
            prev.next = node;
            incrementListSize();
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= listSize) return;
        decrementListSize();
        if (index == 0) {
            deleteHead();
        } else {
            ListNode prev = getNode(head, index - 1, 0);
            ListNode node = prev.next;

            if (node == tail) {
                prev.next = null;
                tail = prev;
            } else {
                prev.next = node.next;
            }

            node = null;
        }
    }

    private void decrementListSize() {
        listSize--;
    }

    private void deleteHead() {
        head = head.next;
    }

    public void printList() {
        printList(head);
    }

    private void printList(MyLinkedList.ListNode node) {
        if (node == null) {
            System.out.println();
            return;
        }
        System.out.print(node.val + " - ");
        printList(node.next);
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

}
