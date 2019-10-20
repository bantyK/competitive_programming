package solutions.medium;

class MyLinkedList {

    private class ListNode {
        int value;
        ListNode next;

        public ListNode(int v) {
            value = v;
            next = null;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();

//        ["MyLinkedList","get","addAtIndex","get","get","addAtIndex","get","get"]
//        [[],[0],[1,2],[0],[1],[0,1],[0],[1]]

//        ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail",
//        "get","addAtHead","addAtIndex","addAtHead"]
//        [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]


//        ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
//        [[],[1],[3],[1,2],[-1],[1],[-3]]

//        ["MyLinkedList","addAtIndex","get","deleteAtIndex"]
//        [[],[-1,0],[0],[-1]]

        linkedList.addAtIndex(-1, 0);
        int output = linkedList.get(0);
        System.out.println(output);
        linkedList.deleteAtIndex(-1);

//        ["MyLinkedList","addAtHead","addAtIndex","get","get","get"]
//        [[],[1],[1,2],[1],[0],[2]]


//        linkedList.addAtHead( 1);
//        linkedList.addAtIndex(1, 2);
//        int output = linkedList.get(1);
//        System.out.println(output);
//         output = linkedList.get(0);
//        System.out.println(output);
//        output = linkedList.get(2);
//        System.out.println(output);

//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1, 2);
//        int output = linkedList.get(-1);
//        System.out.println(output);


//        linkedList.addAtHead(7);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(1);
//        linkedList.addAtIndex(3, 0);
//        linkedList.deleteAtIndex(2);
//        linkedList.addAtHead(6);
//        linkedList.addAtTail(4);
//        int output = linkedList.get(4);
//        System.out.println(output);
//        linkedList.addAtHead(4);
//        linkedList.addAtIndex(5, 0);
//        linkedList.addAtHead(6);

//        int output = linkedList.get(0);
//        System.out.println(output);
//        linkedList.addAtIndex(1, 2);
//        output = linkedList.get(0);
//        System.out.println(output);
//        output = linkedList.get(1);
//        System.out.println(output);
//        linkedList.addAtIndex(0, 1);
//        output = linkedList.get(0);
//        System.out.println(output);
//        output = linkedList.get(1);
//        System.out.println(output);


//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(1, 2);
//        System.out.println(linkedList.get(1));
//        linkedList.deleteAtIndex(1);
//        System.out.println(linkedList.get(1));
//
//        linkedList.addAtTail(4);
//        linkedList.printList(linkedList.head);
//        linkedList.addAtIndex(3, 4);
//        System.out.println();
//        linkedList.printList(linkedList.head);
//        linkedList.addAtIndex(2, 5);
//        System.out.println();
//        linkedList.printList(linkedList.head);
//        linkedList.deleteAtIndex(0);
//        System.out.println();
//        linkedList.printList(linkedList.head);

    }


    private void printList(ListNode head) {
        if (head == null)
            return;
        System.out.print(head.value + "->");
        printList(head.next);
    }

    ListNode head = null;
    ListNode tail = null;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0) return -1;
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            if (current == null)
                return -1;
            current = current.next;
        }

        return current == null ? -1 : current.value;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0) return;

        ListNode current = head;
        ListNode prev = head;
        ListNode node = new ListNode(val);

        for (int i = 0; i < index; i++) {
            prev = current;
            if (prev == null)
                return;
            current = current.next;

        }

        if (index == 0) {
            head = node;
        }
        if (prev != null) {
            if (current == null) {
                tail = node;
            }
            prev.next = node;
            node.next = current;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0)
            return;
        ListNode current = head;
        ListNode prev = head;

        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;

            if (current == null)
                return;
        }

        if (index == 0) {
            head = head.next;
        } else {
            if (current.next == null) {
                tail = prev;
            }
            prev.next = current.next;
            current = null;
        }
    }
}