import input.TwoDArrayReader;

import java.util.*;

// 1377 https://leetcode.com/problems/frog-position-after-t-seconds/

public class FrogPositionProbabilityDFS {

    double ans;

    public static void main(String[] args) {
        FrogPositionProbabilityDFS obj = new FrogPositionProbabilityDFS();
        int[][] edges = new TwoDArrayReader().get2DArray();

        System.out.println(obj.frogPosition(7, edges, 2, 4));
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        ans = 0.0;

        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        dfs(graph, 1, target, 0, t, visited, 1.0);

        return ans;
    }


    private boolean dfs(Map<Integer, List<Integer>> graph, int currentVertex, int targetVertex, int currentTime, int totalTime, boolean[] visited, double prob) {
        if (targetVertex == currentVertex && currentTime == totalTime) {
            ans = prob;
            return true;
        }

        if (currentTime == totalTime) return false;

        visited[currentVertex] = true;

        double unvisited = 0;
        for (int node : graph.get(currentVertex)) {
            if (!visited[node]) {
                unvisited++;
            }
        }

        if (unvisited == 0) {
            if (currentVertex == targetVertex) {
                ans = prob;
                return true;
            }
            return false;
        }

        for (int child : graph.get(currentVertex)) {
            if (visited[child]) continue;
            boolean reachedTarget = dfs(graph, child, targetVertex, currentTime + 1, totalTime, visited, prob * (1 / unvisited));
            if (reachedTarget) return true;
        }
        return false;
    }

}