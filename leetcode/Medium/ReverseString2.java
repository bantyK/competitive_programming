import java.util.*;

//186 https://leetcode.com/problems/reverse-words-in-a-string-ii/
public class ReverseString2 {
    public static void main(String[] args) {
        ReverseString2 obj = new ReverseString2();
        char[] s = "the sky is blue".toCharArray();
        obj.reverseWords(s);
        System.out.println(Arrays.toString(s));
    }

    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int start = 0;
        while (start < s.length) {
            int end = start;
            while (end < s.length && s[end] != ' ') {
                end++;
            }
            reverse(s, start, end - 1);
            start = end + 1;
        }
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        }
    }
}
