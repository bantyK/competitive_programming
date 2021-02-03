import java.util.*;

//261 https://leetcode.com/problems/graph-valid-tree/
public class GraphValidTree {

    public static final int NON_VISITED = 0;
    public static final int SEEN = 1;
    public static final int VISITED = 2;

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int n = 5;
        System.out.println(new GraphValidTree().validTree(n, edges));
    }

    // there should be only 1 connected component and nno cycle
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        int[] parents = new int[n];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            parents[edge[0]] = edge[1];
            parents[edge[1]] = edge[0];
        }

        int[] seen = new int[n];
        boolean containsCycle = containsCycle(graph, 0, -1, seen);
        if (containsCycle) {
            return false;
        }

        // At this point the graph does not contain a cycle, now we just need to test if it is a single connected graph
        for (int i = 0; i < n; i++) {
            // if the graph is single connected graph, then all the vertices will be visited in a single DFS
            if (seen[i] == NON_VISITED) {
                return false;
            }
        }

        return true;
    }

    // returns true if there is a cycle
    private boolean containsCycle(Map<Integer, List<Integer>> graph, int currentVertex, int parent, int[] seen) {
        if (seen[currentVertex] == SEEN) {
            return true;
        }

        if (seen[currentVertex] == VISITED) {
            return false;
        }


        seen[currentVertex] = SEEN;

        for (int neigh : graph.get(currentVertex)) {
            if (neigh != parent) {
                boolean cycleExist = containsCycle(graph, neigh, currentVertex, seen);
                if (cycleExist) return true;
            }
        }

        seen[currentVertex] = VISITED;
        return false;
    }

    ///// Using Union Find /////
    public boolean validTree2(int n, int[][] edges) {
        int[] parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for(int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            if(union(parents, x, y)) {
                return false;
            }
        }

        // If the graph is a single connected component then the number of edges will be n - 1
        return edges.length == n - 1;

    }


    private int find(int[] parents, int k) {
        if (parents[k] == k) {
            return k;
        }
        return parents[k] = find(parents, parents[k]);
    }

    private boolean union(int[] parents, int x, int y) {
        int parentX = find(parents, x);
        int parentY = find(parents, y);
        if (parentX == parentY) {
            return true;
        }
        parents[parentX] = parentY;
        return false;
    }


}
