//1664 https://leetcode.com/problems/ways-to-make-a-fair-array/
class WayToMakeFairArray {
	public int waysToMakeFair(int[] nums) {
        int len = nums.length;
        if (len == 1) return 1;

        int count = 0;

        // {even odd}
        int[][] leftRight = new int[len][2];
        int[][] rightLeft = new int[len][2];

        leftRight[0][0] = nums[0];
        leftRight[0][1] = 0;
        for (int i = 1; i < len; i++) {
            if (i % 2 == 0) {
                leftRight[i][0] += nums[i] + leftRight[i - 1][0];
                leftRight[i][1] = leftRight[i - 1][1];
            } else {
                leftRight[i][0] += leftRight[i - 1][0];
                leftRight[i][1] = nums[i] + leftRight[i - 1][1];
            }
        }

        if (len % 2 == 1) {
            rightLeft[len - 1][0] = nums[len - 1];
            rightLeft[len - 1][1] = 0;
        } else {
            rightLeft[len - 1][1] = nums[len - 1];
            rightLeft[len - 1][0] = 0;
        }

        for (int i = len - 2; i >= 0; i--) {
            if (i % 2 == 0) {
                rightLeft[i][0] = nums[i] + rightLeft[i + 1][0];
                rightLeft[i][1] = rightLeft[i + 1][1];
            } else {
                rightLeft[i][1] = nums[i] + rightLeft[i + 1][1];
                rightLeft[i][0] = rightLeft[i + 1][0];
            }
        }

        for (int i = 0; i < len; i++) {
            int oddSum = 0;
            int evenSum = 0;

            if (i == 0) {
                evenSum = rightLeft[i + 1][1];
                oddSum = rightLeft[i + 1][0];
            } else if (i == len - 1) {
                evenSum = leftRight[i - 1][0];
                oddSum = leftRight[i - 1][1];
            } else {
                evenSum = leftRight[i - 1][0];
                oddSum = leftRight[i - 1][1];

                evenSum += rightLeft[i + 1][1];
                oddSum += rightLeft[i + 1][0];
            }

            if (oddSum == evenSum) {
                count++;
            }
        }

        return count;
    }
}