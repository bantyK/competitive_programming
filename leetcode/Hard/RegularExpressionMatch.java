// 10 https://leetcode.com/problems/regular-expression-matching/
public class RegularExpressionMatch {
    Result[][] memo;

    public static void main(String[] args) {
        RegularExpressionMatch obj = new RegularExpressionMatch();
        System.out.println(obj.isMatch("bbac", "b*ac"));
    }

    /**
     * Recursive approach. Exponential complexity
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        if (p == null || p.length() == 0) {
            return s == null || s.length() == 0;
        }

        boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            boolean notUse = isMatch(s, p.substring(2));
            boolean use = firstMatch && isMatch(s.substring(1), p);
            return notUse || use;
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatchMemo(String s, String p) {
        memo = new Result[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }

        boolean ans;

        if (j == p.length()) {
            ans = i == s.length();
        } else {
            boolean firstCharMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dp(i, j + 2, s, p) || firstCharMatch && dp(i + 1, j, s, p);
            } else {
                ans = firstCharMatch && dp(i + 1, j + 1, s, p);
            }

        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    public boolean isMatch(String s, String p) {
        if (p == null || p.length() == 0) {
            return s == null || s.length() == 0;
        }

        if (p.length() == 1) {
            if (s == null || s.length() == 0) {
                return false;
            }

            if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }

        if (p.charAt(1) != '*') {
            if (s.length() == 0) {
                return false;
            }
            if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') {
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        } else {
            // for the case where we don't have to use the char before * at all.("bbb", "a*bbb")
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            // case where we do have to use the char before * at least once.
            int i = 0;
            while (i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    static enum Result {
        TRUE,
        FALSE
    }
}
