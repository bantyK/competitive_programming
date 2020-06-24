//1060 https://leetcode.com/problems/missing-element-in-sorted-array/
public class MissingElementInSortedArray {
    public static void main(String[] args) {
        MissingElementInSortedArray obj = new MissingElementInSortedArray();

        System.out.println(obj.missingElement(new int[]{1,2,3,5}, 5));
        System.out.println(obj.missingElement(new int[]{2,3,4,5}, 5));
        System.out.println(obj.missingElement(new int[]{1,3,4,5}, 5));
        System.out.println(obj.missingElement(new int[]{1,2,3,4}, 5));
        System.out.println(obj.missingElement(new int[]{2}, 2));
        System.out.println(obj.missingElement(new int[]{}, 1));
    }

    public int missingElement(int[] nums, int size) {
        int low = 0;
        int high = size - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if(nums[mid] > mid + 1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low + 1;

    }
}
