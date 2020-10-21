import java.util.*;

// 133 https://leetcode.com/problems/clone-graph/
public class CloneGraph {
    public static void main(String[] args) {
        CloneGraph obj = new CloneGraph();
    }

    // BFS implementation
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

	// DFS implementation
    private Node dfs(Node node, Map<Node, Node> cloneMap) {
        if (node == null) return null;

        if (cloneMap.containsKey(node)) return cloneMap.get(node);

        Node cloned = new Node(node.val);
        cloneMap.put(node, cloned);

        for (Node neigh : node.neighbors) {
            Node clonedNeighbor = dfs(neigh, cloneMap);
            cloned.neighbors.add(clonedNeighbor);
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
