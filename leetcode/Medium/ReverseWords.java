package solutions.medium;

import solutions.util.ArrayUtils;

public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords obj = new ReverseWords();
        String s = obj.reverseWords("  hello world!  ");
        System.out.println(s);
    }

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder builder = new StringBuilder();

        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() > 0) {
                builder.append(split[i]);
                if (i > 0) {
                    builder.append(" ");
                }
            }
        }

        return builder.toString().trim();
    }
}
