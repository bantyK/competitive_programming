import java.util.*;

//859 https://leetcode.com/problems/buddy-strings/submissions/
public class BuddyStrings {
    public static void main(String[] args) {
        BuddyStrings obj = new BuddyStrings();
        System.out.println(obj.buddyStrings("abc", "acb"));
        System.out.println(obj.buddyStrings("abc", "acd"));
    }

    public boolean buddyStrings(String A, String B) {
        int len1 = A.length();
        int len2 = B.length();
        if (len1 == 0 || len2 == 0) return false;
        if (len1 != len2) return false;

        if (A.equals(B)) {
            Set<Character> set = new HashSet<>();
            for (char c : A.toCharArray()) set.add(c);
            return set.size() < len1;
        }

        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < len1; i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diff.add(i);
                if (diff.size() > 3) return false;
            }
        }

        if (diff.size() < 2) return false;
        int idx1 = diff.get(0);
        int idx2 = diff.get(1);
        return A.charAt(idx1) == B.charAt(idx2) && A.charAt(idx2) == B.charAt(idx1);
    }
}
