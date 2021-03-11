// 493 https://leetcode.com/problems/reverse-pairs/
public class ReversePairs {

    public static void main(String[] args) {
        ReversePairs obj = new ReversePairs();
        int[] nums = new int[]{2, 4, 3, 5, 1};
        System.out.println(obj.reversePairs(nums));
        System.out.println(obj.reversePairs(new int[]{1, 3, 2, 3, 1}));
    }


    int count;

    public int reversePairs(int[] nums) {
        count = 0;
        merge(nums, 0, nums.length - 1);
        return count;
    }

    public int[] merge(int[] nums, int start, int end) {
        if (start == end) {
            return new int[]{nums[start]};
        }

        int mid = start + (end - start) / 2;
        int[] left = merge(nums, start, mid);
        int[] right = merge(nums, mid + 1, end);

        int i = 0;
        int j = 0;
        int m = left.length;
        int n = right.length;

        int[] sorted = new int[n + m];
        int k = 0;

        while (i < m && j < n) {
            if (left[i] < right[j]) {
                sorted[k] = left[i++];
            } else {
                sorted[k] = right[j++];
            }
            k++;
        }

        while (i < m) sorted[k++] = left[i++];
        while (j < n) sorted[k++] = right[j++];

        int rightIndex = 0;
        for (int leftIndex = 0; leftIndex < left.length; leftIndex++) {
            while (rightIndex < right.length && left[leftIndex] > right[rightIndex] * 2.0) rightIndex++;
            count += rightIndex;
        }
        return sorted;
    }
}
