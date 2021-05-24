import java.util.*;

//428 https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
public class SerializeDeserialiseNAryTree {

    public static void main(String[] args) {
        Codec obj = new Codec();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.children = Arrays.asList(node2, node3, node4);
        node3.children = Arrays.asList(node5, node6);

        String serialize = obj.serialize(node1);
        System.out.println(serialize);
        Node deserialize = obj.deserialize(serialize);
        System.out.println(deserialize);
    }

    static class Codec {
        private final static String SEPERATOR = ",";

        public String serialize(Node root) {
            StringBuilder builder = new StringBuilder();
            serialiseHelper(root, builder);
            return builder.toString();
        }

        private void serialiseHelper(Node root, StringBuilder builder) {
            if (root == null) {
                builder.append("null").append(SEPERATOR);
                return;
            }
            builder.append(root.val).append(SEPERATOR);
            int childCount = root.children.size();
            builder.append(childCount).append(SEPERATOR);
            for (int i = 0; i < childCount; i++) {
                serialiseHelper(root.children.get(i), builder);
            }
        }


        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            Queue<String> queue = new LinkedList<>();
            queue.addAll(Arrays.asList(data.split(SEPERATOR)));
            return deserializeHelper(queue);
        }

        private Node deserializeHelper(Queue<String> queue) {
            String top = queue.poll();
            if ("null".equals(top)) {
                return null;
            }
            int rootVal = Integer.parseInt(top);
            int childCount = Integer.parseInt(queue.poll());
            Node root = new Node(rootVal);
            root.children = new ArrayList<>();
            for (int i = 0; i < childCount; i++) {
                root.children.add(deserializeHelper(queue));
            }

            return root;
        }

    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}