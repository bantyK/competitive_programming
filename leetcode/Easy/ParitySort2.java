package solutions;

//https://leetcode.com/problems/sort-array-by-parity-ii/
public class ParitySort2 {

    public static void main(String[] args) {
        int[] a = {3, 0, 4, 0, 2, 1, 3, 1, 3, 4};
        new ParitySort2().sortArrayByParityII(a);
        print(a);
    }

    private static void print(int[] a) {
        for (int num : a) {
            System.out.print(num + "  ");
        }
    }

    public int[] sortArrayByParityII(int[] A) {
        int evenIdx = 0;
        int oddIdx = A.length - 1;

        while (evenIdx < A.length && oddIdx > 0) {

            while (evenIdx < A.length && A[evenIdx] % 2 == 0) {
                evenIdx += 2;
            }

            while (oddIdx > 0 && A[oddIdx] % 2 == 1) {
                oddIdx -= 2;
            }
            System.out.println("even : " + evenIdx + " odd : " + oddIdx);
            if (evenIdx < A.length && oddIdx > 0) {
                int temp = A[evenIdx];
                A[evenIdx] = A[oddIdx];
                A[oddIdx] = temp;
            }
        }

        return A;
    }


}
