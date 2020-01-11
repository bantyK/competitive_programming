
// 1312 https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
class MinInsertionPalindrome {
    public int minInsertions(String s) {
        return s.length() - lps(s);
    }
    
    public int lps(String s) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        return lps(s, 0, s.length()-1, dp);
    }
    
    public int lps(String s, int start, int end, Integer[][] dp) {
        if(start > end) return 0;
        
        if(start == end) return 1;
        
        if(dp[start][end] == null) {
            if(s.charAt(start) == s.charAt(end)) {
                return 2 + lps(s, start + 1, end - 1, dp);
            }
            
            int c1 = lps(s, start + 1, end, dp);
            int c2 = lps(s, start, end - 1, dp);
            
            dp[start][end] = Math.max(c1, c2);
        }
        
        return dp[start][end];
    }
}
