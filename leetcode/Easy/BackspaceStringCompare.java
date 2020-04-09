import java.util.*;

//844 https://leetcode.com/problems/backspace-string-compare/
public class BackspaceStringCompare {
    public static void main(String[] args) {
        BackspaceStringCompare obj = new BackspaceStringCompare();
        System.out.println(obj.backspaceCompare("ab#c", "ad#c"));
        System.out.println(obj.backspaceCompare("ab##", "c#d#"));
        System.out.println(obj.backspaceCompare("a#c", "b"));
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        for (char c : S.toCharArray()) {
            if (c == '#') {
                if (!s1.isEmpty()) s1.pop();
            } else {
                s1.push(c);
            }
        }

        for (char c : T.toCharArray()) {
            if (c == '#') {
                if (!s2.isEmpty()) s2.pop();
            } else {
                s2.push(c);
            }
        }

        StringBuilder string1 = new StringBuilder();
        while (!s1.isEmpty()) {
            string1.append(s1.pop());
        }
        StringBuilder string2 = new StringBuilder();
        while (!s2.isEmpty()) {
            string2.append(s2.pop());
        }

        return string1.toString().equals(string2.toString());


    }
}
