import java.util.HashMap;
import java.util.Map;

//1027 https://leetcode.com/problems/longest-arithmetic-subsequence/
public class LongestArithmeticSequence {

    public static void main(String[] args) {
        LongestArithmeticSequence obj = new LongestArithmeticSequence();
        System.out.println(obj.longestArithSeqLength(new int[]{20, 1, 15, 3, 10, 5, 8}));
    }

    // Similar to LIS
    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        int longest = 0;

        // At each index we are going to maintain a list of APs of all possible common difference
        // {common difference, count} for each index
        Map<Integer, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<Integer, Integer>();
        }

        for (int right = 1; right < n; right++) {
            for (int left = 0; left < right; left++) {
                int commonDiff = A[right] - A[left];
                int minLen = 2; // minimum len will be 2, because 2 elements are checked at the minimum

                if (dp[left].containsKey(commonDiff)) {
                    // left already contains this CD. Right will be appended to this sequence
                    minLen = dp[left].get(commonDiff) + 1;
                }

                // current value which is stored in the DP
                int currLengthAtRight = dp[right].getOrDefault(commonDiff, 0);

                // New value will be maximum of old and the len
                dp[right].put(commonDiff, Math.max(currLengthAtRight, minLen));

                // keep track of the maximum value to return at end
                longest = Math.max(longest, dp[right].get(commonDiff));
            }
        }

        return longest;
    }
}