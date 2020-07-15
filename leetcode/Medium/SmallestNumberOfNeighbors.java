package distance;

import java.util.*;

// 1334 https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
public class SmallestNumberOfNeighbors {
    public static void main(String[] args) {
        SmallestNumberOfNeighbors obj = new SmallestNumberOfNeighbors();
        int n = 5;
        int[][] edges = new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
        int distanceThreshold = 2;

        System.out.println(obj.findTheCity(n, edges, distanceThreshold));
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int POSITIVE_INFINITY = 100000;
        int[][] graph = new int[n][n];

        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], POSITIVE_INFINITY);
            graph[i][i] = 0;
        }

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        printArray(graph);
        int minNeighborsCount = POSITIVE_INFINITY;
        int minNeighborIndex = 0;
        int currentMinNeighbourCount;
        for (int i = 0; i < n; i++) {
            currentMinNeighbourCount = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] <= distanceThreshold) {
                    currentMinNeighbourCount++;
                }
            }

            if (currentMinNeighbourCount < minNeighborsCount) {
                minNeighborsCount = currentMinNeighbourCount;
                minNeighborIndex = i;
            } else if (currentMinNeighbourCount == minNeighborsCount && i > minNeighborIndex) {
                minNeighborIndex = i;
            }
        }

        return minNeighborIndex;
    }

    private void printArray(int[][] graph) {
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }

            System.out.println();
        }
        System.out.println();
    }
}
