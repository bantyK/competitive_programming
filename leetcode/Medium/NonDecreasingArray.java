// 665 https://leetcode.com/problems/non-decreasing-array/
public class NonDecreasingArray {

    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        if (n <= 2) return true;

        int count = 0;
        int idx = -1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                if (count == 1) return false;
                count++;
                idx = i;
            }
        }

        if (idx <= 1 || idx == n - 1 || n == 3) return true;

        if (nums[idx - 1] <= nums[idx + 1]) {
            return true;
        } else {
            if (nums[idx - 2] <= nums[idx]) {
                return true;
            }
        }

        return false;
    }

}