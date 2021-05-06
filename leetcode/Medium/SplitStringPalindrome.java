//1616 https://leetcode.com/problems/split-two-strings-to-make-palindrome/
public class SplitStringPalindrome {

    public static void main(String[] args) {
        SplitStringPalindrome obj = new SplitStringPalindrome();
        System.out.println(obj.checkPalindromeFormation("ulacfd", "jizalu"));
    }

    // Optimized sol
    public boolean checkPalindromeFormation(String a, String b) {
        return checkFormation(a, b) || checkFormation(b, a);
    }

    private boolean checkFormation(String a, String b) {
        int i = 0, j = a.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            ++i;
            --j;
        }

        if (i >= j) return true;

        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right && s.charAt(left) == s.charAt(right)) {
            ++left;
            --right;
        }

        return left == right;
    }

    /// My solution
    public boolean checkPalindromeFormation2(String a, String b) {
        if (isPalindrome(a) || isPalindrome(b)) return true;
        int n = a.length();

        if (a.charAt(0) == b.charAt(n - 1)) {
            if (check(a, b)) return true;
        }

        if (a.charAt(n - 1) == b.charAt(0)) {
            return check(b, a);
        }

        return false;
    }

    private boolean check(String a, String b) {
        int n = a.length();

        if (a.charAt(0) == b.charAt(n - 1)) {
            // first char of first string is same as last char of second string.
            // there is a possibility of palindrome
            int aLeft = 0, bRight = n - 1;
            while (aLeft < bRight && a.charAt(aLeft) == b.charAt(bRight)) {
                aLeft++;
                bRight--;
            }


//            if (bRight - aLeft <= 2) return true;

            String left = a.substring(0, aLeft);
            String right = b.substring(aLeft);
            String aRemaining = a.substring(aLeft, n - aLeft);
//            String toCheck = left + (isPalindrome(aRemaining) ? aRemaining : "") + right;
            return isPalindrome(aRemaining) || isPalindrome(left + right);
        }
        return false;
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}