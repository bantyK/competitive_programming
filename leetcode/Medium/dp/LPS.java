import java.util.*;
public class LPS {
    public static void main(String[] args) {
        String s = "aa";
        int lps = lps(s, 0, s.length()-1);
        System.out.println(lps);
    
    }


    public static int lps(String s, int left, int right) {
        if(s == null || s.length() == 0 || left > right || s.charAt(left) == ' ' || s.charAt(right) == ' ') return 0;
        
        if(left == right) return 1;

        if(s.charAt(left) == s.charAt(right)) {
            return 2 + lps(s, left+1, right-1);
        } else {
            return Math.max(lps(s, left, right-1), lps(s, left+1, right));
        }
    }
}