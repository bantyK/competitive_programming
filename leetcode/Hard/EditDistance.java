import java.util.*;

public class EditDistance {

    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        System.out.println(obj.minDistance("horse", "ros"));
        System.out.println(obj.minDistance("intention", "execution"));
    }

    public int minDistance(String word1, String word2) {
        Integer[][] dp = new Integer[word1.length()][word2.length()];
        return helper(word1, word2, word1.length() - 1, word2.length() - 1, dp);
    }

    // word1 needs to be converted to word2
    private int helper(String word1, String word2, int index1, int index2, Integer[][] dp) {
        // 2 bases which handles, if either of the 2 words, ends earlier than the other
        // If they are of different length
        if (index1 < 0) {
            // We are exploring the words from end, so if index1 < 0 means -
            // word1 has been finished, all the remaining characters of word2 needs to be
            // inserted to make word1 equal to word2
            return index2 + 1;
        }

        if (index2 < 0) {
            // word2 has been completely explored, this means that all the remaining chars of
            // word1 needs to be added into word1 to make it equal to word2
            return index1 + 1;
        }

        // memoization
        if (dp[index1][index2] != null) return dp[index1][index2];

        if (word1.charAt(index1) == word2.charAt(index2)) {
            // last chars of both the words are same, no need to do anything here.
            // recurse to check the remaining strings
            return helper(word1, word2, index1 - 1, index2 - 1, dp);
        }

        // chars are not same.
        // 3 actions are possible. All these actions need to be taken into word1
        // 1. Insertion
        // 2. Deletion
        // 3. Substitution

        // A character is inserted at the end word1.
        // This newly inserted char matches with the last char of word2.
        // So, word2's index will decrease, but since we added into the word1, its length increases
        // if we look at index, we still have to check the same index
        int stepsWhenInserted = 1 + helper(word1, word2, index1, index2 - 1, dp);

        // Last character is deleted from word1
        int stepsWhenDeleted = 1 + helper(word1, word2, index1 - 1, index2, dp);

        // Last character of word2 is substituted to make both chars equal
        int stepsWhenSubstituted = 1 + helper(word1, word2, index1 - 1, index2 - 1, dp);

        // Whichever of these options gives us the minimum result is our answer

        return dp[index1][index2] = Math.min(stepsWhenDeleted, Math.min(stepsWhenInserted, stepsWhenSubstituted));
    }


    // Bottom up solution of the above code
    public int minDistanceBottomUp(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        // first row represent s1 is empty. We have to insert all chars from s2
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        // first columnn represent s2 is Empty. We have to delete all chars from s1
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }


        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[len1][len2];
    }
}
