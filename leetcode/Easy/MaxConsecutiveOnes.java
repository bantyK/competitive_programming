// 485 https://leetcode.com/problems/max-consecutive-ones/
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        MaxConsecutiveOnes obj = new MaxConsecutiveOnes();

        System.out.println(obj.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0}));
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{0, 0, 0, 1, 1, 1, 1, 0}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                int len = right - left;
                max = Math.max(max, len);
                left = right + 1;
            }
            right++;
        }
        max = Math.max(right - left, max);
        return max;
    }
}

/*
    1,1,0,1,1,1,0,1,1,1,1,1,1,0


 */