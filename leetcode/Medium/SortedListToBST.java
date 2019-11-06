package solutions.medium;

import models.ListNode;
import models.TreeNode;
import solutions.util.BinaryTreeUtils;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
public class SortedListToBST {
    public static void main(String[] args) {
        SortedListToBST obj = new SortedListToBST();
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        TreeNode root = obj.sortedListToBST(head);
        BinaryTreeUtils.inorderTraversal(root);
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> nodes = getListOfValues(head);
        return constructBinaryTree(nodes, 0, nodes.size() - 1);
    }

    private TreeNode constructBinaryTree(List<Integer> values, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(values.get(mid));
        root.left = constructBinaryTree(values, left, mid - 1);
        root.right = constructBinaryTree(values, mid + 1, right);
        return root;
    }

    private List<Integer> getListOfValues(ListNode head) {
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }

        return values;
    }

}
