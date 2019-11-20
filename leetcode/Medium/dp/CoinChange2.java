import java.util.*;
// 518 https://leetcode.com/problems/coin-change-2/
public class CoinChange2 {
    public static void main(String[] args) {
        int change = change(5, new int[]{1, 2, 5});
        System.out.println(change);
    }
    
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        
        // there is only one way to make the make 0 amount by picking any denomination of coins. This is our base case.
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i < dp.length; i++) {
            int coin = coins[i - 1];
            for(int j = 1; j < dp[i].length; j++) {
                if(j < coin) {
                    // if the amount is greater then the coin, just choose the scenario where you cannot consider that coin
                    dp[i][j] = dp[i-1][j];
                } else {
                    // else, consider both the cases, where you can and cannot consider the coin and add the result.
                    dp[i][j] = dp[i-1][j] + dp[i][j-coin];
                }
            }
        }
        print(dp);
        return dp[coins.length][amount];
    }

    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}