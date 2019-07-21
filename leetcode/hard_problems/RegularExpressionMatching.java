package leetcode;

public class RegularExpressionMatching {
    public static void main(String[] args) {
        RegularExpressionMatching obj = new RegularExpressionMatching();
        String s = "aaa";
        String p = "ab*ac*a";
        System.out.println(obj.isMatch(s, p));
    }

    private boolean isMatch(String s, String p) {
        int i = s.length() - 1;
        int j = p.length() - 1;

        while (i > 0) {
            if (j <= 0) return false;
            if (p.charAt(j) == '*') {
                char previousChar = p.charAt(j - 1);
                if (previousChar == '.') {
                    previousChar = s.charAt(i);
                }
                if (previousChar != s.charAt(i)) {
                    return false;
                } else {
                    while (i > 0 && s.charAt(i) == previousChar) {
                        i--;
                    }
                }
                j -= 2;
            } else if (p.charAt(j) != '.') {
                if (s.charAt(i) != p.charAt(j)) {
                    return false;
                }
                i--;
                j--;
            } else {
                i--;
                j--;
            }
        }
        while (j > 0) {
            if (p.charAt(j) == '*') {
                j -= 2;
            } else {
                return false;
            }
        }
        return true;
    }
}