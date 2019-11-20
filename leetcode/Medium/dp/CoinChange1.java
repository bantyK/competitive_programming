package solutions.medium;

import java.util.*;

public class CoinChange1 {
    public static void main(String[] args) {
        CoinChange1 obj = new CoinChange1();
        int i = minCoins(new int[]{186,419,83,408}, 6249);
        System.out.println(i);
    }

    public static int minCoins(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if(coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
