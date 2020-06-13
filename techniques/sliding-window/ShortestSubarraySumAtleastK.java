package sw;

//209 https://leetcode.com/problems/minimum-size-subarray-sum/
/*
* This method will only work if the elements in the array are all positive.
* For negative values the problem is https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
* */
public class ShortestSubarraySumAtleastK {
    public static void main(String[] args) {
        ShortestSubarraySumAtleastK obj = new ShortestSubarraySumAtleastK();
        System.out.println(obj.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));

    }

    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            currentSum += num;

            while (currentSum >= s) {
                minLen = Math.min(minLen, right - left + 1);

                int leftNum = nums[left++];
                currentSum -= leftNum;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
