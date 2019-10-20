package solutions.util;

import java.util.List;

public class ArrayUtils {

    public static void print2DArray(int[][] arr) {
        for(int row = 0; row <arr.length; row++) {
            for(int col = 0; col <arr.length; col++) {
                System.out.print(arr[row][col] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        for(int i = 0; i <arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printStringArray(String[] arr) {
        for(int i = 0; i <arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void printArrayList(List<Integer> list) {
        for(int i = 0; i <list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println();
    }
}
