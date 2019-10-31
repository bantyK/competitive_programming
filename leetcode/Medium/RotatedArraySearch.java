package solutions.medium;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
public class RotatedArraySearch {
    public static void main(String[] args) {
        RotatedArraySearch obj = new RotatedArraySearch();

        int[] nums = new int[]{2, 3, 4, 5, 6, 7, 0, 1}/* {5, 1, 3}*/;
        System.out.println(
                obj.search(nums, 1)
        );
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[start] <= nums[mid]) {
                    // left part is sorted
                    if (target >= nums[start] && target < nums[mid]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (nums[end] > nums[mid]) {
                    // right part is sorted
                    if (target > nums[mid] && target <= nums[end]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            }
        }
        return -1;
    }

}
