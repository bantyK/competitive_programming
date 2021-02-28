import java.util.*;

//1522 https://leetcode.com/problems/diameter-of-n-ary-tree/
public class NArrayTreeDiameter {
    int ans;

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);

        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        Node node8 = new Node(8);

        node7.children = Arrays.asList(node8);
        node3.children = Arrays.asList(node5, node6, node7);
        root.children = Arrays.asList(node3, node2, node4);

        NArrayTreeDiameter obj = new NArrayTreeDiameter();
        System.out.println(obj.diameter(root));
    }

    public int diameter(Node root) {
        ans = 0;
        solve(root);
        return ans;
    }

    private int solve(Node root) {
        if (root == null) {
            return 0;
        }
        // keep maxHeight1 greater than maxHeight2
        int maxHeight1 = 0;
        int maxHeight2 = 0;
        int max = 0;
        for (Node child : root.children) {
            int height = solve(child);
            max = Math.max(height, max);

            if (height > maxHeight1) {
                maxHeight2 = maxHeight1;
                maxHeight1 = height;
            } else if (height > maxHeight2) {
                maxHeight2 = height;
            }
        }

        ans = Math.max(ans, maxHeight1 + maxHeight2);

        return 1 + max;
    }

    static class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }
}