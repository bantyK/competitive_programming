package graphtheory;

import java.lang.reflect.Array;
import java.util.*;

// This implementation returns the shortest path between two vertex of a graph using Dijkstra's shortest path algorithm
public class ShortestPath {

    // Small epsilon value to comparing double values.
    private final double EPS = 1e-6;

    private int n; // number of vertices in the graph

    private List<Edge>[] graph;

    private Integer[] prev;

    private ShortestPath(int V) {
        this.n = V;
        createEmptyGraph();
    }

    private class Edge {
        final int from;
        final int to;
        final double cost;

        Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private class Node {
        int id;
        double value;

        Node(int id, double value) {
            this.id = id;
            this.value = value;
        }
    }

    private void createEmptyGraph() {
        graph = new List[n];

        prev = new Integer[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

    }

    private Comparator<Node> comparator = (n1, n2) -> {
        if (Math.abs(n1.value - n2.value) < EPS) return 0;
        else return n1.value - n2.value > 0 ? 1 : -1;
    };

    private void addEdge(int from, int to, int cost) {
        graph[from].add(new Edge(from, to, cost));
    }

    private double dijkstra(int start, int end) {
        boolean[] visited = new boolean[n];
        double[] distances = new double[n];

        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[start] = 0;

        Arrays.fill(prev, null);

        PriorityQueue<Node> pq = new PriorityQueue<>(comparator);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.id] = true;

            if (distances[node.id] < node.value) continue;

            List<Edge> edges = graph[node.id];

            for (Edge edge : edges) {
                if (visited[edge.to]) continue;

                double newDist = distances[edge.from] + edge.cost;
                if (newDist < distances[edge.to]) {
                    prev[edge.to] = edge.from;
                    distances[edge.to] = newDist;
                    pq.offer(new Node(edge.to, newDist));
                }
            }

            if (node.id == end) {
                return distances[end];
            }
        }

        return Double.POSITIVE_INFINITY; // the end node is unreachable
    }

    private List<Integer> reconstructPath(int start, int end) {
        double distance = dijkstra(start, end);
        List<Integer> path = new ArrayList<>();
        if (distance == Double.POSITIVE_INFINITY) {
            return path;
        }
        for (Integer at = end; at != null; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        ShortestPath obj = new ShortestPath(5);

        obj.addEdge(0, 1, 1);
        obj.addEdge(0, 4, 2);
        obj.addEdge(1, 2, 3);
        obj.addEdge(2, 3, 1);
        obj.addEdge(4, 3, 4);

        System.out.println(obj.dijkstra(0, 3));

        for(Integer path : obj.reconstructPath(0, 3)) {
            System.out.print(path + " ");
        }

    }
}
