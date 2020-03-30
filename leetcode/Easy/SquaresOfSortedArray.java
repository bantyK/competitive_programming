import java.util.*;

//977 https://leetcode.com/problems/squares-of-a-sorted-array/
public class SquaresOfSortedArray {
    public static void main(String[] args) {
        SquaresOfSortedArray obj = new SquaresOfSortedArray();
        System.out.println(Arrays.toString(obj.sortedSquares(new int[]{-7,-3,2,3,11})));
    }

    public int[] sortedSquares(int[] A) {
        int[] squares = new int[A.length];
        int idx = A.length - 1;

        int left = 0, right = A.length - 1;

        while (left <= right) {
            if (Math.abs(A[left]) > Math.abs(A[right])) {
                squares[idx] = A[left] * A[left];
                left++;
            } else {
                squares[idx] = A[right] * A[right];
                right--;
            }

            idx--;
        }

        return squares;
    }
}
