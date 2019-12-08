//1283 https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/
public class SmallestDivisor {
    public static void main(String[] args) {
        SmallestDivisor obj = new SmallestDivisor();
        int[] nums = new int[]{19};
        int threshold = 5;
        System.out.println(obj.smallestDivisor(nums, threshold));
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = max(nums);

        while (low < high) {
            int mid = (low + high) / 2;
            int i = 0, result = 0;

            while (i < nums.length) {
                result += divide(nums[i++], mid);
            }

            if (result > threshold) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int max(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums)
            max = Math.max(max, num);

        return max;
    }

    private int divide(int dividend, int divisor) {
        return (int) Math.ceil((float) dividend / divisor);
    }

}
