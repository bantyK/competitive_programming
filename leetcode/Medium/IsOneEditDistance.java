// 161 https://leetcode.com/problems/one-edit-distance/
public class IsOneEditDistance {
    public static void main(String[] args) {
        IsOneEditDistance obj = new IsOneEditDistance();
        System.out.println(obj.isOneEditDistance("abc", "dbc"));
        System.out.println(obj.isOneEditDistance("abc", "b"));
        System.out.println(obj.isOneEditDistance("abc", "aaabc"));
        System.out.println(obj.isOneEditDistance("abc", "abc"));
    }


    // Optimised solution without recursion //
    public boolean isOneEditDistance(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (Math.abs(sLen - tLen) > 1 || s.equals(t)) return false;

        int minLen = Math.min(sLen, tLen);

        for (int i = 0; i < minLen; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sLen == tLen) {
                    // both strings are of same length
                    // so we can substitute at this index and check is rest of the string are same
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (sLen < tLen) {
                    // t is longer than s, we can delete a char at t and check for the rest
                    return s.substring(i).equals(t.substring(i + 1));
                } else {
                    // s is longer, delete char at s and check for rest
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
        }

        // all chars are same, we just need to check if both the strings differ by 1
        return Math.abs(s.length() - t.length()) <= 1;
    }


    // Solution based on edit distance //
    boolean oneEditMade;

    public boolean isOneEditDistance2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (s.equals(t)) return false;
        if (Math.abs(sLen - tLen) > 1) return false;

        oneEditMade = false;
        return helper(s, t, s.length() - 1, t.length() - 1);
    }

    private boolean helper(String s, String t, int sIndex, int tIndex) {
        if (sIndex < 0) {
            // s has ended
            // if one edit has already been made and tIndex >= 0 then return false
            if (oneEditMade && tIndex >= 0) return false;

            // one edit has not been made, so if there is at-most 1 char left in t then it is valid
            return tIndex < 1;
        }

        if (tIndex < 0) {
            // t has ended
            // if one edit has already been made and sIndex >= 0(always true at this point, so omitted) then return false
            if (oneEditMade) return false;

            // one edit has not been made, so if there is at-most 1 char left in s then it is valid
            return sIndex < 1;
        }

        if (s.charAt(sIndex) == t.charAt(tIndex)) {
            return helper(s, t, sIndex - 1, tIndex - 1);
        }

        if (oneEditMade) return false;

        else {
            oneEditMade = true;
            return helper(s, t, sIndex - 1, tIndex)
                    || helper(s, t, sIndex, tIndex - 1)
                    || helper(s, t, sIndex - 1, tIndex - 1);
        }
    }

}
