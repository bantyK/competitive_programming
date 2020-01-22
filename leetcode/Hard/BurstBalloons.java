// 312 https://leetcode.com/problems/burst-balloons/
public class BurstBalloons {
    public static void main(String[] args) {
        BurstBalloons obj = new BurstBalloons();
        final int res = obj.maxCoins(new int[]{3,1,5,8});
        System.out.println(res);
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;

        int paddedLen = n + 2;
        int[] paddedArr = new int[paddedLen];
        paddedArr[0] = 1;
        paddedArr[paddedLen - 1] = 1;

        for(int i = 0; i < n; i++) {
            paddedArr[i + 1] = nums[i];
        }

        int[][] dp = new int[paddedLen][paddedLen];

        for(int windowLen = 1; windowLen <= n; windowLen++) {
            for(int start = 1; start <= n - windowLen + 1; start++) {
                int end = start + windowLen - 1;
                int maxVal = -1;
                for(int i = start; i <= end; i++) {
                    maxVal = Math.max(maxVal,(paddedArr[start - 1] * paddedArr[i] * paddedArr[end + 1]) + dp[start][i - 1] + dp[i + 1][end]);
                }
                dp[start][end] = maxVal;
            }
        }
        return dp[1][n];
    }
}