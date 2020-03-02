import input.ListNode;
import input.TreeNode;

// 1367 https://leetcode.com/problems/linked-list-in-binary-tree/
public class LinkedListInBinaryTree {

    public boolean isSubPath(ListNode head, TreeNode root) {
        if(head == null) return true;
        if(root == null) return false;

        if(root.val == head.val) {
            return isSubPath(head.next, root.left) || isSubPath(head.next, root.right);
        }

        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

}
