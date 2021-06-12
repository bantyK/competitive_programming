// 1400 https://leetcode.com/problems/construct-k-palindrome-strings/
public class ConstructKPalindromes {

    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) return false;
        if (n == k) return true;

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a'] += 1;
        }
        // the characters which occur in odd frequencies will decide how many palindromes can be made
        // there are more than k odd count character, than while making the palindromes, we cannot use
        // all of them, which violates the question given in the problem

        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount += 1;
            }
        }

        return oddCount <= k;
    }
}