// 724 https://leetcode.com/problems/find-pivot-index/
public class EqualSumPivotIndex {
    public static void main(String[] args) {
        EqualSumPivotIndex obj = new EqualSumPivotIndex();
        System.out.println(obj.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;

        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        leftSum[0] = nums[0];
        rightSum[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i];
            rightSum[n - i - 1] = rightSum[n - i] + nums[n - i - 1];
        }

        for (int i = 0; i < n; i++) {
            if (leftSum[i] == rightSum[i]) {
                return i;
            }
        }

        return -1;
    }
}
