//318 https://leetcode.com/problems/maximum-product-of-word-lengths/

import java.util.*;
public class MaximumProductWordLength {
    public static void main(String[] args) {
        String[] words = new String[]{"abcw","baz","foo","bar","xtfn","abcdef"};
        System.out.println(new MaximumProductWordLength().maxProduct(words));
    }

    public int maxProduct(String[] words) {
        int maxProduct = 0;
        for(int i = 0; i < words.length; i++) {
            for(int j = i + 1; j < words.length; j++) {
                System.out.println(words[i]);
                System.out.println(words[j]);
                boolean haveCommonChars = haveCommonChars(words[i],words[j]);
                System.out.println(haveCommonChars);
                if(haveCommonChars) {
                    continue;
                } else {
                    maxProduct = Math.max(maxProduct, (words[i].length() * words[j].length()));
                    System.out.println(maxProduct);
                }
            }
        }
        return maxProduct;
    }

    private boolean haveCommonChars(String word1, String word2) {
        Set<Character> char1 = new HashSet<>();
    
        for(char c : word1.toCharArray()) {
            char1.add(c);
        }

        for(char c : word2.toCharArray()) {
            if(char1.contains(c)) return false;
        }

        return true;
    }
}