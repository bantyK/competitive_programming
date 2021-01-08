// 1712 https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/
public class WaysToSplitArray {

    public int waysToSplit(int[] nums) {
        /*
            LeftSum + MidSum + RightSum = TotalSum
            This means, once the leftSum is determined, the range of MidSum is also determined.
            LeftSum ≤ MidSum ≤ RightSum = TotalSum−(LeftSum + MidSum)
            We can simplify the right part:
            MidSum ≤ (TotalSum − LeftSum) / 2
            so the final equation becomes leftSum <= midSum <=(TotalSum − LeftSum) / 2
            if we fix leftSum, we will be able to find the range of mid sum, and for all those ranges, we will have valid solution.
         */
        int MOD = 1000000007;
        long result = 0;
        int n = nums.length;

        // this will help us find the range sum in constant time
        int[] presum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }


        for (int i = 0; i < n - 2; i++) {
            int leftSum = presum[i + 1];
            int remaining = presum[n] - leftSum;

            if(leftSum * 2 > remaining) {
                // from the equation in the comments, leftSum <= remaining / 2;
                // if leftSum * 2 > remaining then the first condition itself is not satisfied, no need for further calculationn
                break;
            }

            int first = binarySearchFindFirst(i, n, leftSum, presum);
            int last = binarySearchFindLast(i, n, remaining / 2, presum);

            // the mid values from first to last are all valid inndices and form valid subarray
            // so we will count all of them
            result += Math.max(last - first + 1, 0);
        }
        return (int) (result % MOD);
    }

    /**
     * Find the first index in presum such that presum[i] >= leftSum (target)
     */
    private int binarySearchFindFirst(int index, int n, int target, int[] presum) {
        int left = index + 1;
        int right = n - 2; //  n - 2 because right should have atleast one element
        while(left < right) {
            int mid = left + (right - left) / 2;
            //sum of all element from the end of left sum boundary to the mid element
            int current = presum[mid + 1] - presum[index + 1];
            if(current >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
    /**
     * Find the last index in presum such that presum[i] <= target
     */

    private int binarySearchFindLast(int index, int n, int target, int[] presum) {
        int left = index + 1;
        int right = n - 2;
        while(left < right) {
            int mid = (left + right) / 2 + 1;
            //sum of all element from the end of left sum boundary to the mid element
            int current = presum[mid + 1] - presum[index + 1];
            if(current > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
