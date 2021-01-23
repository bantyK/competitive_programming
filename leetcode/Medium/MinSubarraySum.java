import java.util.*;

// 907 https://leetcode.com/problems/sum-of-subarray-minimums/
public class MinSubarraySum {

    public static void main(String[] args) {
        MinSubarraySum obj = new MinSubarraySum();
//        int sum = obj.sumSubarrayMins(new int[]{2, 9, 7, 8, 3, 4, 6});
//        System.out.println(sum);
        int sum = obj.sumSubarrayMins(new int[]{3, 1, 2, 4});
        System.out.println(sum);
    }

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;

        System.out.println("Arr     : " + Arrays.toString(arr));

        // These 2 arrays contains the distance between the first smaller element to left of a number
        // and to right of a number.

        int[] smallerElementIndexToLeft = smallerElementIndexToLeft(arr);
        System.out.println("Previous: " + Arrays.toString(smallerElementIndexToLeft));

        int[] smallerElementIndexToRight = smallerThanSelfToRight(arr);
        System.out.println("Next    : " + Arrays.toString(smallerElementIndexToRight));

        long sum = 0;

        for (int i = 0; i < n; i++) {
            int left = smallerElementIndexToLeft[i];
            int right = smallerElementIndexToRight[i];

            // Logic for multiplication:
            // https://lh3.googleusercontent.com/-GyygvrTJ3GY/XRlvmDTxEHI/AAAAAAAAO4E/yDc-Xvo3isgM8QFOSiVp6yUK_j9E8cwGACK8BGAs/s0/2019-06-30.jpg
            sum += (long) arr[i] * left * right;
        }
        return (int) (sum % 1000000007);
    }
    private int[] smallerElementIndexToLeft(int[] arr) {
        int n = arr.length;
        int[] smallerElementIndexToLeft = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                smallerElementIndexToLeft[i] = i - stack.peek();
            } else {
                // This is the smallest element from left, so the distance is from -1th element (left of first)
                // to this element. Which is basically i + 1.
                smallerElementIndexToLeft[i] = i + 1;
            }
            stack.push(i);
        }
        return smallerElementIndexToLeft;
    }

    private int[] smallerThanSelfToRight(int[] arr) {
        int n = arr.length;
        int[] smallerElementIndexToRight = new int[n];
        Arrays.fill(smallerElementIndexToRight, n);

        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                smallerElementIndexToRight[i] = stack.peek() - i;
            } else {
                // This is the smallest number from right.
                // So the distance is size of array to this index.
                smallerElementIndexToRight[i] = n - i;
            }
            stack.push(i);
        }

        return smallerElementIndexToRight;
    }
}
