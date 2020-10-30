//510 https://leetcode.com/problems/inorder-successor-in-bst-ii/
public class BSTSuccessor2 {
    public Node inorderSuccessor(Node node) {
        if(node.right != null) {
            return minInRightSubtree(node.right);
        }

        Node parent = node;
        while(parent != null) {
            parent = node.parent;
            if(parent != null && parent.left == node) return parent;
            else {
                node = parent;
            }
        }
        return null;
    }

    private Node minInRightSubtree(Node root) {
        if(root.left == null) return root;
        return minInRightSubtree(root.left);
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        public Node(int x) {
            val = x;
        }
    }
}