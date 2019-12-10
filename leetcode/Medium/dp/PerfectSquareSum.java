import java.util.*;

// 279 https://leetcode.com/problems/perfect-squares/
public class PerfectSquareSum {

    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int i = new PerfectSquareSum().numSquares(12);
        System.out.println(i);
    }

    public int numSquaresBrute(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            perfectSquares.add(i * i);
        }
        List<Integer> current = new ArrayList<>();
        solve(perfectSquares, current, 0, n);
        return res;
    }

    private void solve(List<Integer> perfectSquares, List<Integer> current, int index, int currentSum) {
        if (currentSum == 0) {
            System.out.println(current);
            int n = current.size();
            res = Math.min(res, n);
            return;
        }

        for (int i = index; i < perfectSquares.size(); i++) {
            if (perfectSquares.get(i) <= currentSum) {
                current.add(perfectSquares.get(i));
                solve(perfectSquares, current, i, currentSum - perfectSquares.get(i));
                current.remove(current.size() - 1);
            }
        }
    }

    private int numSquares(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
