import java.util.Arrays;

// 945 https://leetcode.com/problems/minimum-increment-to-make-array-unique/
public class MinIncrementToMakeArrayUnique {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 2, 1, 7};
        MinIncrementToMakeArrayUnique obj = new MinIncrementToMakeArrayUnique();
        System.out.println(obj.minIncrementForUnique(nums));

        int[] nums1 = {1, 2, 2};
        System.out.println(obj.minIncrementForUnique(nums1));
    }

    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int minSteps = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] >= nums[i]) {
                minSteps += (nums[i - 1] + 1 - nums[i]);
                nums[i] = nums[i - 1] + 1;
            }
        }

        return minSteps;
    }
}