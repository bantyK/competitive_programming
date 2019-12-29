// https://practice.geeksforgeeks.org/problems/rod-cutting/0
import java.util.*;
public class RodCutting {
    public static void main(String[] args) {
        RodCutting obj = new RodCutting();
        int n = 8;
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(obj.solveRodCutting(n, prices));
    }

    private int solveRodCutting(int n, int[] prices) {
        int[] lengths = new int[prices.length];
        Integer[][] dp = new Integer[n][n + 1];
        for(int i = 1; i <= prices.length; i++) {
            lengths[i-1] = i;
        }
        return helper(n, lengths, prices, 0, dp);
    }

    private int helper(int remainingLength, int[] lengths, int[] prices, int index, Integer[][] dp) {
        if(index >= prices.length) {
            return 0;
        }
        if(dp[index][remainingLength] != null) {
            return dp[index][remainingLength];
        }
        int profit1 =  0;
        if(lengths[index] <= remainingLength) {
            profit1 = prices[index] + helper(remainingLength - lengths[index], lengths, prices, index, dp);
        }

        int profit2 = helper(remainingLength, lengths, prices, index + 1, dp);

        dp[index][remainingLength] = Math.max(profit1, profit2);
        return dp[index][remainingLength];
    }
}