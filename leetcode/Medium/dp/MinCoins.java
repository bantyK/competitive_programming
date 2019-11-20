import java.util.*;
//322 : https://leetcode.com/problems/coin-change/
public class MinCoins {
    public static void main(String[] args) {
        int[] coins = new int[]{186,419,83,408};
        int amount = 6249;
        int minCoins = minCoins(coins,amount);
        System.out.println(minCoins);
    }

    public static int minCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int i = 1; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < dp.length; i++) {
            for(int coin : coins) {
                min = dp[i];
                System.out.println(min);
                if(coin <= i) {
                    min = Math.min(min, 1 + dp[i - coin]);
                }
            }
            dp[i] = min;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    private static void print(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int minCoins(int[] coins, int remainder, int[] dp) {
        if(remainder < 0) return -1;
        
        if(remainder == 0) {
            return 0;
        }

        if(dp[remainder] != 0) {
            return dp[remainder];
        }

        int min = Integer.MAX_VALUE;

        for(int coin : coins) {
            int changeResult = minCoins(coins, remainder - coin, dp);

            if(changeResult >= 0 && changeResult < min) {
                min = 1 + changeResult;
            }
        }

        dp[remainder] = (min == Integer.MAX_VALUE) ? -1 : min;

        return dp[remainder];
    }

}