//1580 https://leetcode.com/problems/put-boxes-into-the-warehouse-ii/
public class BoxesAndWarehouses2 {

    public static void main(String[] args) {
        System.out.println(new BoxesAndWarehouses2().maxBoxesInWarehouse(new int[]{1, 2, 2, 3, 4}, new int[]{3, 4, 1, 2}));
    }

    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        int w = warehouse.length;
        java.util.Arrays.sort(boxes);

        int leftEnd = 0;
        int min = warehouse[0];

        for (int i = 0; i < w; i++) {
            if (warehouse[i] < min) {
                min = warehouse[i];
                leftEnd = i;
            }
        }
        int count = 0;
        int warehouseIndex = 0;
        for (int i = boxes.length - 1; i >= 0 && warehouseIndex <= leftEnd; i--) {
            if (boxes[i] <= warehouse[warehouseIndex]) {
                warehouseIndex++;
                count++;
                boxes[i] = Integer.MAX_VALUE;
            }
        }
        warehouseIndex = w - 1;
        for (int i = boxes.length - 1; i >= 0 && warehouseIndex > leftEnd; i--) {
            if (boxes[i] <= warehouse[warehouseIndex]) {
                warehouseIndex--;
                count++;
            }
        }

        return count;
    }
}