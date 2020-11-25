import input.TwoDArrayReader;

import java.util.*;

//1615 https://leetcode.com/problems/maximal-network-rank/
public class MaximalNetworkRank {

    public static void main(String[] args) {
        int[][] roads = new TwoDArrayReader().get2DArray();
        System.out.println(new MaximalNetworkRank().maximalNetworkRank(9, roads));
    }


    public int maximalNetworkRank2(int n, int[][] roads) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int total = graph.get(i).size();
                total += graph.get(j).size();
                if (graph.get(i).contains(j)) total--;

                maxRank = Math.max(maxRank, total);
            }
        }
        return maxRank;
    }

    // Same approach but little optimised
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connected = new boolean[n][n];
        int[] count = new int[n];

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];

            connected[from][to] = true;
            connected[to][from] = true;

            count[to]++;
            count[from]++;
        }

        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int total = count[i] + count[j];
                if (connected[i][j]) total--;
                maxRank = Math.max(maxRank, total);
            }
        }
        return maxRank;
    }

}