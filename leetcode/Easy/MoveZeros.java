import java.util.*;
//283 https://leetcode.com/problems/move-zeroes/
public class MoveZeros {
    public static void main(String[] args) {
        MoveZeros obj = new MoveZeros();
         int[] nums = {0, 1, 0, 2, 0};
        obj.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 1, 0, 3, 12};
        obj.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1,2,3,0,-15};
        obj.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0,0,0,0,-15};
        obj.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int index = 0;
        int i;
        for (i = 0; i < n; i++) {
            if (nums[i] == 0) {
                index = i;
                break;
            }
        }

        if(i == n) return;

        for (i = index + 1; i < n; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                nums[i] = 0;
                index++;
            }

        }
    }
}
