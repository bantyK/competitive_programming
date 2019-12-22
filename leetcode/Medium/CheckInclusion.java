import java.util.*;

// 567 https://leetcode.com/problems/permutation-in-string/
public class CheckInclusion {
    public static void main(String[] args) {
        CheckInclusion obj = new CheckInclusion();
        System.out.println(obj.checkInclusion2("adc", "dcda"));
    }

    public boolean checkInclusion2(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();

        if (lenA > lenB) return false;

        int[] charCount = new int[26];
        int[] window = new int[26];
        Arrays.fill(charCount, 0);
        Arrays.fill(window, 0);

        for (char c : a.toCharArray()) {
            charCount[c - 'a'] += 1;
        }


        for (int i = 0; i < lenA; i++) {
            window[b.charAt(i) - 'a']++;
        }

        for (int i = 0; i < b.length() - lenA; i++) {
            if (same(window, charCount)) return true;
            else {
                window[b.charAt(i) - 'a']--;
                window[b.charAt(i + lenA) - 'a']++;
            }
        }

        return same(window, charCount);
    }

    private boolean same(int[] a1, int[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) return false;
        }

        return true;
    }

    public boolean checkInclusion(String a, String b) {
        if (b.isEmpty()) return false;
        else if (a.isEmpty()) return true;
        else {
            Set<Integer> visited = new HashSet<>();
            return generatePermutations(a, b, new StringBuilder(), visited);
        }
    }

    private boolean generatePermutations(String a, String b, StringBuilder builder, Set<Integer> visited) {
        // a should be inside b
        if (builder.length() == a.length()) {
            return b.contains(builder.toString());
        }

        for (int i = 0; i < a.length(); i++) {
            if (visited.contains(i)) continue;

            visited.add(i);
            builder.append(a.charAt(i));
            if (generatePermutations(a, b, builder, visited)) return true;
            builder.deleteCharAt(builder.length() - 1);
            visited.remove(i);
        }

        return false;
    }
}
