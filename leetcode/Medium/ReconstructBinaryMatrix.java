package solutions.medium;

import java.util.*;

// https://leetcode.com/problems/reconstruct-a-2-row-binary-matrix/
public class ReconstructBinaryMatrix {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> upperRow = new ArrayList<>();

        int currentSum = 0;

        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] >= 1 && currentSum < upper) {
                upperRow.add(1);
                currentSum += 1;
            } else {
                upperRow.add(0);
            }
        }

        res.add(upperRow);

        List<Integer> lowerRow = new ArrayList<>();

        int currentLower = 0;

        for (int i = 0; i < colsum.length; i++) {
            int currentColSum = colsum[i];
            int e = currentColSum - upperRow.get(i);
            lowerRow.add(e);
            currentLower += e;

            if(currentLower > lower) {
                return new ArrayList<>();
            }

        }

        if (currentLower < lower) {
            return new ArrayList<>();
        }

        res.add(lowerRow);

        return res;
    }
}
