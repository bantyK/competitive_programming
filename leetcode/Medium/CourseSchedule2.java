import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 210 https://leetcode.com/problems/course-schedule-ii/
public class CourseSchedule2 {
    public static void main(String[] args) throws IOException {
        CourseSchedule2 obj = new CourseSchedule2();
        int[][] input = new TwoDArrayReader().get2DArray();
        System.out.println(Arrays.toString(obj.findOrder(2, input)));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            addEdge(graph, pair[1], pair[0]);
        }

        if (isCyclic(graph, numCourses)) return new int[]{};
        return topologicalSort(graph, numCourses);

    }

    private int[] topologicalSort(Map<Integer, List<Edge>> graph, int vertices) {
        int[] ordering = new int[vertices];
        boolean[] visited = new boolean[vertices];

        int i = vertices - 1;

        for (int vertex = 0; vertex < vertices; vertex++) {
            if (!visited[vertex]) {
                i = dfs(i, vertex, visited, ordering, graph);
            }
        }

        return ordering;
    }

    private int dfs(int index, int vertex, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph) {
        visited[vertex] = true;

        List<Edge> edges = graph.get(vertex);

        if (edges != null) {
            for (Edge edge : edges) {
                if (!visited[edge.to]) {
                    index = dfs(index, edge.to, visited, ordering, graph);
                }
            }
        }

        ordering[index] = vertex;
        return index - 1;
    }

    private boolean isCyclic(Map<Integer, List<Edge>> graph, int numVertices) {
        boolean[] recStack = new boolean[numVertices];
        boolean[] visitedNodes = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            if (helper(graph, i, recStack, visitedNodes)) {
                return true;
            }
        }
        return false;
    }

    private boolean helper(Map<Integer, List<Edge>> graph, int i, boolean[] recStack, boolean[] visited) {
        if (recStack[i]) return true;

        if (visited[i]) return false;

        recStack[i] = true;
        visited[i] = true;

        for (Edge edge : graph.get(i)) {
            if (helper(graph, edge.to, recStack, visited)) {
                return true;
            }
        }
        recStack[i] = false;

        return false;
    }

    private void addEdge(Map<Integer, List<Edge>> graph, int from, int to) {
        graph.get(from).add(new Edge(from, to));
    }

    static class Edge {
        final int to;
        final int from;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
