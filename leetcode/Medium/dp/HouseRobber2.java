import java.util.Arrays;
import java.util.Random;

//213 https://leetcode.com/problems/house-robber-ii/
public class HouseRobber2 {
    public static void main(String[] args) {
        HouseRobber2 obj = new HouseRobber2();
        System.out.println(obj.rob(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        int[] nums = new int[100];
        for(int i = 0; i < 100; i++)
            nums[i] = new Random().nextInt(1000);

        System.out.println(Arrays.toString(nums));
        System.out.println(obj.rob(nums));
    }

    public int rob(int[] nums) {
        // maximum amount if we rob the first house
        int takeFirstHouse = rob(nums, 0, nums.length - 2);

        // maximum amount if we rob the second house
        int takeLastHouse = rob(nums, 1, nums.length - 1);

        return Math.max(takeFirstHouse, takeLastHouse);
    }

    private int rob(int[] nums, int start, int end) {
        int len = end - start + 1;
        int[] dp = new int[nums.length];
        if (start >= 0) {
            dp[start] = nums[start];
        }
        if (len >= 2) {
            dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        }

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[start + len - 1];
    }

}
