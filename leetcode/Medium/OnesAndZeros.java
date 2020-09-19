//474 https://leetcode.com/problems/ones-and-zeroes/
public class OnesAndZeros {
    public static void main(String[] args) {
        OnesAndZeros obj = new OnesAndZeros();
        System.out.println(obj.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

    // Bottom up
    public int findMaxFormBottomUp(String[] strs, int m, int n) {
        final int len = strs.length;
        Integer[][][] dp = new Integer[len + 1][m + 1][n + 1];

        for (int i = 0; i <= len; i++) {
            int countZeros = 0;
            int countOnes = 0;
            if (i > 0) {
                countZeros = countZeros(strs[i]);
                countOnes = strs[i].length() - countZeros;
            }

            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    if (i == 0) {
                        dp[i][j][k] = 0;
                    } else if (j >= countZeros && k >= countOnes) {
                        int takeStringAtI = dp[i - 1][j - countZeros][k - countOnes];
                        int skipStringAtI = dp[i - 1][j][k];
                        dp[i][j][k] = Math.max(skipStringAtI, 1 + takeStringAtI);
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                }
            }
        }

        return dp[len][m][n];
    }

    /**
     * @param strs
     * @param m    number of zeros
     * @param n    number of ones
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        Integer[][][] dp = new Integer[m + 1][n + 1][strs.length];
        return helper(strs, m, n, 0, dp);
    }

    private int helper(String[] strs, int m, int n, int strIndex, Integer[][][] dp) {
        if (strIndex == strs.length || m + n == 0) {
            // m + n == 0 essentially means m and n are both zeros because m and n are both positive intergers. if the sum is 0 means both are 0.
            return 0;
        }

        if (dp[m][n][strIndex] != null) {
            return dp[m][n][strIndex];
        }

        String current = strs[strIndex];

        int countOfZerosInCurrentString = countZeros(current);
        int countOfOnesInCurrentString = current.length() - countOfZerosInCurrentString;

        int countIfCurrentIsTaken = 0;
        if (m >= countOfZerosInCurrentString && n >= countOfOnesInCurrentString) {
            countIfCurrentIsTaken = 1 + helper(strs, m - countOfZerosInCurrentString, n - countOfOnesInCurrentString, strIndex + 1, dp);
        }
        int countIfCurrentIsSkipped = helper(strs, m, n, strIndex + 1, dp);

        dp[m][n][strIndex] = Math.max(countIfCurrentIsTaken, countIfCurrentIsSkipped);
        return dp[m][n][strIndex];
    }

    private int countZeros(String str) {
        int count0 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                count0++;
            }
        }
        return count0;
    }
}
