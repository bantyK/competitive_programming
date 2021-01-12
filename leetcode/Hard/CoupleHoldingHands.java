import java.util.*;

// 765 https://leetcode.com/problems/couples-holding-hands/
public class CoupleHoldingHands {
    public static void main(String[] args) {
        CoupleHoldingHands obj = new CoupleHoldingHands();
    }

    Map<Integer, Integer> indexMap = new HashMap<>();

    public int minSwapsCouples(int[] row) {
        for (int i = 0; i < row.length; i++) {
            indexMap.put(row[i], i);
        }

        int numSwap = 0;

        for (int i = 0; i < row.length - 1; i += 2) {
            int first = row[i];
            int expectedSecond = first + ((row[i] % 2 == 0) ? 1 : -1);
            if (row[i + 1] != expectedSecond) {
                // the number at index i + 1 is not the pair of first
                // the pair is present in map.get(expectedSecond)
                // so we the num at i + 1 needs to be swapped with the expectedIndex
                swap(row, i + 1, indexMap.get(expectedSecond));
                numSwap++;
            }
        }

        return numSwap;
    }

    private void swap(int[] row, int i, int j) {
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;

        // the swapping needs to be done at the map also
        // the swapping has already been done at the array so we simply need to update
        // the same
        // values at the map
        indexMap.put(row[i], i);
        indexMap.put(row[j], j);
    }
}
