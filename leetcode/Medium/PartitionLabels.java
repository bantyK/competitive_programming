package solutions.medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/partition-labels/

public class PartitionLabels {
    public static void main(String[] args) {
        PartitionLabels obj = new PartitionLabels();
        List<Integer> integers = obj.partitionLabels("ababcbacadefegdehijhklij");

        System.out.println(integers);
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();

        int[] lastIndices = new int[26];

        for (int i = 0; i < S.length(); i++) {
            lastIndices[S.charAt(i) - 'a'] = i;
        }

        int i = 0;
        while (i < S.length()) {
            int end = lastIndices[S.charAt(i) - 'a'];

            int j = i;

            while (j != end) {
                end = Math.max(end, lastIndices[S.charAt(j) - 'a']);
                j++;
            }
            result.add(j - i + 1);
            i = j + 1;
        }

        return result;
    }
}
