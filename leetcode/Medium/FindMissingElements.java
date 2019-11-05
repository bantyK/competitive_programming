package src;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
public class FindMissingElements {
    public static void main(String[] args) {
        FindMissingElements obj = new FindMissingElements();
        List<Integer> disappearedNumbers = obj.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        System.out.println(disappearedNumbers);
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                res.add(i + 1);
            }
        }

        return res;
    }
}
