import java.util.*;

// 1485 https://leetcode.com/problems/clone-binary-tree-with-random-pointer/
public class CloneTreeWithRandomPointer {

    // 1 pass solution
    public NodeCopy copyRandomBinaryTree2(Node root) {
        if (root == null) return null;
        Map<Node, NodeCopy> map = new HashMap<>();
        return cloneTree(root, map);
    }

    private NodeCopy cloneTree(Node root, Map<Node, NodeCopy> map) {
        if (root == null) return null;

        if (map.containsKey(root)) return map.get(root);

        NodeCopy rootCopy = new NodeCopy(root.val);
        map.put(root, rootCopy);

        rootCopy.left = cloneTree(root.left, map);
        rootCopy.right = cloneTree(root.right, map);
        rootCopy.random = cloneTree(root.random, map);

        return map.get(root);
    }


    // 2 pass of the tree
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> cloneMap = new HashMap<>();
        cloneNodes(root, cloneMap);

        attachPointers(root, cloneMap);

        return cloneMap.get(root);
    }

    private void attachPointers(Node root, Map<Node, NodeCopy> map) {
        if (root == null) return;

        map.get(root).left = map.get(root.left);
        map.get(root).right = map.get(root.right);
        map.get(root).random = map.get(root.random);

        attachPointers(root.left, map);
        attachPointers(root.right, map);
    }

    private void cloneNodes(Node root, Map<Node, NodeCopy> map) {
        if (root == null) return;
        map.put(root, new NodeCopy(root.val));
        cloneNodes(root.left, map);
        cloneNodes(root.right, map);
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }

    private static class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;

        NodeCopy() {
        }

        NodeCopy(int val) {
            this.val = val;
        }

        NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.random = random;
        }
    }
}
