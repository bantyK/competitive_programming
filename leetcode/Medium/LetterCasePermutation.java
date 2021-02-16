import java.util.*;

public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation obj = new LetterCasePermutation();
        System.out.println(obj.letterCasePermutation("a1b2k7mx"));
        System.out.println(obj.letterCasePermutation("12345"));
    }

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        helper(s, 0, new StringBuilder(), res);
        return res;
    }

    private void helper(String s, int index, StringBuilder builder, List<String> res) {
        if (builder.length() == s.length()) {
            res.add(builder.toString());
            return;
        }

        if (Character.isLetter(s.charAt(index))) {
            int prevLen = builder.length();
            builder.append(Character.toLowerCase(s.charAt(index)));
            helper(s, index + 1, builder, res);
            builder.setLength(prevLen);

            builder.append(Character.toUpperCase(s.charAt(index)));
            helper(s, index + 1, builder, res);
            builder.setLength(prevLen);
        } else {
            builder.append(s.charAt(index));
            helper(s, index + 1, builder, res);
        }
    }
}