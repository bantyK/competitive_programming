package com.company.leet;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/occurrences-after-bigram/
public class Bigram {


    public static void main(String[] args) {
        Bigram obj = new Bigram();
        String text = "jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd";
        String first = "kcyxdfnoa";
        String second = "jkypmsxd";
        String[] occurrences = obj.findOcurrences(text, first, second);
        obj.printArray(occurrences, 0);
    }

    private void printArray(String[] arr, int index) {
        if (arr != null && arr.length != 0 && index < arr.length) {
            System.out.println(arr[index]);
            printArray(arr, index + 1);
        }
    }

    public String[] findOcurrences(String text, String first, String second) {
        String[] arr = text.split(" ");
        List<String> result = new ArrayList<>();
        int arrLen = arr.length;
        for (int i = 0; i < arrLen; i++) {
            if (i < arrLen - 2 && arr[i].equalsIgnoreCase(first)) {
                if (arr[i + 1].equalsIgnoreCase(second)) {
                    result.add(arr[i + 2]);
                }
            }
        }

        String[] resultArray = new String[result.size()];
        return result.toArray(resultArray);
    }
}
