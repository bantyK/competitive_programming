package solutions.medium;

import java.util.*;

//983: https://leetcode.com/problems/minimum-cost-for-tickets/
public class MinTicketCost {
    public static void main(String[] args) {
        MinTicketCost obj = new MinTicketCost();
        int[] days = new int[]{1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = new int[]{2, 7, 15};
        int minAmount = mincostTickets(days, costs);
        System.out.println(minAmount);

    }

    /**
    * Improved version. Time 2ms
    */
    public int mincostTickets2(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        Set<Integer> uniqueDays = new HashSet<>();
        for (int day : days) uniqueDays.add(day);
        int cost1 = 0, cost2 = 0, cost3 = 0;
        for (int i = 1; i <= lastDay; i++) {
            if (uniqueDays.contains(i)) {
                if (i - 1 >= 0) {
                    cost1 = dp[i - 1] + costs[0];
                }

                if (i - 7 >= 0) {
                    cost2 = dp[i - 7] + costs[1];
                } else {
                    cost2 = dp[0] + costs[1];
                }

                if (i - 30 >= 0) {
                    cost3 = dp[i - 30] + costs[2];
                } else {
                    cost3 = dp[0] + costs[2];
                }

                dp[i] = Math.min(Math.min(cost1, cost2), cost3);
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[lastDay];

    }


    public static int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] dp = new int[lastDay + 1];
        Set<Integer> uniqueDays = new HashSet<>();
        for (int day : days) {
            uniqueDays.add(day);
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, costs[0]);
        map.put(7, costs[1]);
        map.put(30, costs[2]);

        for (int i = 1; i < dp.length; i++) {
            if (uniqueDays.contains(i)) {
                for (int key : map.keySet()) {
                    if (i - key < 0) {
                        dp[i] = Math.min(dp[i], map.get(key) + dp[0]);
                    } else {
                        dp[i] = Math.min(dp[i], map.get(key) + dp[i - key]);
                    }
                }
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[lastDay];
    }
}
