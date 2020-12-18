import java.util.Arrays;
import java.util.Comparator;

// 646 https://leetcode.com/problems/maximum-length-of-pair-chain/
public class MaximumLengthOfPairChains {
    public static void main(String[] args) {
        System.out.println(new MaximumLengthOfPairChains().findLongestChain(new int[][]{{1,2},{2,3},{3,4}}));
        System.out.println(new MaximumLengthOfPairChains().findLongestChain(new int[][]{{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}}));
    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(arr -> arr[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int longest = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (pairs[i][0] > pairs[j][1] && dp[i] <= dp[j]) {
                    dp[i] = 1 + dp[j];
                    longest = Math.max(longest, dp[i]);
                }
            }
        }

        return longest;

    }
}