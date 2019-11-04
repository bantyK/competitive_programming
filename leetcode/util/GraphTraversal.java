package src;

import java.util.*;

public class GraphTraversal {

    private class Edge {
        final int from;
        final int to;
        final double weight;


        private Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private List<Edge>[] createGraph(int V) {
        List<Edge>[] graph = new List[V];

        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        return graph;
    }

    private void addEdge(List<Edge>[] graph, int from, int to, double weight) {
        graph[from].add(new Edge(from, to, weight));
    }

    private List<Integer> bfs(List<Edge>[] graph, int V, int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            res.add(current);// process vertex

            List<Edge> edges = graph[current];
            for (Edge edge : edges) {
                if (!visited[edge.to]) {
                    queue.offer(edge.to);
                    visited[edge.to] = true;
                }
            }
        }
        return res;
    }

    private List<Integer> dfs(List<Edge>[] graph, int V, int start) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        List<Integer> res = new ArrayList<>();

        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            res.add(current);

            List<Edge> edges = graph[current];
            for (Edge edge : edges) {
                if (!visited[edge.to]) {
                    stack.push(edge.to);
                    visited[edge.to] = true;
                }
            }
        }

        return res;
    }

    private void dfs(List<Edge>[] graph, int vertex, boolean[] visited, List<Integer> res) {
        res.add(vertex);
        visited[vertex] = true;

        for (Edge edge : graph[vertex]) {
            if (!visited[edge.to]) {
                dfs(graph, edge.to, visited, res);
            }
        }
    }

    public static void main(String[] args) {
        GraphTraversal obj = new GraphTraversal();
        final int V = 5;
        List<Edge>[] graph = obj.createGraph(V);

        obj.addEdge(graph, 0, 1, 1);
        obj.addEdge(graph, 1, 2, 2);
        obj.addEdge(graph, 2, 3, 3);
        obj.addEdge(graph, 0, 4, 5);
        obj.addEdge(graph, 0, 2, 5);
        obj.addEdge(graph, 3, 0, 4);

        List<Integer> bfs = obj.bfs(graph, V, 0);
        System.out.println(bfs);

        List<Integer> dfs = obj.dfs(graph, V, 0);
        System.out.println(dfs);

        boolean[] visited = new boolean[V];
        List<Integer> res = new ArrayList<>();
        obj.dfs(graph, 3 , visited, res);
        System.out.println(res);
    }



}
