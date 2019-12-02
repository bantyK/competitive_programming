import java.util.*;

// 779 https://leetcode.com/problems/k-th-symbol-in-grammar/
public class KthSymbol {
    public static void main(String[] args) {
        KthSymbol obj = new KthSymbol();
        System.out.println(obj.kthGrammer(4, 5));
    }

    public int kthGrammer(int N, int K) {
        long length = (long) Math.pow(2, N - 1);
        int flips = 0;

        while (length > 2) {
            if (K > length / 2) {
                K = K - (int) (length / 2);
                flips++;
            }

            length = length / 2;
        }

        K--;

        return (flips % 2 == 0) ? K : reverse(K);
    }

    private int reverse(int val) {
        return val == 0 ? 1 : 0;
    }
}
