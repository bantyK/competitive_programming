import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

// 97 https://leetcode.com/problems/interleaving-string/
public class InterleavingStrings {

    public static void main(String[] args) throws MalformedURLException {
        System.out.println(new InterleavingStrings().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(new InterleavingStrings().isInterleave("aab", "adc", "aaadbc"));
        System.out.println(new InterleavingStrings().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(new InterleavingStrings().isInterleave("", "", ""));
    }

    // bottom up
    private boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        if(len1 + len2 != len3) return false;
        // dp[i][j] -> means can s3[i+j] can be made by s1[0:i] and s2[0:j]
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        dp[0][0] = true; // this means empty s1, s2, s3

        for(int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        for(int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                int c1 = s1.charAt(i - 1);
                int c2 = s2.charAt(j - 1);
                int c3 = s3.charAt(i + j - 1);

                if(c1 == c3 && c2 == c3) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if(c1 == c3) {
                    dp[i][j] = dp[i - 1][j];
                } else if(c2 == c3){
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = false; // not really required, just for understanding
                }
            }
        }

        return dp[len1][len2];
    }


    // top downn
    public boolean isInterleaveTopDown(String s1, String s2, String s3) {
        Map<String, Boolean> cache = new HashMap<>();
        return helper(s1, s2, s3, 0, 0, 0, cache);
    }

    private boolean helper(String s1, String s2, String s3, int i, int j, int k, Map<String, Boolean> cache) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        } else if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        } else if (k == s3.length()) {
            return false;
        } else {
            String key = i + "," + j + "," + k;

            if (cache.containsKey(key)) return cache.get(key);

            boolean matchingWithS1 = false, matchingWithS2 = false;
            if (s1.charAt(i) == s3.charAt(k)) {
                matchingWithS1 = helper(s1, s2, s3, i + 1, j, k + 1, cache);
            }
            if (s2.charAt(j) == s3.charAt(k)) {
                matchingWithS2 = helper(s1, s2, s3, i, j + 1, k + 1, cache);
            }
            boolean res = matchingWithS1 || matchingWithS2;
            cache.put(key, res);
            return res;
        }
    }
}