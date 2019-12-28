import java.util.*;

public class ContinuousSubarraySum {
    public static void main(String[] args) {
        ContinuousSubarraySum obj = new ContinuousSubarraySum();
        int[] nums = new int[]{23, 2, 6, 4, 7};
        int k = 6;

        System.out.println(obj.checkSubarraySum(nums, k));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        return canPartition(nums, 0, k, 0);
    }

    private boolean canPartition(int[] nums, int sum, int k, int index) {
        if (index > 0 && sum > 0 && sum % k == 0) {
            return true;
        }

        if (index >= nums.length) return false;

        boolean check1 = canPartition(nums, sum + nums[index], k, index + 1);
        if (check1) return true;

        return canPartition(nums, sum, k, index + 1);
    }
}
