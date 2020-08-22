import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        WordBreak obj = new WordBreak();

        System.out.println(obj.wordBreakBFS("leetcode", Arrays.asList("leet", "code")));
        System.out.println(obj.wordBreakBFS("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(obj.wordBreakBFS("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(obj.wordBreakBFS("aaaaaaa", Arrays.asList("aaaa", "aaa")));
    }

    //Top Down
    fun wordBreak(s: String, words: List<String>): Boolean {
    	val cache = HashMap<Int, Boolean>()
    	return helper(s, words, 0, cache)
	}

	private fun helper(s: String, words: List<String>, strIndex: Int, cache: HashMap<Int, Boolean>): Boolean {
	    if (strIndex >= s.length) return true

	    cache[strIndex]?.let {
	        return it
	    }

	    for (word in words) {
	        val currentWordLen = word.length
	        val endIndex = strIndex + currentWordLen

	        if (endIndex > s.length) continue

	        if (s.substring(strIndex, endIndex) == word) {
	            val result = helper(s, words, endIndex, cache)
	            if (result) {
	                cache[strIndex] = true
	                return true
	            }
	        }
	    }
	    cache[strIndex] = false
	    return false
	}

	// Bottom up
	fun wordBreakBottomUp(s: String, words: List<String>): Boolean {
	    // dp[i] represent the question can be form the word ending at i using the words given in dictionary
	    val dp = BooleanArray(s.length + 1)
	    dp[0] = true
	    val set = HashSet<String>(words)
	    for (i in 1..s.length) {
	        for (j in 0 until i) {
	            val word = s.substring(j, i)
	            if (dp[j] && set.contains(word)) {
	                dp[i] = true
	            }
	        }
	    }
	    println(dp.contentToString())
	    return dp[s.length]
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
