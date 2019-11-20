import java.util.*;
// 387 https://leetcode.com/problems/first-unique-character-in-a-string/
public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        int idx = firstUniqChar("dddccdbba");
        System.out.println(idx);
    }

    public static int firstUniqChar(String s) {
        int[] count = new int[26];
        Arrays.fill(count, 0);

        int index = 0;
        for(int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            count[index] += 1;

        }

        for(int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            if(count[index] == 1) {
                return i;
            }
        }

        for(int i = 0; i < count.length; i++) System.out.print(count[i] + " ");
        return -1;
    }
}