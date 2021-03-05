import java.util.Arrays;

// 1478 https://leetcode.com/problems/allocate-mailboxes/
// Check leetcode solutions for solutions using preComputedMedians and without using preComputedMedians
public class AllocateMailboxes {
    // We cannot cannot take INT_MAX as the max value because we are adding distance into
    // this value. If we take INT_MAX and add something to it, it will become -INT_MAX and taking min
    // will always return INT_MIN
    private static final int MAX = 10000000;
    private int[][] preComputedMedians;

    public static void main(String[] args) {
        AllocateMailboxes obj = new AllocateMailboxes();
        System.out.println(obj.minDistance(new int[]{1, 4, 8, 10, 20}, 3));
    }

    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);
        Integer[][] dp = new Integer[n][k + 1];
        preComputedMedians = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int medianIndex = (i + j) / 2;
                int medianHouse = houses[medianIndex];
                int sum = 0;
                // considering the houses from index = start to index = end
                // for this range of houses, the best place to put a mailbox is to put it at the median house
                for (int x = i; x <= j; x++) {
                    sum += Math.abs(medianHouse - houses[x]);
                }

                preComputedMedians[i][j] = sum;
            }
        }
        return solve(0, k, houses, dp);
    }

    private int solve(int start, int k, int[] houses, Integer[][] dp) {
        if (start == houses.length) {
            return 0;
        }

        if (k == 0) {
            // houses are not finished, but mail boxes have, so this is not a valid answer
            return MAX;
        }

        if (dp[start][k] != null) return dp[start][k];

        int res = MAX;

        for (int pos = start; pos < houses.length; pos++) {
            // consider the houses from start to pos.
            int distance = preComputedMedians[start][pos];
            System.out.println(distance);
            res = Math.min(res, distance + solve(pos + 1, k - 1, houses, dp));
        }

        dp[start][k] = res;

        return dp[start][k];
    }

    private int calcDistance(int[] houses, int start, int end) {
        // considering the houses from index = start to index = end
        // for this range of houses, the best place to put a mailbox is to put it at the median house
        int medianIndex = (start + end) / 2;
        int medianHouse = houses[medianIndex];

        int distance = 0;
        for (int i = start; i <= end; i++) {
            distance += Math.abs(houses[i] - medianHouse);
        }
        return distance;
    }
}