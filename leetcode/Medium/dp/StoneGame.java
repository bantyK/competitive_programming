import java.util.*;

// 877 https://leetcode.com/problems/stone-game/

// This problem can be solved by simply returning true, but this is the detailed solution which I understood
public class StoneGame {

    public boolean stoneGame(int[] piles) {
        int n = piles.length;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int totalScore = 0;
        for (int pile : piles) {
            totalScore += pile;
        }

        int firstPlayerScore = helper(piles, 0, piles.length - 1, dp);

        if (firstPlayerScore > totalScore / 2) return true;
        return false;

    }

    private int helper(int[] nums, int i, int j, int[][] dp) {
        if (i >= nums.length || j < 0 || i > j) return 0;
        if (i == j) return nums[i];

        if (dp[i][j] != -1) return dp[i][j];

        int score1 = nums[i] + Math.min(helper(nums, i + 2, j, dp), helper(nums, i + 1, j - 1, dp));
        int score2 = nums[j] + Math.min(helper(nums, i + 1, j - 1, dp), helper(nums, i, j - 2, dp));

        dp[i][j] = Math.max(score1, score2);
        return dp[i][j];
    }

}
