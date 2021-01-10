import java.util.*;

// Implementation of a Fenwick tree in Java
public class FenwickTree {
    // this array is the copy of the actual array, we want to keep a copy of the
    // original
    // array because we are going to do a in-place algorithm
    private long[] tree;

    public FenwickTree(int n) {
        tree = new long[n + 1]; // fenwick tree will be 1-index based
    }

    public FenwickTree(long[] values) {
        this.tree = values.clone();

        for (int i = 1; i < values.length; i++) {
            int j = i + lsb(i);
            if (j < tree.length)
                tree[j] += tree[i];
        }

        System.out.println(Arrays.toString(tree));
    }

    /*
     * Returns the value of the least significant bit lsb(108) = lsb(0b1101100) = 2
     * ^ 2 = 4 lsb(104) = lsb(0b1101000) = 2 ^ 3 = 8
     */
    private int lsb(int x) {
        return x & -x;
    }

    private long prefixSum(int i) {
        long sum = 0;
        while (i != 0) {
            sum += tree[i];
            i &= ~lsb(i); // or i -= lsb(i);
        }
        return sum;
    }

    // returns the sum between i and j, both inclusive
    private long rangeQuery(int left, int right) {
        // we need to include the value at left, so subtract till left - 1
        return prefixSum(right) - prefixSum(left - 1);
    }

    // Update the value at index
    private void update(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            // propagate up
            index += lsb(index);
        }
    }

    public static void main(String[] args) {
        FenwickTree obj = new FenwickTree(new long[] { 0, 3, 4, -2, 7, 3, 11, 5, -8, -9, 2, 4, -8 }); // first element
                                                                                                      // does not get
                                                                                                      // used
        long sum7 = obj.prefixSum(7);
        System.out.println(sum7);

        long sum3 = obj.prefixSum(3);
        System.out.println(sum3);

        System.out.println(obj.rangeQuery(3, 7));
    }
}
