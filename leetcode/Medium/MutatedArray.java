import java.util.*;

// 1300 https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/
public class MutatedArray {
    public static void main(String[] args) {
        MutatedArray obj = new MutatedArray();
        System.out.println(obj.findBestValue(new int[]{60864, 25176, 27249, 21296, 20204}, 56803));
        System.out.println(obj.findBestValue(new int[]{4, 9, 3, 7, 6, 3, 2, 7, 8}, 30));
        System.out.println(obj.findBestValue(new int[]{4, 9, 3}, 10));
        System.out.println(obj.findBestValue(new int[]{2, 3, 5}, 10));
    }

    public int findBestValue(int[] arr, int target) {
        int low = 0;
        int high = Arrays.stream(arr).max().getAsInt();
        int mutatedSum = 0, minSoFar = Integer.MAX_VALUE;
        int res = high;
        int diff = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            mutatedSum = calculateNewSum(arr, mid);

            diff = Math.abs(mutatedSum - target);
            if (diff < minSoFar || (diff == minSoFar && mid < res)) {
                res = mid;
                minSoFar = diff;
            }

            if (mutatedSum > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    private int calculateNewSum(int[] arr, int val) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, val);
        }
        return sum;
    }
}
