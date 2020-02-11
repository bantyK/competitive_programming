import java.util.*;

// 31 https://leetcode.com/problems/next-permutation/
public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();
        int[] nums = {2, 3, 1, 3, 3};
        obj.nextPermutation(nums);
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int pivotIndex = getIndexOfLastPeak(nums) - 1;
        if (pivotIndex != -1) {
            int index = getNextGreaterElementIndex(nums, nums[pivotIndex]);
            swap(nums, pivotIndex, index);
        }
        reverseArray(nums, pivotIndex + 1);
//        System.out.println(Arrays.toString(nums));
    }

    private int getIndexOfLastPeak(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    private int getNextGreaterElementIndex(int[] nums, int min) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min) {
                return i;
            }
        }
        return -1;
    }

    private void reverseArray(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
