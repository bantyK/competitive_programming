import input.TreeNode;

// 426 https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class BSTreeToSortedDLL {
    public static void main(String[] args) {
        BSTreeToSortedDLL obj = new BSTreeToSortedDLL();

        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(1);
//        root.left.right = new TreeNode(3);

        TreeNode res = obj.treeToDoublyList(root);
        System.out.println(res);
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            link(root, root);
            return root;
        }
        TreeNode leftHead = treeToDoublyList(root.left);
        TreeNode rightHead = treeToDoublyList(root.right);
        TreeNode leftTail = leftHead != null ? leftHead.left : null;
        TreeNode rightTail = rightHead != null ? rightHead.left : null;

        TreeNode head = null;

        if (rightHead == null) {
            head = leftHead;
            link(leftTail, root);
            link(root, leftHead);
        } else if (leftHead == null) {
            head = root;
            link(root, rightHead);
            link(rightTail, root);
        } else {
            head = leftHead;
            link(leftTail, root);
            link(root, rightHead);
            link(rightTail, leftHead);
        }

        return head;
    }

    private void link(TreeNode nodeA, TreeNode nodeB) {
        nodeA.right = nodeB;
        nodeB.left = nodeA;
    }
}
