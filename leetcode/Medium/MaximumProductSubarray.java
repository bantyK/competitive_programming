// 152 https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray {
    public static void main(String[] args) {
        MaximumProductSubarray obj = new MaximumProductSubarray();
        int[] a = new int[]{-117, 141, 196, -61, 57, 111, -9, -31, 32, 53};//, 80, -49, -153, -147, 52, -66, 73, 183, 177, -44, 74, -40, -4, -144, 132, -164, 139, 5, -57, -142, -81, 24, 182, -178, -172, 51, -159, 112, -24, 163, 120, 124, -130, -111, 90, 29, 3, 4, -106, 173, 4, -160, 35, -171, 130, -56, -157, -46, -149, 13, 62, -13, 168, 79, -34, -164, -72, 36, 117, 77, -19, -72, -176, 107, 62, 157, 56, -122, 18, -51, 198, 23, -62, 105, -127, -150, -32, -162, 88, -92, 189, -193, 172, 22, -187, -142, 118, -117, 100};
        int[] nums = new int[]{1, -2, -4, 5, -10, 8, 7, 10, 3, 8, 9};
//        nums = new int[]{2, 3, -2, 4, -5};
        nums = new int[]{9, 1, -7, -3, 9, 2, -10, 500};
        System.out.println(obj.maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int minProductSoFar = nums[0];
        int maxProductSoFar = nums[0];
        int globalMax = nums[0];

        for(int i = 1; i < n; i++) {
            int temp = maxProductSoFar;

            maxProductSoFar = Math.max(nums[i], Math.max(maxProductSoFar * nums[i], minProductSoFar * nums[i]));
            minProductSoFar = Math.min(nums[i], Math.min(nums[i] * temp, minProductSoFar * nums[i]));

            globalMax = Math.max(globalMax, maxProductSoFar);
        }

        return globalMax;
    }


}
