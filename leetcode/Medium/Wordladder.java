import java.util.*;

//127 https://leetcode.com/problems/word-ladder/
public class Wordladder {
    public static void main(String[] args) {
        Wordladder obj = new Wordladder();

        final int res = obj.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(res);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int t = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                String curr = q.poll();
                if (curr.equals(endWord)) {
                    return t + 1;
                }

                for (int j = 0; j < curr.length(); j++) {
                    char[] arr = curr.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        arr[j] = ch;
                        String newWord = new String(arr);
                        if (wordSet.contains(newWord) && !newWord.equals(curr)) {
                            q.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                }
            }
            ++t;
        }

        return 0;
    }
}
