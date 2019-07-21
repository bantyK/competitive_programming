package com.company.leet;


public class MergeSortedLinkedList {



    public static void main(String[] args) {
        MergeSortedLinkedList mergeSortedLinkedList = new MergeSortedLinkedList();
        ListNode head1 = null;
        head1 = mergeSortedLinkedList.addNode(null, 1);
        head1 = mergeSortedLinkedList.addNode(head1, 2);
        head1 = mergeSortedLinkedList.addNode(head1, 3);
        head1 = mergeSortedLinkedList.addNode(head1, 4);
//        head1 = mergeSortedLinkedList.addNode(head1, 5);

        printList(head1);

        ListNode resultNode = mergeSortedLinkedList.middleNode(head1);
        System.out.println();

        printList(resultNode);


    }

    private ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private ListNode addNode(ListNode head, int dataToAdd) {
        if (head == null) head = new ListNode(dataToAdd);
        else if (head.next == null) {
            head.next = new ListNode(dataToAdd);
        } else {
            head.next = addNode(head.next, dataToAdd);
        }
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode resultNode = null;

        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                resultNode = addNode(resultNode, node2.val);
                node2 = node2.next;
            } else {
                resultNode = addNode(resultNode, node1.val);
                node1 = node1.next;
            }
        }

        while (node1 != null) {
            resultNode = addNode(resultNode, node1.val);
            node1 = node1.next;
        }

        while (node2 != null) {
            resultNode = addNode(resultNode, node2.val);
            node2 = node2.next;
        }

        return resultNode;
    }


    private static void printList(ListNode head) {
        if (head != null) {
            System.out.print(head.val + " ");
            printList(head.next);
        }
    }
}
