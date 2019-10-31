package solutions.medium;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinRotatedArray {
    public static void main(String[] args) {
        FindMinRotatedArray obj = new FindMinRotatedArray();
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, -1}/*{2, 3, 4, 5, 6, 7, 0, 1}*/;
        System.out.println(
                obj.findMin(nums)
        );
    }

    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end) / 2;
        int min = nums[mid];
        while (start <= end) {
            mid = (start + end) / 2;

            if (nums[start] <= nums[mid]) {
                // left part is sorted
                if (min > nums[start])
                    min = nums[start];
                start = mid + 1;
            } else if (nums[end] > nums[mid]) {
                // right part is sorted
                if (min > nums[mid])
                    min = nums[mid];
                end = mid - 1;
            }
        }

        return min;
    }
}
