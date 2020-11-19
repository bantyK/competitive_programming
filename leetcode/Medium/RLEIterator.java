//900 https://leetcode.com/problems/rle-iterator/
public class RLEIterator {

    int index;
    int[] A;

    public RLEIterator(int[] A) {
        this.A = A;
        index = 0;
    }

    public static void main(String[] args) {

        RLEIterator iterator = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});

        System.out.println(iterator.next(2));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(2));

    }

    public int next(int n) {
        int num = -1;

        while (index < A.length && n > A[index]) {
            n = n - A[index];
            index += 2;
        }
        if (index < A.length - 1) {
            A[index] -= Math.abs(n);
            num = A[index + 1];
        }
        return num;
    }

}