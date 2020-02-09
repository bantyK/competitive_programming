// 1343 https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
public class SubarrayWithAverage {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int res = 0;
        if (arr == null || arr.length == 0) return 0;

        int left = 0;
        int right = 0;

        int windowSum = 0;
        while (right < k) {
            windowSum += arr[right];
            right++;
        }
        right--;
        while (right < arr.length - 1) {
            if (windowSum / k >= threshold) {
                res += 1;
            }
            windowSum -= arr[left];
            left++;
            right++;
            windowSum += arr[right];
        }
        if(windowSum / k >= threshold) {
            res += 1;
        }
        return res;
    }
}
