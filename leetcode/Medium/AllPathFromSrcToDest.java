import java.util.*;

// 1059 https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
public class AllPathFromSrcToDest {
    public static void main(String[] args) {
        AllPathFromSrcToDest obj = new AllPathFromSrcToDest();
        // System.out.println(obj.leadsToDestination(3, new int[][] { { 0, 1 }, { 0, 2 }
        // }, 0, 2));
        System.out.println(obj.leadsToDestination(4, new int[][] { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 2, 1 } }, 0, 3));
        System.out.println(obj.leadsToDestination(4, new int[][] { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } }, 0, 3));
    }

    private int destination;
    private Map<Integer, List<Integer>> graph;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        this.destination = destination;
        this.graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            graph.get(src).add(dest);
        }

        // start a dfs from src;
        // If we find a loop return false;
        // If we reach a leaf node and if that node is not the destination node, return
        // false;
        Set<Integer> set = new HashSet<>();
        return dfs(set, source);
    }

    private boolean dfs(Set<Integer> seenNodes, int currentNode) {
        if (seenNodes.contains(currentNode)) {
            // found a cycle,
            return false;
        }

        if (graph.get(currentNode).size() == 0) {
            return currentNode == destination;
        }

        seenNodes.add(currentNode);
        for (int neighbour : graph.get(currentNode)) {
            boolean intermediateResult = dfs(seenNodes, neighbour);
            if (!intermediateResult) {
                return false;
            }
        }

        seenNodes.remove(currentNode);
        return true;
    }
}