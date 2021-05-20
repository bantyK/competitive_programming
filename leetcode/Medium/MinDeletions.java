import java.util.*;
//1647 https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
public class MinDeletions {

    public static void main(String[] args) {
        System.out.println(
                new MinDeletions().minDeletions("acacabcbedde")
        );
    }

    public int minDeletions(String s) {
        // Sort by frequencies
        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a'] += 1;
        }

        Arrays.sort(count);
        int maxAllowed = count[25];
        int res = 0;

        for (int i = 25; i >= 0; i--) {
            if (count[i] == 0) break;

            if (count[i] > maxAllowed) {
                res += count[i] - maxAllowed; // this number of deletion is required
            } else {
                maxAllowed = count[i]; // the next set of character must all have counts less than this
            }

            if (maxAllowed > 0) maxAllowed--;
        }

        return res;
    }

    public int minDeletions2(String s) {
        Set<Integer> seen = new HashSet<>();
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a'] += 1;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0 && !seen.add(freq[i])) {
                --freq[i];
                ++res;
            }
        }
        return res;
    }

}