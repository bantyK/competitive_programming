import java.util.*;
//16 https://leetcode.com/problems/3sum-closest/
public class ThreeSumClosest {
    public static void main(String[] args) {
        ThreeSumClosest obj = new ThreeSumClosest();

//        System.out.println(obj.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(obj.threeSumClosest(new int[]{1, 1, 1, 0}, -100));
//        System.out.println(obj.threeSumClosest(new int[]{0, 5, 4, 2, 1, -1, 1}, 0));
//        System.out.println(obj.threeSumClosest(new int[]{5,5,5,5,6,7}, 20));
//        System.out.println(obj.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
//        System.out.println(obj.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int res = 0;
        int left, right, sum, diff, minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            int num = nums[i];
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                sum = num + nums[left] + nums[right];
                diff = Math.abs(sum - target);
                if (diff < minDiff) {
                    res = sum;
                    minDiff = diff;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    //Brute force
    public int threeSumClosest2(int[] nums, int target) {
        int minDiff = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int diff = Math.abs(sum - target);
                    if (diff < minDiff) {
                        minDiff = diff;
                        res = sum;
                    }
                }
            }
        }
        return res;
    }
}

