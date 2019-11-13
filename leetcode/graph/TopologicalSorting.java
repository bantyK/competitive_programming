package graphtheory;

import solutions.util.ArrayUtils;

import java.util.*;

public class TopologicalSorting {

    public static void main(String[] args) {
        TopologicalSorting obj = new TopologicalSorting();

        final int N = 6;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < N; i++) graph.put(i, new ArrayList<>());
        graph.get(0).add(new Edge(0, 1, 3));
        graph.get(0).add(new Edge(0, 2, 2));
        graph.get(0).add(new Edge(0, 5, 3));
        graph.get(1).add(new Edge(1, 3, 1));
        graph.get(1).add(new Edge(1, 2, 6));
        graph.get(2).add(new Edge(2, 3, 1));
        graph.get(2).add(new Edge(2, 4, 10));
        graph.get(3).add(new Edge(3, 4, 5));
        graph.get(5).add(new Edge(5, 4, 7));

        int[] ordering = topologicalSort(graph, N);

        ArrayUtils.printArray(ordering);
    }

    private static int[] topologicalSort(Map<Integer, List<Edge>> graph, int numNodes) {
        int[] ordering = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        Stack<Integer> stack = new Stack<>();

        for (int at = 0; at < numNodes; at++) {
            if (!visited[at]) {
                dfs(at, visited, stack, graph);
            }
        }

        int i = 0;
        while (!stack.isEmpty()) {
            ordering[i++] = stack.pop();
        }

        return ordering;
    }

    private static void dfs(int at, boolean[] visited, Stack<Integer> stack, Map<Integer, List<Edge>> graph) {
        visited[at] = true;

        List<Edge> edges = graph.get(at);

        if (null != edges) {
            for (Edge edge : edges) {
                if (!visited[edge.to]) {
                    dfs(edge.to, visited, stack, graph);
                }
            }
        }

        stack.push(at);
    }

    static class Edge {
        final int to;
        final int from;
        final int weight;

        public Edge(int from, int to, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
    }


}
