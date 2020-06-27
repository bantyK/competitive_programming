public class LongestSubarrayOf1 {
    public static int longestSubarray(int[] nums) {
        int left = 0;
        int num0 = 0;
        int maxLen = Integer.MIN_VALUE;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                num0++;
            }

            while (num0 > 1) {
                int leftNum = nums[left++];
                if (leftNum == 0) {
                    num0--;
                }
            }

            maxLen = Math.max(maxLen, (right - left + 1));
        }

        return maxLen - 1;
    }
}
