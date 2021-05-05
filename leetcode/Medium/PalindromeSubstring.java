import input.TwoDArrayReader;

import java.util.*;

// 1177 https://leetcode.com/problems/can-make-palindrome-from-substring/
public class PalindromeSubstring {
    public static void main(String[] args) {
        PalindromeSubstring obj = new PalindromeSubstring();
        System.out.println(obj.canMakePaliQueries("abcda", TwoDArrayReader.get2DArray()));
    }

    public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
        Map<Integer, int[]> prefixCount = new HashMap<>();
        prefixCount.put(-1, new int[26]);

        int[] count = new int[26];
        count[s.charAt(0) - 'a'] = 1;
        prefixCount.put(0, count);

        for (int i = 1; i < s.length(); i++) {
            int[] prev = prefixCount.get(i - 1).clone();
            prev[s.charAt(i) - 'a'] += 1;
            prefixCount.put(i, prev);
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            int k = query[2];
            k *= 2;
            int[] arr1 = prefixCount.get(right);
            int[] arr2 = prefixCount.get(left - 1);
            int[] diff = new int[26];
            for (int i = 0; i < 26; i++) {
                diff[i] = arr1[i] - arr2[i];
            }
            int oddCount = 0;
            for (int i = 0; i < 26; i++) {
                if (diff[i] % 2 == 1) {
                    if (k == 0) oddCount++;
                    else {
                        k--;
                    }
                }
            }
            res.add(oddCount <= 1);
        }
        return res;
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] count = new int[n + 1][26];

        for (int i = 0; i < n; i++) {
            System.arraycopy(count[i], 0, count[i + 1], 0, 26);
            count[i + 1][s.charAt(i) - 'a'] += 1;
        }

        List<Boolean> res = new ArrayList<>();

        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            int k = query[2];

            int oddCount = 0;
            for (int i = 0; i < 26; i++) {
                if ((count[right + 1][i] - count[left][i]) % 2 == 1) {
                    oddCount += 1;
                }
            }

            res.add((oddCount / 2) <= k);
        }
        return res;
    }

}