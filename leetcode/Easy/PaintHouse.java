import java.util.*;

//256 https://leetcode.com/problems/paint-house/
public class PaintHouse {
    private int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) {
        PaintHouse obj = new PaintHouse();
        final int minCost = obj.minCost(new int[][]{{14, 2, 11}, {11, 14, 5}, {14, 3, 10}});
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
}

/*
        R  B  G
    H1 [14,2,11]
    H2 [11,14,5]
    H3 [14,3,10]

RBG, RGB, R

* */
