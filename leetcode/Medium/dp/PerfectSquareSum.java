import java.util.*;

// 279 https://leetcode.com/problems/perfect-squares/
public class PerfectSquareSum {

    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int i = new PerfectSquareSum().numSquares(12);
        System.out.println(i);
    }

    import java.util.*;

public class PerfectSquares {

    public static void main(String[] args) {
        PerfectSquares obj = new PerfectSquares();
        System.out.println(obj.numSquares(13));
        System.out.println(obj.numSquares(15));
        System.out.println(obj.numSquares(18));
        System.out.println(obj.numSquares(49));
        System.out.println(obj.numSquares(99));
        System.out.println(obj.numSquares(5));
        System.out.println(obj.numSquares(5));
    }

    public int numSquares(int n) {
        Map<String, Integer> dp = new HashMap<>();
        int[] perfectSquares = getSquares(n);
        return helper(perfectSquares, n);
    }

    private int helper(int[] nums, int remaining) {
        int n = nums.length;
        int[][] dp = new int[n][remaining + 1];

        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= remaining; ++j) {
                if(i > 0) {
                    dp[i][j] = dp[i-1][j];
                }

                if(j >= nums[i]) {
                    if(dp[i][j - nums[i]] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - nums[i]]);
                    }
                }
            }
        }
        return dp[n-1][remaining];
    }

    private int[] getSquares(int n) {
        int[] res = new int[(int) Math.sqrt(n)];
        int idx = 0;
        for (int i = 1; i <= n; ++i) {
            if (isPerfectSquare(i)) {
                res[idx++] = i;
            }
        }

        return res;
    }


    private boolean isPerfectSquare(int x) {
        double sr = Math.sqrt(x);
        return ((sr - Math.floor(sr)) == 0);
    }
}

}
