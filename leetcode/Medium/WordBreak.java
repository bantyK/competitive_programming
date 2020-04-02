import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak obj = new WordBreak();

        System.out.println(obj.wordBreakBFS("leetcode", Arrays.asList("leet", "code")));
        System.out.println(obj.wordBreakBFS("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(obj.wordBreakBFS("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(obj.wordBreakBFS("aaaaaaa", Arrays.asList("aaaa", "aaa")));
    }

    private boolean wordBreak(String s, List<String> words) {
        return wordBreakHelper(s, words, 0);
    }

    private boolean wordBreakHelper(String s, List<String> words, int startIndex) {
        if (s.length() == startIndex) return true;

        for (String word : words) {
            int len = word.length();
            int endIndex = startIndex + len;

            if (endIndex > s.length()) continue;

            if (s.substring(startIndex, endIndex).equals(word)) {
                if (wordBreakHelper(s, words, startIndex + len)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean wordBreak2(String s, List<String> words) {
        boolean[] canSegment = new boolean[s.length() + 1];
        canSegment[0] = true;

        for (int i = 0; i < s.length(); i++) {
            if (!canSegment[i]) continue;

            for (String word : words) {
                int len = word.length();
                int endIndex = i + len;

                if (endIndex > s.length()) continue;

                if (canSegment[endIndex]) continue;

                if (s.substring(i, endIndex).equals(word)) {
                    canSegment[endIndex] = true;
                }
            }
        }
        return canSegment[s.length()];
    }

    // BFS solution
    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            String candidate = queue.poll();
            if (wordDict.contains(candidate)) return true;
            for (int i = 0; i < candidate.length(); i++) {
                String chop = candidate.substring(0, i);
                String remaining = candidate.substring(i);

                if (!visited.contains(remaining) && wordDict.contains(chop)) {
                    remaining = candidate.substring(i);
                    queue.offer(remaining);
                    visited.add(remaining);
                }
            }
        }
        return false;
    }


}
