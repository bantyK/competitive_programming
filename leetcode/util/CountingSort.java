import java.util.*;
public class CountingSort {
    public static void main(String[] args) {
        int[] nums = {3,2,6,5,8,1,4,9,2};
        countingSort(nums, 9);
    }

    public static void countingSort(int[] nums, int max) {
        int n = nums.length;
        int[] countArr = new int[max + 1];
        Arrays.fill(countArr, 0);

        // populate the count arr
        for(int num : nums) {
            countArr[num] += 1;
        }
        System.out.println(Arrays.toString(countArr));
        // calculate the cumulative sum
        for(int i = 1;i <= max; i++) {
            countArr[i] += countArr[i-1];
        }
        System.out.println(Arrays.toString(countArr));

        int[] sorted = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            int elem = nums[i];
            int indexToBePlaced = countArr[elem]--;
            sorted[indexToBePlaced - 1] = elem;
        }

        System.out.println(Arrays.toString(sorted));

    }
}