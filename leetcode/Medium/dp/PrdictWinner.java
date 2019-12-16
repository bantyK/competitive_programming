import java.util.*;

// 468 https://leetcode.com/problems/predict-the-winner/
public class PrdictWinner {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;

        if (n == 1 || n % 2 == 0) return true;

        // the table to store the intermediate results.
        int[][] dp = new int[n][n];

        // initialise the dp table with -1
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // total score that is possible. The winner has to get more than half of this value to win.
        int totalScore = 0;

        for (int num : nums) totalScore += num;

        // The final score that player1 can score after considering all possible scenarios.
        int player1Score = score(nums, 0, n - 1, dp);

        return player1Score > totalScore / 2;
    }

    private int score(int[] nums, int i, int j, int[][] dp) {
        if (i > j) return 0;
        if (i == j) return nums[i];

        if (dp[i][j] != -1) return dp[i][j];

        // Player 1 has choosen nums[i].
        int scoreFromChoice1 = nums[i] + Math.min(score(nums, i + 2, j, dp), score(nums, i + 1, j - 1, dp));

        // Player 1 has choosen nums[j].
        int scoreFromChoice2 = nums[j] + Math.min(score(nums, i + 1, j - 1, dp), score(nums, i, j - 2, dp));

        // The maximum of the two values will be the optimal solution
        dp[i][j] = Math.max(scoreFromChoice1, scoreFromChoice2);
        return dp[i][j];
    }
}
