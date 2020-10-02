import java.util.*;

//854 https://leetcode.com/problems/k-similar-strings/
public class KSimilarString {
    public static void main(String[] args) {
        KSimilarString obj = new KSimilarString();
        System.out.println(obj.kSimilarity("abc", "bac"));
        System.out.println(obj.kSimilarity("fffeaacbdbdafcfbbafb",
                "abcbdfafffefabdbbafc"));
    }

    public int kSimilarity(String A, String B) {
        if (A.equals(B)) return 0;
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        seen.add(A);
        queue.offer(A);
        int res = 0;

        while (!queue.isEmpty()) {
            res++;
            for (int size = queue.size(); size > 0; size--) {
                String curr = queue.poll();
                int i = 0;
                while (curr.charAt(i) == B.charAt(i)) {
                    // find the first char where the characters differ in A and B
                    i++;
                }

                for (int j = i + 1; j < curr.length(); j++) {
                    if (B.charAt(j) == curr.charAt(j) || curr.charAt(j) != B.charAt(i)) {
                        // If the chars at A and B are same then no need to consider that, OR
                        // if after swapping the ith and jth chars in curr, if the jth char in curr still does not
                        // become equal to ith char in B(remember i and j are positions where the chars differ in the first place), then that swap is also useless.
                        continue;
                    }
                    String next = swap(curr, i, j);
                    if (next.equals(B)) return res;
                    if (seen.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }

        return res;
    }

    private String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();

        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return new String(arr);
    }
}
