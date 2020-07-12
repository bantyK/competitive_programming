import java.util.*;

//1513 https://leetcode.com/problems/number-of-substrings-with-only-1s/
public class NumSubstringWithOnly1 {
    public static void main(String[] args) {
        NumSubstringWithOnly1 obj = new NumSubstringWithOnly1();
    }

    public int numSub(String s) {
        long res = 0;
        long count = 0;
        long mod = 1000000000 + 7;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '1') {
                count += 1;
            } else {
                res += (count + 1) * count / 2;
                count = 0;
            }
        }
        res += (count + 1) * count / 2;

        return (int)(res % mod);
    }
}
