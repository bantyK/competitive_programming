import java.util.Arrays;

//1231 https://leetcode.com/problems/divide-chocolate/
public class DivideChocolates {

    public int maximizeSweetness(int[] nums, int K) {
        int low = 1;
        int high = Arrays.stream(nums).reduce(0, Integer::sum) / (K + 1);

        int res = 0;

        while (low <= high) {
            int mid = low + (high - low + 1) / 2;
            if (numGroups(nums, mid) < K + 1) {
                // we cannot make K + 1 splits considering the mid as the minimum sweetness
                // we need to decrease this value
                high = mid - 1;
            } else {
                // we can make K + 1 split, try to maximize this value
                low = mid;
            }
        }
        return low;
    }

    private int numGroups(int[] nums, int mid) {
        // mid is the minimum sum of sweetness that each subarray must have.
        // Its ok if some of the sub-arrays have sweetness greater than this value, but none should have less tha this
        // Can we make K + 1 groups.
        // If we cannot make K + 1 groups, the value of sweetness is too large, and we need to decrease the sweetness value, high = mid - 1
        // If we can make K + 1 groups, this is a valid choice, we need to maximize this value, low = mid

        int numPieces = 0;
        int sweetness = 0;
        for (int num : nums) {
            sweetness += num;
            if (sweetness >= mid) {
                numPieces++;
                sweetness = 0;
            }
        }
        return numPieces;
    }
}
