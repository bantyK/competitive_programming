import java.util.*;

// 650 https://leetcode.com/problems/2-keys-keyboard/
public class KeyboardKeys {
    public static void main(String[] args) {
        KeyboardKeys obj = new KeyboardKeys();
        System.out.println(obj.minSteps(100));
    }

    public int minSteps(int n) {
        Map<String, Integer> cache = new HashMap<>();
//        return helper(n, "", "A", 0, cache);
        Integer[][] dp = new Integer[n + 1][n + 1];
        return countSteps(n, 0, 1, 0, dp);
    }

    private int countSteps(int n, int clipBoard, int document, int steps, Integer[][] cache) {
        if (document == n) {
            return steps;
        }

        if (document > n || clipBoard > n) return Integer.MAX_VALUE;
        if (cache[clipBoard][document] != null) return cache[clipBoard][document];

        int copy = Integer.MAX_VALUE;
        int paste = Integer.MAX_VALUE;

        if (document != clipBoard) {
            copy = countSteps(n, document, document, steps + 1, cache);
        }
        // paste -> clipboard will remain same and document's content will be appended with clipboard's content
        if (clipBoard > 0)
            paste = countSteps(n, clipBoard, document + clipBoard, steps + 1, cache);

        cache[clipBoard][document] = Math.min(copy, paste);
        return cache[clipBoard][document];
    }

    private int helper(int n, String clipBoard, String document, int steps, Map<String, Integer> cache) {
        if (document.length() == n) {
            return steps;
        }

        if (document.length() > n || clipBoard.length() > n) {
            return Integer.MAX_VALUE;
        }

        String key = clipBoard + "->" + document;
        if (cache.containsKey(key)) return cache.get(key);

        // copy -> the document will not change, documents content will be in clipboard
        int copy = Integer.MAX_VALUE;
        int paste = Integer.MAX_VALUE;

        if (!document.equals(clipBoard)) {
            copy = helper(n, document, document, steps + 1, cache);
        }
        // paste -> clipboard will remain same and document's content will be appended with clipboard's content
        if (!clipBoard.isEmpty())
            paste = helper(n, clipBoard, document + clipBoard, steps + 1, cache);

        cache.put(key, Math.min(copy, paste));
        return cache.get(key);
    }
}
