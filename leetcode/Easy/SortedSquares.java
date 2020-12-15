import java.util.Arrays;

//977 https://leetcode.com/problems/squares-of-a-sorted-array/
public class SortedSquares {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SortedSquares().sortedSquares(new int[]{-3, -2, -1, 1, 2, 3})));
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] squares = new int[n];

        int left = 0;
        int right = n - 1;
        int resIdx = n - 1;

        while (left <= right) {
            int squareLeft = nums[left] * nums[left];
            int squareRight = nums[right] * nums[right];

            if (squareLeft > squareRight) {
                squares[resIdx--] = squareLeft;
                left++;
            } else {
                squares[resIdx--] = squareRight;
                right--;
            }
        }

        return squares;
    }
}