//392 https://leetcode.com/problems/is-subsequence/
public class IsSubsequence {
    public static void main(String[] args) {
        IsSubsequence obj = new IsSubsequence();

        System.out.println(obj.isSubsequence("abc", "afgbhsc"));
        System.out.println(obj.isSubsequence("abc", "afgchsb"));
        System.out.println(obj.isSubsequence("abc", "afgsb"));
    }

    public boolean isSubsequence(String s, String t) {
        return helper(s, t, 0, 0);
    }

    private boolean helper(String s, String t, int sIndex, int tIndex) {
        if(sIndex == s.length()) {
            return true;
        }

        if(tIndex == t.length()) {
            return false;
        }

        if(s.charAt(sIndex) == t.charAt(tIndex)) {
            return helper(s, t, sIndex + 1, tIndex + 1);
        }

        return helper(s, t, sIndex, tIndex + 1);
    }
}
