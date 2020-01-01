import java.util.*;

// 18 https://leetcode.com/problems/4sum/
public class FourSum {
    public static void main(String[] args) {
        FourSum obj = new FourSum();
        int[] arr = {-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;
        List<List<Integer>> res = obj.fourSum(arr, target);
        for (List<Integer> l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
                        results.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                    }

                    if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        int currentRight = right;
                        while (right >= 0 && nums[currentRight] == nums[right]) {
                            right--;
                        }
                    } else {
                        int currentLeft = left;
                        while (left < right && nums[currentLeft] == nums[left]) {
                            left++;
                        }
                    }
                }
                while(j + 1 < nums.length && nums[j + 1] == nums[j]) {
                    ++j;
                }
            }

            while(i + 1 < nums.length && nums[i + 1] == nums[i]) {
                ++i;
            }
        }
        return results;
    }

}
