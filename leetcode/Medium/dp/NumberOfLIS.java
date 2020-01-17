import java.util.*;
public class NumberOfLIS {
    public static void main(String[] args) {
        int x = findNumberOfLIS(new int[]{1,3,5,4,7});
        System.out.println(x);
    }

    public static int findNumberOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return 1;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = 1;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(1, 1);

        for(int j = 1; j < dp.length; j++) {
            for(int i = 0; i < j; i++) {
                if(nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[j], 1+dp[i]);
                    if(dp[j] > max) {
                        max = dp[j];
                    }    
                }
            }
            countMap.put(dp[j], countMap.getOrDefault(dp[j], 0) + 1);
        }

        return countMap.get(max);
    }
}