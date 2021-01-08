//1420 https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/
public class BuildArrayWithMaxKComparison {

    public static void main(String[] args) {
        BuildArrayWithMaxKComparison obj = new BuildArrayWithMaxKComparison();
        System.out.println(obj.numOfArrays(3, 3, 2));
    }

    public int numOfArrays(int n, int m, int k) {
        Integer[][][] dp = new Integer[n + 1][m + 1][k + 1];
        return dfs(n, m, k, 0, 0, 0, dp);
    }


    private int dfs(int n, int m, int k, int i, int currMax, int currCost, Integer[][][] dp) {
        if (i == n) {
            return currCost == k ? 1 : 0;
        }

        if (dp[i][currMax][currCost] != null) return dp[i][currMax][currCost];

        int ans = 0;
        ans += (long) currMax * dfs(n, m, k, i + 1, currMax, currCost, dp) % 1_000_000_007;

        if (currCost + 1 <= k) {
            for (int num = currMax + 1; num <= m; num++) {
                ans += dfs(n, m, k, i + 1, num, currCost + 1, dp);
                ans %= 1000000007;
            }
        }

        dp[i][currMax][currCost] = ans;
        return dp[i][currMax][currCost];
    }


    // this is a slower version
    private int helper(int totalElements, int maxValue, int totalCostAllowed, int index, int currentMax,
                       int currentCost, Integer[][][] dp) {
        if (index == totalElements) {
            // if have an array of n elements. Check if this array has been formed with K
            // comparisons
            if (currentCost == totalCostAllowed) {
                return 1; // this is a valid array
            } else {
                return 0; // not a valid array
            }
        }

        if (dp[index][currentMax][currentCost] != null) {
            return dp[index][currentMax][currentCost];
        }

        int ans = 0;

        for (int num = 1; num <= maxValue; num++) {
            int newMax = currentMax;
            int newCost = currentCost;

            if (num > currentMax) {
                newMax = num;
                newCost++;
            }

            if (newCost > totalCostAllowed) {
                break; // only k comparisons are allowed. If we have made more comparisons here, then
                // it will never be a valid array. We can early break
            }

            // we have a valid combination, meaning for index = index, we have found a
            // value, lets find the value for index + 1
            // we will do this until index becomes equal to n, going this way
            ans += helper(totalElements, maxValue, totalCostAllowed, index + 1, newMax, newCost, dp);
            ans %= 1000000007;
        }

        dp[index][currentMax][currentCost] = ans;
        return dp[index][currentMax][currentCost];
    }
}
