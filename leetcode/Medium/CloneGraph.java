import java.util.*;

// 133 https://leetcode.com/problems/clone-graph/
public class CloneGraph {
    public static void main(String[] args) {
        CloneGraph obj = new CloneGraph();
    }

    public Node cloneGraph(Node node) {

        Map<Node, Node> cloneMap = new HashMap<>();

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        cloneMap.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for(Node neighbour : curr.neighbors) {
                if(!cloneMap.containsKey(neighbour)) {
                    cloneMap.put(neighbour, new Node(neighbour.val, new ArrayList<>()));
                    queue.add(neighbour);
                }

                cloneMap.get(curr).neighbors.add(cloneMap.get(neighbour));

            }

        }

        return cloneMap.get(node);
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
