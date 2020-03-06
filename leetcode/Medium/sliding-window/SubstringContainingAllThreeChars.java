//1358 https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
public class SubstringContainingAllThreeChars {
    public static void main(String[] args) {
        SubstringContainingAllThreeChars obj = new SubstringContainingAllThreeChars();

        System.out.println(obj.numberOfSubstrings("abcabc"));
        System.out.println(obj.numberOfSubstrings("cbaaaa"));
        System.out.println(obj.numberOfSubstrings("aaaabc"));
        System.out.println(obj.numberOfSubstrings("aaaaaa"));
    }

    public int numberOfSubstrings(String s) {
        int[] count = new int[]{0, 0, 0};
        int n = s.length();
        int res = 0;
        int left = 0;

        int countA = 0;
        int countB = 0;
        int countC = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (c == 'a') countA++;
            else if (c == 'b') countB++;
            else countC++;

            while (countA > 0 && countB > 0 && countC > 0) {
                res += s.length() - right; // count of all substrings which can be formed after the right pointer
                System.out.println("Result : " + res);
                char leftChar = s.charAt(left++);
                if (leftChar == 'a') {
                    countA--;
                } else if (leftChar == 'b') {
                    countB--;
                } else {
                    countC--;
                }
            }

        }

        return res;
    }
}
