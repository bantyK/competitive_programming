package solutions.medium;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class RangeInAscendingArray {
    public static void main(String[] args) {
        RangeInAscendingArray obj = new RangeInAscendingArray();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 8};
        int[] ints = obj.searchRange(nums, 8);
        System.out.println("First occurrence : " + ints[0]);
        System.out.println("Last occurrence : " + ints[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = findFirstOccurrence(nums, target, 0, nums.length - 1);
        res[1] = findLastOccurrence(nums, target, 0, nums.length - 1);
        return res;

    }

    private int findLastOccurrence(int[] nums, int target, int start, int end) {
        int lastIndex = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] == target) {
                lastIndex = mid;
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return lastIndex;
    }

    private int findFirstOccurrence(int[] nums, int target, int start, int end) {
        int firstIndex = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (nums[mid] == target) {
                firstIndex = mid;
                end = mid - 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return firstIndex;

    }
}
