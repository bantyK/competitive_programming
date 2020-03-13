import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 15 https://leetcode.com/problems/3sum/
public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        int[] nums = {-2, -3, 0, 0, -2};
        for (List<Integer> res : obj.threeSum(nums)) {
            System.out.println(res);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
       
        List<List<Integer>> res = new ArrayList<>();

        int target;
        int left, right, sum, prevLeftVal, prevIVal;
        for (int i = 0; i < nums.length-2; i++) {
            target = -nums[i];
            left = i + 1;
            right = nums.length - 1;
            prevIVal = nums[i];
            while (left < right) {
                sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    prevLeftVal = nums[left];
                    while(left < right && nums[left] == prevLeftVal) {
                        left++;
                    }
                } else if(sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
            while(i < nums.length-1 && prevIVal == nums[i+1]) {
                i++;
            }
        }

        return res;
    }
 	   
}
