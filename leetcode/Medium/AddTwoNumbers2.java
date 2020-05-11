import input.ListNode;
import java.util.*;

// 445 https://leetcode.com/problems/add-two-numbers-ii/
public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers obj = new AddTwoNumbers();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        populateStack(l1, stack1);
        populateStack(l2, stack2);

        ListNode head = null, tail = null;
        int sum, num1, num2 = 0, carry = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            num1 = stack1.isEmpty() ? 0 : stack1.pop();
            num2 = stack2.isEmpty() ? 0 : stack2.pop();
            // System.out.print(num1 + " + ");
            // System.out.print(num2 + " = ");

            sum = num1 + num2 + carry;
            carry = 0;

            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            }

            // System.out.println(sum);
            // System.out.println("---");


            if (head == null) {
                head = new ListNode(sum);

            } else {
                ListNode node = new ListNode(sum);
                node.next = head;
                head = node;
            }
        }

        if(carry == 1) {
            ListNode node = new ListNode(carry);
            node.next = head;
            head = node;
        }

        return head;
    }

    private void populateStack(ListNode list, Stack<Integer> stack) {
        if (list == null) return;
        stack.push(list.val);
        populateStack(list.next, stack);
    }
}
