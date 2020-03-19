import java.util.*;

//784 https://leetcode.com/problems/letter-case-permutation/
public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation obj = new LetterCasePermutation();
        System.out.println(obj.letterCasePermutation3("abc"));
        System.out.println(obj.letterCasePermutation3("a1b2cd"));
        System.out.println(obj.letterCasePermutation3("abcdEf"));
    }


    // DFS
    public List<String> letterCasePermutation3(String s) {
        char[] chars = s.toCharArray();
        List<String> results = new ArrayList<>();
        recurse(chars, 0, results);
        return results;
    }

    private void recurse(char[] chars, int index, List<String> results) {
        if (index == chars.length) {
            results.add(new String(chars));
            return;
        }

        if (Character.isLetter(chars[index])) {
            chars[index] = Character.toLowerCase(chars[index]);
            recurse(chars, index + 1, results);

            chars[index] = Character.toUpperCase(chars[index]);
            recurse(chars, index + 1, results);
        } else {
            recurse(chars, index + 1, results);
        }
    }

    // BFS
    public List<String> letterCasePermutation(String s) {
        if (s == null || s.length() == 0) return Collections.emptyList();

        Queue<String> queue = new LinkedList<>();
        queue.offer(s);

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) continue;

            int size = queue.size();

            for (int j = 0; j < size; j++) {
                String curr = queue.poll();

                char[] chars = curr.toCharArray();

                chars[i] = Character.toUpperCase(chars[i]);
                queue.offer(new String(chars));

                chars[i] = Character.toLowerCase(chars[i]);
                queue.offer(new String(chars));
            }
        }

        return new LinkedList<>(queue);
    }

    // Brute force
    public List<String> letterCasePermutation2(String S) {
        int numLetters = 0;
        List<String> permutations = new ArrayList<>();
        permutations.add(S);
        for (int i = S.length() - 1; i >= 0; i--) {
            List<String> newPermutations = new ArrayList<>();
            char c = S.charAt(i);
            boolean lower = c >= 97 && c <= 122;
            if (Character.isDigit(c)) continue;
            for (String p : permutations) {
                char newC = lower ? Character.toUpperCase(c) : Character.toLowerCase(c);
                newPermutations.add(p.substring(0, i) + newC + p.substring(i + 1));
            }
            permutations.addAll(newPermutations);
        }
        return permutations;
    }
}
