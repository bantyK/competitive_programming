//1649 https://leetcode.com/problems/create-sorted-array-through-instructions/
public class ArrayThroughInstructions {
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 3, 3, 3, 2, 4, 2, 1, 2 };
        ArrayThroughInstructions obj = new ArrayThroughInstructions();
        System.out.println(obj.createSortedArray(nums));
    }

    long[] fenwickTree;

    public int createSortedArray(int[] nums) {
        // The idea is count the number of elements which inserted into the array and
        // which are less than the current number.

        // We also have the count of total numbers inserted into the array, which is
        // nothing but the index of the for loop.

        // From the above two information, we can calculate the count of numbers which
        // are inserted into the array and which are greater than the current number
        // this value is equal total numbers inserted - count of all number less than
        // equal to current num

        // prefix sum is actually giving us the total count of the range of numbers
        long res = 0;
        int n = nums.length;
        int MOD = 1000000007;
        fenwickTree = new long[100001];

        // inserted is the count of numbers inserted into the sorted array.
        for (int inserted = 0; inserted < n; inserted++) {
            int currentNum = nums[inserted];

            // sum of all numbers which are less than current num
            long lesserNumberCount = rangeSum(currentNum - 1);

            // sum of all the numbers which are less than or equal to the current number
            long greaterOrEqualNumCount = rangeSum(currentNum);

            // (total inserted - greaterOrEqualNumCount) is the count of all numbers which
            // are strictly greater than the current number

            // here i basically means total inserted numbers
            res += Math.min(lesserNumberCount, inserted - greaterOrEqualNumCount);
            res %= MOD;
            update(currentNum);
        }

        return (int) res;
    }

    // Standard range query and range update methods of Fennwick Tree
    private long rangeSum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += fenwickTree[index];
            index -= (index & -index);
        }
        return sum;
    }

    // the value is going to be 1
    private void update(int index) {
        while (index < 100001) {
            fenwickTree[index] += 1;
            index += (index & -index);
        }
    }
}
