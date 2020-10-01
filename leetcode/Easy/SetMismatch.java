import java.util.*;
//645 https://leetcode.com/problems/set-mismatch/
public class SetMismatch {
    public static void main(String[] args) {
        SetMismatch obj = new SetMismatch();
        System.out.println(Arrays.toString(obj.findErrorNums2(new int[]{1,2,3,2})));
    }

    // optimised solution
    // dont use extra space
    public int[] findErrorNums2(int[] nums) {
        int[] res = new int[2];

        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] < 0) {
                res[0] = Math.abs(nums[i]);
            } else {
                nums[index] *= -1;
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                res[1] = i + 1;
                break;
            }
        }

        return res;
    }

    // Uses extra space
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                res[0] = num;
            } else {
                set.add(num);
            }
        }
        for(int i = 1; i <= nums.length; i++) {
            if(!set.contains(i)) {
                res[1] = i;
                break;
            }
        }

        return res;
    }
}
