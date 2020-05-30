import java.util.*;

// 1358 https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

public class SubstringCount {
    public static void main(String[] args) {
        SubstringCount obj = new SubstringCount();
//        System.out.println(obj.numberOfSubstrings("abcabc"));
        System.out.println(obj.numberOfSubstrings("aaabc"));
    }

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int countA = 0, countB = 0, countC = 0;
        int left = 0, res = 0;
        for (int right = 0; right < n; right++) {
            switch (s.charAt(right)) {
                case 'a':
                    countA++;
                    break;
                case 'b':
                    countB++;
                    break;
                case 'c':
                    countC++;
                    break;
            }

            while (left < n && countA > 0 && countB > 0 && countC > 0) {
                // if the window[i, j] contains all the letters, then all the substring [i, j + 1], [i , j + 2] ..... [i, n - 1] will also
                // satisfy the constraints. "abcabc", left = 0, right = 2 satisfy the constraint, so left = 0, right = 3 will also satisfy the
                // constraints.

                res += n - right;

                // Once all the substrings which end at right pointers are counted, we increment the left and look for other substrings
                // which now start at (left + 1) and satisfy the constraints.
                switch (s.charAt(left++)) {
                    case 'a':
                        countA--;
                        break;
                    case 'b':
                        countB--;
                        break;
                    case 'c':
                        countC--;
                        break;
                }
            }
        }

        return res;
    }
}
