import java.util.*;
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {2, 5, 3, 6};
        int amount = 10;

        System.out.println(coinChange(coins, amount));
    }

    private static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        Integer[][] dp = new Integer[n][amount + 1];

        return helper(coins, amount, 0, dp);
    }

    private static int helper(int[] coins, int amount, int index, Integer[][] dp) {
        if(index >= coins.length) {
            return 0;
        }
        
        if(amount == 0) {
            return 1;
        }

        if(dp[index][amount] != null) {
            return dp[index][amount];
        }

        int coins1 = 0;
        if(amount >= coins[index]) {
            coins1 = helper(coins, amount - coins[index], index, dp);
        }

        int coins2 = helper(coins, amount, index + 1, dp);
        dp[index][amount] = coins1 + coins2;
        return dp[index][amount];
    }
}