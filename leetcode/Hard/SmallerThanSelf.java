import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//315 https://leetcode.com/problems/count-of-smaller-numbers-after-self/
// Explanation: https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/445769/merge-sort-CLEAR-simple-EXPLANATION-with-EXAMPLES-O(n-lg-n)
public class SmallerThanSelf {

    public static void main(String[] args) {
        System.out.println(new SmallerThanSelf().countSmaller(new int[]{5, 2, 6, 4, 7, 9}));
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        int n = nums.length;
        int[] res = new int[n];

        ArrayValWithOrigIndex[] newNums = new ArrayValWithOrigIndex[n];

        for (int i = 0; i < n; i++) {
            newNums[i] = new ArrayValWithOrigIndex(nums[i], i);
        }

        mergeSortAndCount(newNums, 0, n - 1, res);

        List<Integer> ret = new ArrayList<>();
        for (int num : res) ret.add(num);
        return ret;
    }

    private void mergeSortAndCount(ArrayValWithOrigIndex[] nums, int start, int end, int[] res) {
        if (start >= end) return;

        int mid = (start + end) / 2;

        mergeSortAndCount(nums, start, mid, res);
        mergeSortAndCount(nums, mid + 1, end, res);

        int leftPos = start;
        int rightPos = mid + 1;

        List<ArrayValWithOrigIndex> merged = new LinkedList<>();
        int numElemsInRightSubarrayLessThanLeftSubArray = 0; // this keep track of the number of elements in right subarray which are less than numbers in right subarray


        while (leftPos <= mid && rightPos <= end) {
            if (nums[leftPos].val > nums[rightPos].val) {
                // this is the case that we are looking for
                // the num at the right hand side is greater than the number at the left hand side
                ++numElemsInRightSubarrayLessThanLeftSubArray;
                merged.add(nums[rightPos]);
                rightPos++;
            } else {
                res[nums[leftPos].index] += numElemsInRightSubarrayLessThanLeftSubArray;
                merged.add(nums[leftPos]);
                ++leftPos;
            }
        }

        while (leftPos <= mid) {
            res[nums[leftPos].index] += numElemsInRightSubarrayLessThanLeftSubArray;
            merged.add(nums[leftPos]);
            leftPos++;
        }

        while (rightPos <= end) {
            merged.add(nums[rightPos]);
            ++rightPos;
        }

        int pos = start;
        for (ArrayValWithOrigIndex num : merged) {
            nums[pos++] = num;
        }
    }

    private static class ArrayValWithOrigIndex {
        int val;
        int index;

        public ArrayValWithOrigIndex(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}