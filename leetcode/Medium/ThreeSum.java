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
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n - 2; ) {
            int firstNum = nums[i];

            int left = i + 1;
            int right = n - 1;
            int requiredSum = -firstNum;

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == requiredSum) {
                    res.add(Arrays.asList(firstNum, nums[left], nums[right]));
                    int rightNum = nums[right];
                    // avoid infinite loops if a combination is already found
                    while (right >= 0 && nums[right] == rightNum) {
                        --right;
                    }
                    int leftNum = nums[left];
                    while (left < n && nums[left] == leftNum) {
                        ++left;
                    }

                } else if (sum > requiredSum) {
                    right--;
                } else {
                    left++;
                }
            }
            // Avoid dups
            while(i < n && nums[i] == firstNum) {
                i++;
            }
        }

        return res;
    }

}
