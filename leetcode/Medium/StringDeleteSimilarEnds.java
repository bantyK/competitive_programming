//1750 https://leetcode.com/problems/minimum-length-of-string-after-deleting-similar-ends/

public class StringDeleteSimilarEnds {

    public int minimumLength(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int left, int right) {
        if (left == right) {
            return 1;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return right - left + 1;
        }
        int i = left;
        char ch = s.charAt(left);
        while (i < s.length() && s.charAt(i) == ch) {
            i++;
        }
        if (i == s.length()) return 0;

        int j = right;
        while (j > i && s.charAt(j) == ch) {
            j--;
        }

        return helper(s, i, j);
    }
}
