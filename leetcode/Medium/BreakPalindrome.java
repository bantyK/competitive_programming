// 1328 https://leetcode.com/problems/break-a-palindrome/
public class BreakPalindrome {
    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        char[] str = palindrome.toCharArray();
        if (len == 1) return "";
        for (int i = 0; i < len; i++) {
            int j = len - i - 1;

            if (i == j) {
                // This case will only arise in case of odd length palindromes.
                // it means its the middle element, and no matter how you change it
                // it will always be a palindrome. so just continue
                continue;
            }

            if (palindrome.charAt(i) != 'a') {
                str[i] = 'a';
                return new String(str);
            }
        }

        str[len - 1] = 'b';
        return new String(str);
    }
}
