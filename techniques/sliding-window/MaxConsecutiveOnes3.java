// #1004 https://leetcode.com/problems/max-consecutive-ones-iii/submissions/
public class MaxConsecutiveOnes3 {
    public static void main(String[] args) {
        MaxConsecutiveOnes3 obj = new MaxConsecutiveOnes3();
        System.out.println(obj.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        System.out.println(obj.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
        System.out.println(obj.longestOnes(new int[]{0,0,0,1}, 4));
    }

    /**
     * Move the right pointer. If a zero is found, decrement the value of K.
     * If K becomes less than 0, then start incrementing the left pointer.
     *
     * Increment K if left pointer points to a zero.
     *
     * The segment between left and right indicates the current window that we are looking at.
     * @param A
     * @param k
     * @return
     */
    public int longestOnes(int[] A, int k) {
        int left = 0;
        int maxLen = Integer.MIN_VALUE;
        int right;
        for (right = 0; right < A.length; right++) {
            if (A[right] == 0) {
                k--;
            }

            // Every time the right moves, we need to increase the length
            maxLen = Math.max(maxLen, (right - left));

            while (k < 0) {
                if (A[left] == 0) {
                    k++;
                }
                left++;
            }
        }
        // this handles the case if the left pointer does not move at all. It will be 0 so right - left will return a height which is one less than actual height.
        // When this line is executed, right pointer will move one towards the right, hence (right - left) will return the correct height.
        maxLen = Math.max(maxLen, (right - left));
        return maxLen;
    }
}
