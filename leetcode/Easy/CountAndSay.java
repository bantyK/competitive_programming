package com.company.leet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountAndSay {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("countsay.in"));
        CountAndSay countAndSay = new CountAndSay();
        String line = reader.readLine();
        while (line != null) {
            int n = Integer.parseInt(line.split(":")[0]);
            String result = countAndSay.countAndSay(n);
//            System.out.println(result);
            System.out.println(result.equalsIgnoreCase(line.split(":")[1]));
            line = reader.readLine();
        }

    }

    public String countAndSay(int n) {
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = sayCount(result);
        }
        return result;
    }

    /*
     * count the digits and print them
     */
    public String sayCount(String num) {
        if (num == null || num.length() == 0)
            return null;

        StringBuilder builder = new StringBuilder();
        int currentDigit;
        char[] chars = num.toCharArray();
        currentDigit = Character.digit(chars[0], 10);
        int currentNumCount = 1;
        for (int i = 1; i < chars.length; i++) {
            int c = Character.digit(chars[i], 10);
            if (c == currentDigit) {
                currentNumCount++;
            } else {
                builder.append(currentNumCount).append(currentDigit);
                currentDigit = c;
                currentNumCount = 1;
            }
        }

        builder.append(currentNumCount).append(currentDigit);
        return builder.toString();
    }
}
