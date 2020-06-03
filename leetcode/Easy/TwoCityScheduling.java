import input.TwoDArrayReader;

import java.util.*;

// 1029 https://leetcode.com/problems/two-city-scheduling/

public class TwoCityScheduling {
    public static void main(String[] args) {
        TwoCityScheduling obj = new TwoCityScheduling();
        int[][] arr = new TwoDArrayReader().get2DArray();
        System.out.println(obj.twoCitySchedCost(arr));
    }

    public int twoCitySchedCost(int[][] costs) {
        // Only half of the people will be sent to A and rest half has to be sent to B, no matter the cost.
        // Hence to decrease the cost, we have to choose those intervals for which the difference between costA and costB is maximum. Because that will
        // only minimize the difference between the other half.

        // The selection should be such that the price of B corresponding to those A should be as higher than A as possible
        // (ie, B-A should be as high as possible) so (Avoiding that B is most beneficial for us
        Arrays.sort(costs, (a1, a2) -> {
            int diff1 = a1[1] - a1[0];
            int diff2 = a2[1] - a2[0];

            return diff2 - diff1;
        });

        int n = costs.length;
        int cost = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2)
                cost += costs[i][0];
            else cost += costs[i][1];
        }

        return cost;
    }
}
