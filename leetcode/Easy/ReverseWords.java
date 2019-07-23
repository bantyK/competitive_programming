package com.company.leet;


public class ReverseWords {
    public static void main(String[] args) {
        ReverseWords obj = new ReverseWords();
        String s = obj.reverseWords("Let's take LeetCode contest");
        System.out.println(s);
    }

    public String reverseWords(String s) {
        String[] strings = s.split(" ");

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i<strings.length;i++) {
            builder.append(reversedWord(strings[i]));
            if(i != strings.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    private String reversedWord(String str) {
        char[] chars = str.toCharArray();
        int end = chars.length - 1;
        int start = 0;

        while (end >= start) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            end--;
            start++;
        }
        return new String(chars);
    }
}
