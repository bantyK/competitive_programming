import java.util.stream.IntStream;

// 698 https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
public class PartitionKEqualSubSubset {
    public static void main(String[] args) {
        PartitionKEqualSubSubset obj = new PartitionKEqualSubSubset();
        int[] nums = {4, 5, 1};
        int k = 2;

        System.out.println(obj.canPartitionKSubsets(nums, k));
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = IntStream.of(nums).sum();
        if (k == 0 || totalSum % k == 1) {
            return false;
        }

        int targetBucketSum = totalSum / k;
        boolean[] seen = new boolean[nums.length];
        return canPartition(nums, 0, targetBucketSum, seen, k, 0);
    }


    private boolean canPartition(int[] nums, int currentBucketSum, int targetBucketSum, boolean[] seen, int k, int startIndex) {
        //If all buckets are filled correctly. The final bucket will difinetely get filled
        if (k == 1) {
            return true;
        }

        if (currentBucketSum == targetBucketSum) {
            // the current bucket is done. Move to the next one
            return canPartition(nums, 0, targetBucketSum, seen, k - 1, 0);
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (!seen[i] && nums[i] + currentBucketSum <= targetBucketSum) {
                seen[i] = true;
                if (canPartition(nums, currentBucketSum + nums[i], targetBucketSum, seen, k, i + 1)) {
                    return true;
                }
                seen[i] = false;
            }
        }

        return false;
    }

}
