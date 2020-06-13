package sw;

// 1208 https://leetcode.com/problems/get-equal-substrings-within-budget/
public class EqualSubstringWithinBudget {
    public static void main(String[] args) {
        EqualSubstringWithinBudget obj = new EqualSubstringWithinBudget();
        System.out.println(obj.equalSubstring("abcd", "bcde", 3));
        System.out.println(obj.equalSubstring("abcd", "bddd", 3));
        System.out.println(obj.equalSubstring("abcd", "xbcd", 3));
        System.out.println(obj.equalSubstring("abcd", "cdef", 3));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        if (s == null || s.length() == 0) return 0;

        int left = 0;
        int maxLen = 0;
        int diff = 0;
        for (int right = 0; right < s.length(); right++) {
            char rightCharInS = s.charAt(right);
            char rightCharInT = t.charAt(right);

            diff += getDiff(rightCharInS, rightCharInT);

            while (diff > maxCost) {
                char leftCharInS = s.charAt(left);
                char leftCharInT = t.charAt(left);
                diff -= getDiff(leftCharInS, leftCharInT);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private int getDiff(char c1, char c2) {
        int i = c1 - 'a';
        int j = c2 - 'a';
        return Math.abs(i - j);
    }
}
