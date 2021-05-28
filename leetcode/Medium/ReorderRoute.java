import java.util.*;

// 1466 https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
public class ReorderRoute {

    int count;
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Set<Integer>> edgePresent = new HashMap<>();

    public static void main(String[] args) {
        int n = 7;
        int[][] connections = new int[][]{{0, 1}, {1, 2}, {2, 3}, {4, 2}, {5, 1}, {6, 0}};

        System.out.println(new ReorderRoute().minReorder(n, connections));
    }

    public int minReorder(int n, int[][] connections) {
        // Start DFS from 0
        // While exploring the node, if there is a path from from -> to
        // that means that path needs to be reversed
        // if there is no path, meaning the direction is already reversed,
        // in this case, dont increment the count
        count = 0;

        graph = new HashMap<>();
        edgePresent = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
            edgePresent.put(i, new HashSet<>());
        }

        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];

            graph.get(from).add(to);
            graph.get(to).add(from);

            edgePresent.get(from).add(to);
        }

        dfs(0, -1);

        return count;
    }

    private void dfs(int vertex, int parent) {
        for (int child : graph.get(vertex)) {
            if (child != parent) {
                if (edgePresent.get(vertex).contains(child)) {
                    count++;
                }
                dfs(child, vertex);
            }
        }
    }

}