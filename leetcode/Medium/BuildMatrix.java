import java.util.*;

// 1605 https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/
public class BuildMatrix {
    public static void main(String[] args) {
        BuildMatrix obj = new BuildMatrix();

        int[][] mat = obj.restoreMatrix(new int[]{5,7,10}, new int[]{8,6,8});
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));            
        }
    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rows = rowSum.length;
        int cols = colSum.length;

        int[][] mat = new int[rows][cols];
        Set<Integer> seenRowItem = new HashSet<>();
        Set<Integer> seenColumnItem = new HashSet<>();

        while(seenRowItem.size() != rows && seenColumnItem.size() != cols) {
            int minRowIndex = minIndex(rowSum, seenRowItem);
            int minColIndex = minIndex(colSum, seenColumnItem);

            if(rowSum[minRowIndex] < colSum[minColIndex]) {
                mat[minRowIndex][minColIndex] = rowSum[minRowIndex];
                colSum[minColIndex] -= rowSum[minRowIndex];
                seenRowItem.add(minRowIndex);
            } else {
                mat[minRowIndex][minColIndex] = colSum[minColIndex];
                rowSum[minRowIndex] -= colSum[minColIndex];
                seenColumnItem.add(minColIndex);
            }
        }
        return mat;
    }

    /**
     * returns the index of the minimum item of the array, given the index is not present in the hashmap
     * @param arr
     * @param set
     * @return
     */
    private int minIndex(int[] arr, Set<Integer> set) {
        int minIndex = 0;
        int minItem = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++)  {
            if(arr[i] < minItem && !set.contains(i)){
                minItem = arr[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

}