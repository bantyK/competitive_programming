package src;


// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
public class RotatedSearchArray {
    public static void main(String[] args) {
        RotatedSearchArray obj = new RotatedSearchArray();
        System.out.println(
                obj.search(new int[]{1,3,1,1,1}, 3)
        );
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return true;
            } else {
                if( (nums[left] == nums[mid]) && (nums[right] == nums[mid]) )
                {
                    ++left;
                    --right;
                } else if (nums[left] <= nums[mid]) {
                    // left part is sorted
                    if (target >= nums[left] && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else if (nums[right] >= nums[mid]) {
                    // right part is sorted
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return false;
    }
}

