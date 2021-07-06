import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/number-of-wonderful-substrings/
public class WonderfulSubstring {

    public static void main(String[] args) {
        WonderfulSubstring obj = new WonderfulSubstring();
        System.out.println(obj.wonderfulSubstrings("aba"));
        System.out.println(obj.wonderfulSubstrings2("aba"));
    }

    public long wonderfulSubstrings(String word) {
        Map<Long, Long> count = new HashMap<>();
        count.put(0L, 1L);
        long mask = 0;
        long res = 0;

        for (char ch : word.toCharArray()) {
            mask = mask ^ (1 << (ch - 'a'));

            // if the parity at some index i is repeating again at some index j (i < j)
            // this means that count of all chars between i + 1 and j is even (or the chars occur in pairs)
            // this is because of the fact that xor of two numbers is 0 [A ^ A = 0]
            // similar to subarray sum equals k problem
            res += count.getOrDefault(mask, 0L);

            count.put(mask, count.getOrDefault(mask, 0L) + 1);

            // Important
            // we are basically removing one character at a time from the current mask
            // If the mask is currently 0010010101 and we want to remove character B(0000000010) from it
            // we will xor B's value from the mask and B will be removed
            // After removing this character, if we find out that we have the new mask value in the map
            // this means that this character occurs odd number of times in the substring
            // https://www.youtube.com/watch?v=TTFrsi6iDog

            for (char c = 'a'; c <= 'j'; c++) {
                long key = mask ^ (1 << (c - 'a'));
                res += count.getOrDefault(key, 0L);
            }
        }

        return res;
    }

    public long wonderfulSubstrings2(String word) {
        // set bit -> character has been encountered odd number of times
        // unset bit -> character has been encountered even number of times

        // We need 2 types of substrings (atmost is given in the question so need to count 0 as well as 1):
        // 1. Count of all chars in the substring is even (count of odd occurring chars = 0)
        // 2. Count of 1 char is odd

        Map<Long, Long> count = new HashMap<>();
        count.put(0L, 1L);
        long running = 0;
        long res = 0;

        for (char c : word.toCharArray()) {
            running = running ^ (1 << (c - 'a'));

            // if the parity at some index i is repeating again at some index j (i < j)
            // this means that count of all chars between i and j - 1 is even
            // similar to subarray sum equals k problem
            res += count.getOrDefault(running, 0L);
            count.put(running, count.getOrDefault(running, 0L) + 1);

            for (char cc = 'a'; cc <= 'j'; cc++) {
                long key = running ^ (1 << (cc - 'a'));
                res += count.getOrDefault(key, 0L);
            }

        }
        return res;
    }
}