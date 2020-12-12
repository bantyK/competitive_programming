import java.util.*;

//256 https://leetcode.com/problems/paint-house/
public class PaintHouse {
    public static void main(String[] args) {
        PaintHouse obj = new PaintHouse();
        final int minCost = obj.minCost(new int[][]{{1,2,3}, {7, 2, 5}, {1, 3, 1}});
        System.out.println(minCost);
    }

    // top down DP
    public int minCost(int[][] costs) {
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        int end = costs.length - 1;
        return Math.min(costs[end][0], Math.min(costs[end][1], costs[end][2]));
    }


    // Bottom up
    public int minCost2(int[][] costs) {
        Integer[][] cache = new Integer[costs.length][3];
        return helper(costs, 0, -1, cache);
    }

    private int helper(int[][] costs, int houseIndex, int previouslyUsedIndex, Integer[][] cache) {
        if (houseIndex >= costs.length) {
            return 0;
        }

        if (previouslyUsedIndex != -1 && cache[houseIndex][previouslyUsedIndex] != null)
            return cache[houseIndex][previouslyUsedIndex];

        int minCost = Integer.MAX_VALUE;

        for (int j = 0; j < 3; j++) {
            if (j != previouslyUsedIndex) {
                int val = costs[houseIndex][j] + helper(costs, houseIndex + 1, j, cache);
                minCost = Math.min(minCost, val);
            }
        }
        if (previouslyUsedIndex != -1) {
            cache[houseIndex][previouslyUsedIndex] = minCost;
        }
        return minCost;
    }
}
