package models;

import java.util.*;

public class DjikstraAlgorithm {

    // Small epsilon value to comparing double values.
    private static final double EPS = 1e-6;


    public static void main(String[] args) {
        DjikstraAlgorithm obj = new DjikstraAlgorithm();

        int V = 5;
        int start = 0;

        List<Edge>[] graph = createGraph(V);

        addEdge(graph, 0, 1, 1);
        addEdge(graph, 0, 4, 2);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 2, 3, 5);
        addEdge(graph, 4, 3, 4);

        double[] distances = djikstra(graph, V, start);

        for (int i = 0; i < V; i++) {
            System.out.printf("The cost to get from node %d to %d is %.2f\n", start, i, distances[i]);
        }
    }

    private static class EdgeDistanceSet {
        int id;
        double distance;

        public EdgeDistanceSet(int id, double distance) {
            this.id = id;
            this.distance = distance;
        }
    }

    private static class Edge {
        final int from;
        final int to;
        double cost;

        Edge(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static List<Edge>[] createGraph(int V) {
        List<Edge>[] graph = new List[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        return graph;
    }

    private static void addEdge(List<Edge>[] graph, int from, int to, double cost) {
        graph[from].add(new Edge(from, to, cost));
    }

    private static Comparator<EdgeDistanceSet> comparator = new Comparator<EdgeDistanceSet>() {
        @Override
        public int compare(EdgeDistanceSet s1, EdgeDistanceSet s2) {
            if (Math.abs(s1.distance - s2.distance) < EPS) return 0;
            else return s1.distance - s2.distance > 0 ? 1 : -1;
        }
    };

    private static double[] djikstra(List<Edge>[] graph, int V, int start) {
        double[] distances = new double[V];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[start] = 0;

        PriorityQueue<EdgeDistanceSet> pq = new PriorityQueue<>(comparator);
        pq.offer(new EdgeDistanceSet(start, 0));

        boolean visited[] = new boolean[V];

        while (!pq.isEmpty()) {
            EdgeDistanceSet set = pq.poll();
            visited[set.id] = true;

            List<Edge> edges = graph[set.id];

            for (Edge edge : edges) {
                if (visited[edge.to]) continue;

                double newDist = distances[edge.from] + edge.cost;
                if (newDist < distances[edge.to]) {
                    distances[edge.to] = newDist;
                    pq.offer(new EdgeDistanceSet(edge.to, newDist));
                }
            }
        }

        return distances;

    }

}
