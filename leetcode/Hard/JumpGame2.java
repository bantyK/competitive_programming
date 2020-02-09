import java.util.*;

// 45 https://leetcode.com/problems/jump-game-ii/
public class JumpGame2 {
    public static void main(String[] args) {
        JumpGame2 obj = new JumpGame2();

        int[] nums = {5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6, 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5};
        System.out.println(obj.jumpdp(nums));
    }

    public boolean canJump(int[] nums) {
        Boolean[] dp = new Boolean[nums.length + 1];
        Arrays.fill(dp, null);
        return helper(nums, 0, dp);
    }


    public boolean helper(int[] nums, int currentIndex, Boolean[] dp) {
        if (currentIndex == nums.length - 1) {
            return true;
        }

        if (dp[currentIndex] != null) return dp[currentIndex];

        int farthestJump = nums[currentIndex] + currentIndex;
        farthestJump = Math.min(farthestJump, nums.length - 1);

        for (int nextPosition = currentIndex + 1; nextPosition <= farthestJump; nextPosition++) {
            if (helper(nums, nextPosition, dp)) {
                dp[currentIndex] = true;
                return true;
            }
        }
        dp[currentIndex] = false;
        return false;
    }

    public int jumpDp(int[] nums) {
        Map<String, Integer> dp = new HashMap<>();
        return minJumps(nums, 0, 0, dp);
    }

    private int minJumps(int[] nums, int currentIndex, int jumps, Map<String, Integer> dp) {
        if (currentIndex >= nums.length - 1) {
            return jumps;
        }

        String key = currentIndex + "" + jumps;
        if (dp.containsKey(key)) return dp.get(key);

        currentIndex = Math.min(currentIndex, nums.length - 1);

        int farthestJump = nums[currentIndex] + currentIndex;
        int minJumps = Integer.MAX_VALUE;
        for (int nextPos = currentIndex + 1; nextPos <= farthestJump; nextPos++) {
            int jumpsRequired = minJumps(nums, nextPos, jumps + 1, dp);
            minJumps = Math.min(jumpsRequired, minJumps);
        }

        dp.put(key, minJumps);
        return minJumps;
    }

    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int ladder = nums[0]; // keep track of the largest ladder that we have
        int stairs = nums[0]; // keep track of the number of stairs in the current ladder
        int jumps = 1;

        for (int i = 1; i < nums.length; i++) {
            if (i == nums.length - 1) {
                return jumps;
            }
            if (i + nums[i] > ladder) {
                ladder = i + nums[i];
            }
            stairs--;
            if (stairs == 0) {
                jumps++;
                stairs = ladder - i;
            }
        }

        return jumps;
    }

    /**
     * Accepted solution in leetcode
     * Other solutions are for educational purpose
     * @param nums
     * @return
     */
    public int jumpdp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            int maxReach = Math.min(nums[i] + i, nums.length - 1);
            for (int j = i + 1; j <= maxReach; j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        return dp[nums.length - 1];
    }
}
