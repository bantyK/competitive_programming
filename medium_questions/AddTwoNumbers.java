package leetcode;

public class AddTwoNumbers {

    public static void main(String[] args) {
        AddTwoNumbers obj = new AddTwoNumbers();
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);

        ListNode resultNode = obj.addTwoNumbers(l1, l2);

        obj.printList(resultNode);
    }

    private void printList(ListNode resultNode) {
        while (resultNode != null) {
            System.out.print(resultNode.val + "->");
            resultNode = resultNode.next;
        }
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum, carry = 0;
        ListNode result = null;
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val;
            if (carry > 0) {
                sum += carry;
                carry = 0;
            }
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            }
            result = addNode(result, sum);
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val;
            if (carry > 0) {
                sum += carry;
                carry = 0;
            }

            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            }
            addNode(result, sum);
            l1 = l1.next;
        }

        while (l2 != null) {
            sum = l2.val;
            if (carry > 0) {
                sum += carry;
                carry = 0;
            }

            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            }
            addNode(result, sum);
            l2 = l2.next;
        }

        if (carry > 0)
            addNode(result, carry);

        return result;
    }

    private ListNode addNode(ListNode result, int sum) {
        ListNode node = getNode(sum);
        if (result == null) {
            result = node;
        } else {
            ListNode temp = result;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        return result;
    }

    private ListNode getNode(int sum) {
        ListNode node = new ListNode(sum);
        node.next = null;

        return node;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}