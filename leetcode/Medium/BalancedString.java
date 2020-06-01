
//1234 https://leetcode.com/problems/replace-the-substring-for-balanced-string/
public class BalancedString {
    public static void main(String[] args) {
        BalancedString obj = new BalancedString();
        System.out.println(obj.balancedString("QQER"));
    }

    /**
     * This question is a variant of finding the minimum length substring, which contains some specific number of
     * some characters.
     *
     * Find min length substring which contains 2 Q, 3W and 1R (something like this)
     * @param s
     * @return
     */
    public int balancedString(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] freq = new int[4];

        int n = s.length();


        for(int i = 0; i < n; i++) {
            ++freq[charToIdx(s.charAt(i))];
        }

        int req = n / 4;
        boolean equal = true;

        for(int i = 0; i < 4; i++) {
            int extra = freq[i] - req;
            if(extra != 0) equal = false;
            freq[i] = Math.max(0, extra);
        }

        // if equal is true, that means we have no extra chars, so simply return.
        if(equal) return 0;

        // At this point, freq array contains the count of each char that we need inside our window.
        int left = 0, minLen = n;

        for(int right = 0; right < n; right++) {

            // untill all chars are consumed, keep increasing the window to the right.
            char rightChar = s.charAt(right);
            --freq[charToIdx(rightChar)];

            while(areAllExtraCharsConsumed(freq)) {
                // once all chars are consumed, start decreasing the size of window, keeping
                // the constraints. We need the min length, hence the shrinking

                minLen = Math.min(minLen, right - left + 1);

                char leftChar = s.charAt(left++);
                ++freq[charToIdx(leftChar)];
            }
        }
        return minLen;
    }

    private int charToIdx(char c) {
        switch(c) {
            case 'Q': return 0;
            case 'W': return 1;
            case 'E': return 2;
            case 'R': return 3;
        }
        throw new IllegalArgumentException("Invalid character");
    }

    // If this function returns negative, that means we have more number of chars in the window than we require.
    // so even in that case, we will keep shrinking the window.

    // Example, we have one extra Q, but freq[Q] = -1, that means we have 2 Qs in the window
    // Try with example. QWRR for more understanding
    private boolean areAllExtraCharsConsumed(int[] freq) {
        for(int f : freq) {
            if(f > 0) return false;
        }
        return true;
    }
}
