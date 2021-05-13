import java.util.Arrays;

// 1564 https://leetcode.com/problems/put-boxes-into-the-warehouse-i/
public class BoxesAndWarehouseI {

    public static void main(String[] args) {
        BoxesAndWarehouseI obj = new BoxesAndWarehouseI();
        System.out.println(obj.maxBoxesInWarehouse(new int[]{1, 2, 3}, new int[]{2, 1}));
    }

    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        Arrays.sort(boxes);
        int warehouseIdx = 0;
        int count = 0;

        for (int b = boxes.length - 1; b >= 0 && warehouseIdx < warehouse.length; b--) {
            if (boxes[b] <= warehouse[warehouseIdx]) {
                count++;
                warehouseIdx++;
            }
        }
        return count;
    }

    // solution using min array
    public int maxBoxesInWarehouseMinArray(int[] boxes, int[] warehouse) {
        int numWarehouses = warehouse.length;
        int[] dp = new int[numWarehouses];
        dp[0] = warehouse[0];
        for (int i = 1; i < numWarehouses; i++) {
            dp[i] = Math.min(dp[i - 1], warehouse[i]);
        }

        Arrays.sort(boxes);
        int count = 0;
        int boxIndex = 0;

        for (int i = numWarehouses - 1; i >= 0; i--) {
            if (boxIndex < boxes.length && boxes[boxIndex] <= dp[i]) {
                count++;
                boxIndex++;
            }
        }

        return count;
    }

}