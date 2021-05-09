// 1855 https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/
public class MaximumDistancePairs {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int maxLen = 0;
        while (i < nums1.length) {
            while (i < nums1.length && i < j && nums1[i] > nums2[j]) {
                i++;
            }

            while (i < nums1.length && j < nums2.length && nums1[i] > nums2[j]) {
                i++;
                j++;
            }

            while (i < nums1.length && j < nums2.length && nums1[i] <= nums2[j]) {
                maxLen = Math.max(maxLen, j - i);
                j++;
            }

            if (j == nums2.length) {
                break;
            }
        }
        return maxLen;
    }
}
