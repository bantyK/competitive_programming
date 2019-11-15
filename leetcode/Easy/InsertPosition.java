package solutions.easy;

//35 https://leetcode.com/problems/search-insert-position/
public class InsertPosition {
    public static void main(String[] args) {
        InsertPosition obj = new InsertPosition();

        int[] nums = {1, 3, 4, 6, 7, 9, 10};
        System.out.println(
                obj.searchInsert(nums, 5)
        );
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if(nums[mid] == target) return mid;

            else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return right + 1;
    }
}
