package stonegame;

import java.util.*;

// 1510 https://leetcode.com/problems/stone-game-iv/
public class StoneGame4 {
    public static void main(String[] args) {
        StoneGame4 obj = new StoneGame4();
        System.out.println(obj.winnerSquareGame(5));
    }

    public boolean winnerSquareGame(int n) {
        /**
         * The values at dp table indicates that the player who plays the game with dp[i] piles remaining will
         * lose or win
         *
         * dp[i] = true means the player who take the ith pile will always win
         * dp[i] = false means the player who take the ith pile will always lose
         *
         * For Alice to win, she will pick the stone such that Bob falls under one of the false values of dp
         *
         */
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;
        dp[1] = true;
        dp[2] = false;
        dp[3] = true;

        List<Integer> squares = new ArrayList<>();
        squares.add(1);
        for (int i = 4; i <= n; i++) {
            int sq = (int) Math.sqrt(i);
            if ((sq * sq) == i) {
                squares.add(sq * sq);
                dp[i] = true;
                continue;
            }

            for (Integer square : squares) {
                if (!dp[i - square]) {
                    dp[i] = true;
                    break;
                }
            }

        }

        System.out.println(squares);
        return dp[n];
    }
}
