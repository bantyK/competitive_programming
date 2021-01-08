// 1411 https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
public class NumWaysToPrintGrid {
    public static void main(String[] args) {
        NumWaysToPrintGrid obj = new NumWaysToPrintGrid();
        System.out.println(obj.numOfWays(5));
    }

    public int numOfWays(int n) {
        int[][][][] dp = new int[n + 1][4][4][4];
        return helper(n, 0, 0, 0, dp);
    }

    /**
     * @param n  number of rows
     * @param a0 color of first column of previous row
     * @param b0 color of second column of previous row
     * @param c0 color of third column of previous row
     * @param dp dp table to store intermediate rows
     * @return the min number of ways to color the entire grid
     */
    private int helper(int n, int a0, int b0, int c0, int[][][][] dp) {
        if (n == 0) return 1;

        if (dp[n][a0][b0][c0] != 0) return dp[n][a0][b0][c0];

        int ans = 0;
        int[] colors = {1, 2, 3}; // Red: 1, Yellow: 2, Green: 3
        for (int a : colors) {
            // filling the first column with color a
            // if it is same as the color of prev row, we wont use it
            if (a == a0) continue;
            for (int b : colors) {
                // filling the second column with color b
                // the prev row is painted with b0 and the left column is filled with color a
                // so we can't use this color for this column
                if (a == b || b == b0) continue;
                for (int c : colors) {
                    // filling the third column with color c
                    // the third column of the prev row is painted with b and column to its left is painted with c0
                    // so we can't use those 2 colors.
                    if (b == c || c == c0) continue;

                    ans += helper(n - 1, a, b, c, dp); // found a valid color combo, use it and go to the next row
                    ans %= 1000000007;
                }
            }
        }
        return dp[n][a0][b0][c0] = ans;
    }

    // Optimised solution: Constant space
    public int numOfWays2(int n) {
        int MOD = 1000000007;
        // with n = 1(one row), there be total 12 combinations
        // out of these 6 will have 2 colors
        // and 6 will have 3 colors
        long twoColors = 6L;
        long threeColors = 6L;
        
        for(int i = 2; i <= n; i++) {
            // these are arrangements for the row above the current row
            long oldTwoColorArrangementCount = twoColors;
            long oldThreeColorArrangementCount = threeColors;
            
            twoColors =(3 * oldTwoColorArrangementCount + 2 * oldThreeColorArrangementCount) % MOD;
			threeColors =(2 * oldTwoColorArrangementCount + 2 * oldThreeColorArrangementCount) % MOD;
        }
        
        return (int)((twoColors + threeColors) % MOD);
    }
}
}
