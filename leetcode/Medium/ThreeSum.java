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
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] + nums[i] == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    }
                    if (nums[left] + nums[right] + nums[i] < 0) {
                        int currentLeft = left;
                        while (nums[left] == nums[currentLeft] && left < right) {
                            left++;
                        }
                    } else {
                        int currentRight = right;
                        while (nums[currentRight] == nums[right] && right >= left) {
                            right--;
                        }
                    }
                }
            }
        }
        return res;
    }
}
