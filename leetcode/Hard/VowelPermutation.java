import java.util.*;

//1220 https://leetcode.com/problems/count-vowels-permutation/
public class VowelPermutation {

    public static void main(String[] args) {
        VowelPermutation obj = new VowelPermutation();
        System.out.println(obj.countVowelPermutation(4));
    }

    public int countVowelPermutation(int n) {
        long[][] dp = new long[n + 1][5];
        // dp[i][j] -> number of strings of length i that can be formed if the the last char is j (j could be a,e,i,o,u)
        // the valid strings could end with any of the 5 vowels, so the final answer is the sum of the last row of dp array

        for (int j = 0; j < 5; j++) {
            dp[1][j] = 1;
        }
        /*
          'a' -> 0
          'e' -> 1
          'i' -> 2
          'o' -> 3
          'u' -> 4

          ith        (i-1)th
          a    ->    [e,i,u]
          e    ->     [a,i]
          i    ->     [e,o]
          o    ->      [i]
          u    ->     [i, o]

          after a we can put e, we will store the reverse of this info
          the value of 0 is [1, 2, 4] this also means 'a' -> [e, i, u]
          this means what can go before a, meaning if we decided to put a in the ith index, than what all chars can go in (i - 1)th index
          we are building the string from right to left
         */
        Map<Integer, List<Integer>> possibleNextMoves = new HashMap<>();
        possibleNextMoves.put(0, Arrays.asList(1, 2, 4));
        possibleNextMoves.put(1, Arrays.asList(0, 2));
        possibleNextMoves.put(2, Arrays.asList(1, 3));
        possibleNextMoves.put(3, Arrays.asList(2));
        possibleNextMoves.put(4, Arrays.asList(2, 3));

        for (int strlen = 2; strlen <= n; strlen++) {
            dp[strlen][0] = (dp[strlen - 1][1] + dp[strlen - 1][2] + dp[strlen - 1][4]) % 1000000007;

            dp[strlen][1] = (dp[strlen - 1][0] + dp[strlen - 1][2]) % 1000000007;

            dp[strlen][2] = (dp[strlen - 1][1] + dp[strlen - 1][3]) % 1000000007;

            dp[strlen][3] = dp[strlen - 1][2];

            dp[strlen][4] = (dp[strlen - 1][2] + dp[strlen - 1][3]) % 1000000007;
        }

        long res = 0;
        for (int i = 0; i < 5; i++) {
            res = (res + dp[n][i]) % 1000000007;
        }

        return (int) res;
    }

}