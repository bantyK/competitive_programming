import java.util.*;
// 1704 https://leetcode.com/problems/determine-if-string-halves-are-alike/
public class HalvesAlike {
    public static void main(String[] args) {
        HalvesAlike obj = new HalvesAlike();
        System.out.println(obj.halvesAreAlike("MerryChristmas"));
    }

    public boolean halvesAreAlike(String s) {
        s = s.toLowerCase();
        int leftEnd = s.length() / 2;
        int vowelCountA = 0, vowelCountB = 0;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        for (int i = 0; i < leftEnd; i++) {
            char a = s.charAt(i);
            if (vowels.contains(a)) vowelCountA++;
            char b = s.charAt(leftEnd + i);
            if (vowels.contains(b)) vowelCountB++;
        }

        return vowelCountA == vowelCountB;
    }

}