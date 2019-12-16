import java.util.*;
// 207 https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for(int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        for(int[] pair : prerequisites) {
            addEdge(graph, pair[0], pair[1]);
        }

        return !isCyclic(graph, numCourses, 0);
    }

    private boolean isCyclic(Map<Integer, List<Edge>> graph, int numVertices, int start) {
        boolean[] recStack = new boolean[numVertices];
        boolean[] visitedNodes = new boolean[numVertices];
        for(int i = 0; i < numVertices; i++) {
            if(helper(graph, i, recStack, visitedNodes)) {
                return true;
            }
        }
        return false;
    }

    private boolean helper(Map<Integer, List<Edge>> graph, int i, boolean[] recStack, boolean[] visited) {
        if(recStack[i]) return true;

        if(visited[i]) return false;

        recStack[i] = true;
        visited[i] = true;

        for(Edge edge : graph.get(i)) {
            if(helper(graph, edge.to, recStack, visited)) {
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
