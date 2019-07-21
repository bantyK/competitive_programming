package com.company.leet;


public class StringToNumber {

    public static void main(String[] args) {
        StringToNumber obj = new StringToNumber();
        System.out.println(obj.myAtoi("-2147483647"));
    }

    public int myAtoi(String str) {
        str = removeLeadingWhiteSpace(str);
        double result = 0;
        int index = 1;
        int startIndex = 0;
        char[] strArray = str.toCharArray();

        if (strArray.length == 0)
            return 0;
        if (strArray[0] == '-') {
            index = -1;
            startIndex = 1;
        } else if (strArray[0] == '+') {
            startIndex = 1;
        } else if (!Character.isDigit(strArray[0])) {
            return 0;
        }

        for (int i = startIndex; i < strArray.length; i++) {
            if (Character.isDigit(strArray[i])) {
                result = (result * 10) + Character.digit(strArray[i], 10);

                if (result >= Integer.MAX_VALUE) {
                    if (index > 0)
                        return Integer.MAX_VALUE;
                    else
                        return Integer.MIN_VALUE;
                }
            } else {
                return index * (int) result;
            }
        }

        return index * (int) result;
    }

    private String removeLeadingWhiteSpace(String str) {
        if (str.isEmpty())
            return str;

        int i = 0;

        while (i < str.length()) {
            if (!Character.isWhitespace(str.charAt(i))) {
                break;
            } else {
                i++;
            }
        }

        return str.substring(i);
    }
}
