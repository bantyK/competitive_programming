// 839 https://leetcode.com/problems/similar-string-groups/
public class SimilarStringGroups {
    public static void main(String[] args) {
        SimilarStringGroups obj = new SimilarStringGroups();
        System.out.println(obj.numSimilarGroups2(new String[] { "tars", "rats", "arts", "star" }));
    }

    // Solution using DFS
    public int numSimilarGroups2(String[] words) {
        if (words.length < 2)
            return words.length;
        int res = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i] != null) {
                String startWord = words[i];
                words[i] = null;
                dfs(words, startWord);
                res++;
            }
        }
        return res;
    }

    private void dfs(String[] words, String startWord) {
        for (int i = 0; i < words.length; i++) {
            if (words[i] == null) {
                continue;
            }

            if (areSimilar(startWord, words[i])) {
                // words[i] and startWord are similar, we will group them together
                String temp = words[i];
                words[i] = null;
                dfs(words, temp);
            }
        }
    }

    // Solution using Union Find
    public int numSimilarGroups(String[] A) {
        DisjoinSet ds = new DisjoinSet(A.length);

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (areSimilar(A[i], A[j])) {
                    ds.union(i, j);
                }
            }
        }
        return ds.getUniqueSets();
    }

    private boolean areSimilar(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class DisjoinSet {
        int sets;
        int[] parents;
        int[] rank;

        public DisjoinSet(int n) {
            this.sets = n;
            this.parents = new int[n];
            this.rank = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int i) {
            if (parents[i] == i)
                return i;
            return parents[i] = find(parents[i]);
        }

        public void union(int i, int j) {
            int parentI = find(i);
            int parentJ = find(j);

            if (parentI == parentJ)
                return;
            if (rank[i] >= rank[j]) {
                parents[parentJ] = parentI;
                rank[parentI] += rank[parentJ];
            } else {
                parents[parentI] = parentJ;
                rank[parentJ] += rank[parentI];
            }
            this.sets--;
        }

        public int getUniqueSets() {
            return this.sets;
        }
    }
}