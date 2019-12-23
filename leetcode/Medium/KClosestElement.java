import java.util.*;

// 658 https://leetcode.com/problems/find-k-closest-elements/
public class KClosestElement {
    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return result;
        int left = binarySearch(nums, x);
        int right = left + 1;

        while (k > 0) {
            if (right >= n || (left >= 0 && x - nums[left] <= nums[right] - x)) {
                left--;
            } else {
                right++;
            }
            k--;
        }

        for (int i = left + 1; i < right; i++) {
            result.add(nums[i]);
        }
        return result;
    }

    private int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
