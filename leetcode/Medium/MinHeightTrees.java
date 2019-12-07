import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//310: https://leetcode.com/problems/minimum-height-trees/


/**
 * We are basically looking at the node which is at the center of the graph(tree).
 * Start from the leaf node and keep removing them from the tree.
 * Keep removing the leaf nodes once there are no nodes left.
 * The last pair of nodes are the nodes which are at the center and they are the result.
* */
public class MinHeightTrees {
    public static void main(String[] args) throws IOException {
        MinHeightTrees obj = new MinHeightTrees();
        int n = 6;
        int[][] edges = new TwoDArrayReader().get2DArray();
        List<Integer> heights = obj.findMinHeightTrees(n, edges);

        System.out.println(heights);
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) return new ArrayList<>();

        if (n == 1) return Collections.singletonList(0);

        Map<Integer, List<Integer>> graph = prepareGraph(n, edges);
        int[] degrees = getInDegrees(n, edges);
        Queue<Integer> queue = new LinkedList<>();

        // we will start with the leaf nodes(with degree 1)
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int len = queue.size();

            // the values which res contain on the last iteration are the final roots with minimum height
            res = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                int current = queue.poll();
                res.add(current);

                for (int v : graph.get(current)) {
                    // We are removing the leaf nodes, so all the nodes which are connected to the leaf node will have one degree less
                    degrees[v] -= 1;
                    if (degrees[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }


        return res;
    }


    private Map<Integer, List<Integer>> prepareGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }

    private int[] getInDegrees(int n, int[][] edges) {
        int[] degrees = new int[n];

        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }

        return degrees;
    }

    private int bfs(Map<Integer, List<Integer>> graph, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int height = 0;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                int current = queue.poll();
                visited[current] = true;

                for (int node : graph.get(current)) {
                    if (!visited[node])
                        queue.offer(node);
                }
            }

            height += 1;
        }

        return height;
    }
}
