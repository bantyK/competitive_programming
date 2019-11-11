package solutions.easy;

import java.util.*;

// 70. https://leetcode.com/problems/climbing-stairs/
public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs obj = new ClimbStairs();
        System.out.println(
                obj.climbStairs(3)
        );
    }

    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
