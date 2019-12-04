// 5 https://leetcode.com/problems/longest-palindromic-substring/

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        LongestPalindromeSubstring obj = new LongestPalindromeSubstring();
        String res = obj.longestPalindrome("babad");
        System.out.println(res);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        int length = s.length();
        boolean[][] isPalindrome = new boolean[length][length];

        int start = 0;
        int end = 0;

        for (int j = 1; j < length; ++j) {
            for (int i = 0; i < j; ++i) {
                boolean isInnerWordPalindrome = isPalindrome[i + 1][j - 1] || j - i <= 2;

                if (s.charAt(i) == s.charAt(j) && isInnerWordPalindrome) {
                    isPalindrome[i][j] = true;

                    if (j - i > end - start) {
                        start = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }

    private void printDp(boolean[][] isPalindrome) {
        for (boolean[] arr : isPalindrome) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
