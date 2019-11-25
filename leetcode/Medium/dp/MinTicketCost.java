package solutions.medium;

import java.util.*;

public class MinTicketCost {
    public static void main(String[] args) {
        MinTicketCost obj = new MinTicketCost();
        int[] days = new int[]{1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = new int[]{2, 7, 15};
        int minAmount = mincostTickets(days, costs);
        System.out.println(minAmount);

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
