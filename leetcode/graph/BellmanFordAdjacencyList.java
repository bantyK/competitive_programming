package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordAdjacencyList {

    private static class Edge {
        double cost;
        int from;
        int to;

        Edge(int from, int to, double cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }
    }

    private static List<Edge>[] createGraph(final int V) {
        List<Edge>[] graph = new List[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        return graph;
    }

    private static void addEdge(List<Edge>[] graph, int from, int to, double cost) {
        graph[from].add(new Edge(from, to, cost));
    }

    public static void main(String[] args) {
        int V = 5;
        int start = 0;

        List<Edge>[] graph = createGraph(V);

        addEdge(graph, 0, 1, 1);
        addEdge(graph, 0, 4, 2);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 2, 3, 5);
        addEdge(graph, 4, 3, 4);

        double[] distances = bellmanFord(graph, V, start);

        for (int i = 0; i < V; i++) {
            System.out.printf("The cost to get from node %d to %d is %.2f\n", start, i, distances[i]);
        }
    }

    private static double[] bellmanFord(List<Edge>[] graph, int numVertices, int start) {
        double[] distances = new double[numVertices];

        // Setting the distance of all the nodes from start vertex = 0
        Arrays.fill(distances, Double.POSITIVE_INFINITY);

        distances[start] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (List<Edge> edges : graph) {
                for (Edge edge : edges) {
                    if (distances[edge.to] > distances[edge.from] + edge.cost) {
                        distances[edge.to] = distances[edge.from] + edge.cost;
                    }
                }
            }
        }

        return distances;
    }
}
