package atcoder;

import java.util.*;

//https://atcoder.jp/contests/dp/tasks/dp_g
public class LongestPath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int numVertices = Integer.parseInt(line[0]);
        int numEdges = Integer.parseInt(line[1]);

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numEdges; i++) {
            line = scanner.nextLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        int[] max = new int[numVertices + 1];
        boolean[] visited = new boolean[numVertices + 1];

        for (int i = 1; i <= numVertices; i++) {
            if (!visited[i]) {
                dfs(graph, i, max, visited);
            }
        }

        int ans = 0;
        for (int num : max) {
            ans = Math.max(ans, num);
        }
        System.out.println(ans);
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int vertex, int[] max, boolean[] visited) {
        visited[vertex] = true;
        if (graph.containsKey(vertex)) {
            for (int child : graph.get(vertex)) {
                if (!visited[child]) {
                    dfs(graph, child, max, visited);
                }
                max[vertex] = Math.max(max[vertex], 1 + max[child]);
            }
        }
    }
}
