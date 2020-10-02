import java.util.*;

//1048 https://leetcode.com/problems/longest-string-chain/
public class LongestStringChain {
    public static void main(String[] args) {
        LongestStringChain obj = new LongestStringChain();

        System.out.println(obj.longestStrChain(new String[]{"a","ba","bca","bcad","bcade","bcadefg","bcadef"}) == 7);
    }


    public int longestStrChainDFS(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Map<String, Integer> memo = new HashMap<>();
        int res = 1;
        for(String word: words) {
            res = Math.max(res, dfs(word, set, memo));
        }
        return res;
    }

    private int dfs(String word, Set<String> set, Map<String, Integer> memo) {
        if(memo.containsKey(word)) return memo.get(word);
        int ans = 1;
        for(int i = 0; i < word.length(); i++) {
            String nextWord = word.substring(0, i) + word.substring(i + 1);
            if(set.contains(nextWord)) {
                ans = Math.max(ans, 1 + dfs(nextWord, set, memo));
            }
        }
        return ans;
    }


    //Optimised solution
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int res = 0;
        for(String word : words) {
            int best = 0;
            for(int i = 0; i < word.length(); i++) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                System.out.println(prev);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(best, res);
        }

        return res;

    }


    // My previous accepted solution
    public int longestStrChain2(String[] words) {
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        int[] dp = new int[words.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(predecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    res = Math.max(res, dp[i]);
                }
            }
        }

        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(dp));
        return res;
    }

    // checks if word1 is a predecessor of word2
    private boolean predecessor(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if(len1 - len2 != 1) return false;
        int diff = 0; // this represent the number of differences in the characters of word1 and word2
        // the character difference between word1 and word2 should be equal to 1
        Map<Character, Integer> charCountMap = new HashMap<>();
        for(char c: word1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for(char c : word2.toCharArray()) {
            if(charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) - 1);
                if(charCountMap.get(c) == 0) charCountMap.remove(c);
            }
        }

        if(charCountMap.size() >= 2) return false;
        for(char key : charCountMap.keySet()) {
            if(charCountMap.get(key) > 1) return false;
        }

        return true;
    }
}
