import java.util.*;

// 906 https://leetcode.com/problems/super-palindromes/
public class SuperPalindrrome {
    public int superpalindromesInRange(String left, String right) {
        long leftVal = Long.parseLong(left);
        long rightVal = Long.parseLong(right);

        List<Long> palindrome = new ArrayList<>();

        for (long i = 1; i < 10; i++) {
            palindrome.add(i);
        }

        for (long i = 1; i <= 10000; i++) {
            String leftPart = Long.toString(i);
            String rightPart = new StringBuilder(leftPart).reverse().toString();

            palindrome.add(Long.parseLong(leftPart + rightPart));

            for (int j = 0; j < 10; j++) {
                palindrome.add(Long.parseLong(leftPart + j + rightPart));
            }
        }

        int count = 0;
        for (long num : palindrome) {
            long sq = num * num;
            if (sq >= leftVal && sq <= rightVal && isPalindrome(String.valueOf(sq))) {
                count++;
            }
        }
        return count;
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}