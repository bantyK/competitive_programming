// 839 https://leetcode.com/problems/similar-string-groups/
public class SimilarStringWords {
    public static void main(String[] args) {
        SimilarStringWords obj = new SimilarStringWords();
        String[] strings = new String[]{"kccomwcgcs", "socgcmcwkc", "sgckwcmcoc", "coswcmcgkc", "cowkccmsgc", "cosgmccwkc", "sgmkwcccoc", "coswmccgkc", "kowcccmsgc", "kgcomwcccs"};

        final int res = obj.numSimilarGroups(strings);
        System.out.println(res);
    }

    public int numSimilarGroups(String[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        DisjointSet set = new DisjointSet(arr.length);

        for(int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if(similarWords(arr[i], arr[j])) {
                    set.union(i, j);
                }
            }
        }

        return set.sets;
    }

    public boolean similarWords(String a, String b) {
        int diff = 0;

        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i) && ++diff > 2) {
                return false;
            }
        }

        return true;
    }

    class DisjointSet {
        int[] parent;
        int[] rank;
        int sets;

        public DisjointSet(int num) {
            parent = new int[num];
            for (int i = 0; i < num; i++) {
                parent[i] = i;
            }
            rank = new int[num];
            this.sets = num;
        }

        public int find(int index) {
            if (parent[index] == index) return index;
            int res = find(parent[index]);
            parent[index] = res;
            return res;
        }

        public void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi == pj) return;

            if (rank[pi] > rank[pj]) {
                parent[pj] = pi;
            } else {
                parent[pi] = pj;
                if (rank[pi] == rank[pj]) {
                    rank[pj]++;
                }
            }
            this.sets--;
        }
    }
}
