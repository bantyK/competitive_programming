// 413 https://leetcode.com/problems/arithmetic-slices/
public class ArithmeticSequences {

    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        int count = 0;
        for(int i = 2; i < A.length; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                count += dp[i];
            }
        }


        return count;
    }

    // O(n2) solution
    public int numberOfArithmeticSlices1(int[] A) {
        int count = 0;

        for(int i = 0; i < A.length - 2; i++) {
            int cd = A[i + 1] - A[i];

            for(int j = i + 2; j < A.length; j++) {
                if(A[j] - A[j - 1] == cd) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

}