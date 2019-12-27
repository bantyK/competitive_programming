import java.util.*;
// 1043. https://leetcode.com/problems/partition-array-for-maximum-sum/
public class MaxSubArrayPartitioning {
    public static void main(String[] args) {
        int[] A = new int[]{1, 15, 7, 9, 2, 5, 10};
        int K = 3;
        System.out.println(maxSumAfterPartitioning(A, K));
    }

    public static int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n + K];

        for(int i = 0; i < K - 1; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        for(int i = 0 + K - 1; i < dp.length; i++) {
            Arrays.toString(dp);
            int max = Math.max(dp[i-2], dp[i-1]);
            dp[i] = Math.max(max, maxForward(A, i, K));
        }

        int sum = 0;
        
        for(int i = 0; i < n; i++) {
            sum += dp[i + K - 1];
        }

        return sum;

    }

    private static int maxForward(int[] A, int i, int k) {
        int max = Integer.MIN_VALUE;
        for(int j = 0; j < k; j++) {
            if(A[i + j] > max) {
                max = A[i+j];
            }
        }
        return max;
    }
}