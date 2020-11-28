// 395 https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
public class LongestSubstringWithKRepeatingChars {

    public static void main(String[] args) {
        LongestSubstringWithKRepeatingChars obj = new LongestSubstringWithKRepeatingChars();
        System.out.println(obj.longestSubstring("bbaaa", 3));
    }

    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length() - 1, k);
    }

    private int helper(String s, int low, int high, int k) {
        if (high - low + 1 < k) {
            // if the string itself has less than k characters, then it is not possible to
            // make a valid string
            return 0;
        }

        // count of all characters in the string
        int[] charFrequency = new int[26];
        for (int i = low; i <= high; i++) {
            charFrequency[s.charAt(i) - 'a']++;
        }

        int left = low;
        int maxLen = 0;

        for (int right = low; right <= high; right++) {
            if (charFrequency[s.charAt(right) - 'a'] < k) {
                // if the frequency of a char is less than k, then that cannot be the part of
                // a valid substring.
                // It divides the string into 2 parts (left and right). We will look for valid substrings at both side
                // we dont know how many substrings are present in the right part but for the left
                // part, we know  that it is between i and j - 1. (char At j can't be the part of it)

                int len = helper(s, left, right - 1, k);
                maxLen = Math.max(maxLen, len);
                left = right + 1; // j cant be a part of a valid substring, to skipping it
            }
        }

        if (left == low) {
            //If even after the for loop, the value of left remains unchanged then it means there are
            // no char in the string with freq less than k, meaning the entire string is a valid string
            return high - low + 1;
        }

        // we have to again call the helper because the low and left might be different and a
        // last segment might remain which we need to count consider the ex: bbaaac
        return Math.max(maxLen, helper(s, left, high, k));
    }


}