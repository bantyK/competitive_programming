import java.util.*;

//433 https://leetcode.com/problems/minimum-genetic-mutation/
public class MinGeneMutation {
    public static void main(String[] args) {
        MinGeneMutation obj = new MinGeneMutation();
        String start = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println(
                obj.minMutation(start, end, bank)
        );
    }

    public int minMutation(String start, String end, String[] bank) {
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));
        if (!geneBank.contains(end)) {
            return -1;
        }

        Queue<String> q = new LinkedList<>();
        q.offer(start);
        int mutationCount = 0;
        char[] transformations = new char[]{'A', 'C', 'G', 'T'};
        while (!q.isEmpty()) {
            for (int x = q.size(); x > 0; x--) {
                String current = q.poll();
                if (current.equals(end)) {
                    return mutationCount;
                }

                for (int i = 0; i < current.length(); i++) {
                    char[] arr = current.toCharArray();
                    for (char t : transformations) {
                        arr[i] = t;
                        String newGene = new String(arr);
                        if (geneBank.contains(newGene) && !newGene.equals(current)) {
                            q.offer(newGene);
                            geneBank.remove(newGene);
                        }
                    }
                }
            }

            ++mutationCount;
        }

        return -1;
    }
}
