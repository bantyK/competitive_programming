import java.util.*;

// 75 https://leetcode.com/problems/sort-colors/
// The national dutch flag problem
public class SortColors {
    public static void main(String[] args) {
        SortColors obj = new SortColors();
        int[] colors = {2, 1, 2, 0, 1, 0};
        colors = new int[]{2, 1, 1, 0, 2, 1, 0};
        obj.sortColors1Pass(colors);
        System.out.println(Arrays.toString(colors));
    }

    // 1 pass solution
    public void sortColors1Pass(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int index = 0;

        while (index <= right) {
            switch (nums[index]) {
                case 0:
                    swap(nums, left, index);
                    left++;
                    index++;
                    break;
                case 1:
                    index++;
                    break;
                case 2:
                    swap(nums, right, index);
                    right--;
                    break;
            }
        }
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // 2 pass solution (constant space)
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        int index = 0;
        for (; index < count0; index++) {
            nums[index] = 0;
        }

        for (; index < count0 + count1; index++) {
            nums[index] = 1;
        }

        for (; index < count0 + count1 + count2; index++) {
            nums[index] = 2;
        }
    }
}
