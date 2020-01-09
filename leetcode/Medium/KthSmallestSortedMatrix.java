import input.TwoDArrayReader;

import java.io.IOException;
import java.util.*;

// 378 https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class KthSmallestSortedMatrix {
    public static void main(String[] args) throws IOException {
        KthSmallestSortedMatrix obj = new KthSmallestSortedMatrix();
        final int[][] mat = new TwoDArrayReader().get2DArray();
        final int k = 2;

        System.out.println(obj.kthSmallest(mat, k));
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> b - a);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (minHeap.size() < k)
                    minHeap.add(matrix[i][j]);
                else {
                    if (matrix[i][j] < minHeap.peek()) {
                        minHeap.poll();
                        minHeap.add(matrix[i][j]);
                    }
                }
            }
        }

        return minHeap.poll();
    }
}
