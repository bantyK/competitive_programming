// 880 https://leetcode.com/problems/decoded-string-at-index/
public class DecodeAtIndex {
    public String decodeAtIndex(String S, int K) {
        long sizeDecodedString = 0;
        int N = S.length();

        // Find the size of the decoded string
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (Character.isDigit(ch)) {
                sizeDecodedString *= (ch - '0');
            } else {
                sizeDecodedString++;
            }
        }

        // Start working from backwards
        for (int i = N - 1; i >= 0; i--) {
            char ch = S.charAt(i);
            K %= sizeDecodedString;

            if (K == 0 && Character.isLetter(ch)) {
                return Character.toString(ch);
            }

            if (Character.isDigit(ch)) {
                int repeatCount = ch - '0';
                sizeDecodedString /= repeatCount;
            } else {
                sizeDecodedString--;
            }
        }

        // will not get executed
        throw null;
    }
}