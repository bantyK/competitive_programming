import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

//684 https://leetcode.com/problems/redundant-connection/

public class RedundantConnection {
    public static void main(String[] args) throws IOException {
        RedundantConnection obj = new RedundantConnection();
        int[][] edges = new TwoDArrayReader().get2DArray();
        int[] res = obj.findRedundantConnection(edges);

        System.out.println(Arrays.toString(res));
    }

    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int numVertices = edges.length;

        for (int i = 1; i <= numVertices; i++) {
            graph.put(i, new LinkedList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            int[] edge = edges[i];

            graph.get(edge[0]).remove(Integer.valueOf(edge[1]));
            graph.get(edge[1]).remove(Integer.valueOf(edge[0]));

            for (int j = 1; j <= numVertices; j++) {
                boolean stillConnected = true;
                if (!allNodesConnected(graph, j, numVertices)) {
                    stillConnected = false;
                }

                if (stillConnected) return edge;
            }

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return new int[]{};
    }

    private boolean allNodesConnected(Map<Integer, List<Integer>> graph, int start, int numVertices) {
        boolean[] visited = new boolean[numVertices + 1];
        visited[0] = true;

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            visited[vertex] = true;
            List<Integer> edges = graph.get(vertex);
            if (edges != null) {
                for (int edge : edges) {
                    if (!visited[edge]) {
                        stack.push(edge);
                    }
                }
            }
        }

        for (boolean b : visited) {
            if (!b) return false;
        }

        return true;
    }

}
