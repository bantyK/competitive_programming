import java.util.*;

// 363 https://leetcode.com/problems/palindrome-pairs/
public class PalindromePairs {

    public static void main(String[] args) {
        String[] words = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
        System.out.println(new PalindromePairs().palindromePairs(words));
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        // Handling the special case of blank string
        // If S1 = "" then for any string S2 which is a palindrome
        // S1 + S2 is a palindrome and S2 + S1 is a palindrome

        if (map.containsKey("")) {
            int blankIndex = map.get("");
            for (int i = 0; i < words.length; i++) {
                if (i == blankIndex) continue;
                if (isPalindrome(words[i])) {
                    res.add(Arrays.asList(i, blankIndex)); // S1 + S2
                    res.add(Arrays.asList(blankIndex, i)); // S2 + S1
                }
            }
        }

        // If S2 is the reverse of S1 then S1 + S2 and S2 + S1 will be palindrome
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reversed = reverse(word);
            if (map.containsKey(reversed) && map.get(reversed) != i) {
                res.add(Arrays.asList(map.get(reversed), i));
            }
        }

        // If S1[0: cut] is a palindrome, and S1[cut + 1] == reverse(s2) then S2 + S1 will be palindrome
        // If S1[cut + 1] is a palindrome, and S1[0: cut] == reverse(s2) then S1 + S2 will be palindrome

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            for (int cut = 1; cut < word.length(); cut++) {
                String firstPart = word.substring(0, cut);
                String secondPart = word.substring(cut);

                if (isPalindrome(firstPart)) {
                    String reversedSecondPart = reverse(secondPart);
                    if (map.containsKey(reversedSecondPart)) {
                        int index = map.get(reversedSecondPart);
                        res.add(Arrays.asList(index, i));
                    }
                }

                if (isPalindrome(secondPart)) {
                    String reversedFirstPart = reverse(firstPart);
                    if (map.containsKey(reversedFirstPart)) {
                        int index = map.get(reversedFirstPart);
                        res.add(Arrays.asList(i, index));
                    }
                }

            }
        }

        return res;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}