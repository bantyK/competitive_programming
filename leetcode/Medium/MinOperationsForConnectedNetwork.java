import java.util.*;

// 1319 https://leetcode.com/problems/number-of-operations-to-make-network-connected/
public class MinOperationsForConnectedNetwork {
    public static void main(String[] args) {
        MinOperationsForConnectedNetwork obj = new MinOperationsForConnectedNetwork();
        int n = 4;
        int[][] components = {{0, 1}, {0, 2}, {1, 2}};

        final int res = obj.makeConnected(n, components);
        System.out.println(res);
    }

    /**
     * DFS
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected2(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] connection : connections) {
            addEdge(graph, connection[0], connection[1]);
            addEdge(graph, connection[1], connection[0]);
        }

        int components = 0;
        boolean[] visited = new boolean[n];
        for (int v = 0; v < n; v++) {
            components += dfs(v, graph, visited);
        }

        return components - 1;
    }

    private int dfs(int v, Map<Integer, List<Integer>> graph, boolean[] visited) {
        if (visited[v]) return 0;
        visited[v] = true;
        if (graph.get(v) != null) {
            for (int u : graph.get(v)) {
                dfs(u, graph, visited);
            }
        }

        return 1;
    }

    private void addEdge(Map<Integer, List<Integer>> graph, int v1, int v2) {
        graph.putIfAbsent(v1, new LinkedList<>());
        graph.get(v1).add(v2);
    }

    /**
     * Union find
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) parent[i] = i;

        int components = 0;

        // union
        for (int[] connection : connections) {
            int p1 = findParent(parent, connection[0]);
            int p2 = findParent(parent, connection[1]);
            parent[p1] = p2;
        }

        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                components++;
            }
        }

        return components - 1;
    }

    // find
    private int findParent(int[] parent, int i) {
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

}
