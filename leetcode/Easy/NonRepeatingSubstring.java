package com.company.leet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class NonRepeatingSubstring {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("string.in"));
        NonRepeatingSubstring obj = new NonRepeatingSubstring();
        String str = bufferedReader.readLine();
        while (str != null) {
            String inputStr = str.split("-")[0];
            int length = obj.longestSubsequence(inputStr);
//            System.out.println(length);
            System.out.println(String.valueOf(length).equalsIgnoreCase(str.split("-")[1]));
            str = bufferedReader.readLine();
        }
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int nonRepeatingSequenceStart = 0;
        Map<Character, Integer> indexMap = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (indexMap.containsKey(key)) {
                int prevKey = indexMap.get(key);
                if (prevKey >= nonRepeatingSequenceStart) {
                    indexMap.remove(key);
                    indexMap.put(key, i);
                    nonRepeatingSequenceStart = prevKey + 1;
                } else {
                    maxLen = addIntoMap(maxLen, nonRepeatingSequenceStart, indexMap, i, key);
                }
            } else {
                maxLen = addIntoMap(maxLen, nonRepeatingSequenceStart, indexMap, i, key);
            }
        }
        return maxLen;
    }

    public int longestSubsequence(String s) {
        Map<Character, Integer> indexMap = new LinkedHashMap<>();
        int nonRepeatingSequenceStart = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (indexMap.containsKey(key)) {
                int prevKey = indexMap.get(key);
                if (prevKey >= nonRepeatingSequenceStart) {
                    indexMap.remove(key);
                    indexMap.put(key, i);
                    nonRepeatingSequenceStart = prevKey + 1;
                }
            } else {
                maxLen = addIntoMap(maxLen, nonRepeatingSequenceStart, indexMap, i, key);
            }
        }
        return maxLen;
    }

    private int addIntoMap(int maxLen, int nonRepeatingSequenceStart, Map<Character, Integer> indexMap, int i, char key) {
        int currentLen;
        indexMap.put(key, i);
        currentLen = i - nonRepeatingSequenceStart + 1;
        maxLen = Integer.max(currentLen, maxLen);
        return maxLen;
    }
}
