package segmenttree;

import java.util.Arrays;

//307 https://leetcode.com/problems/range-sum-query-mutable/
public class RangeQueryMutableArrayImpl {

    public static void main(String[] args) {
        NumArray obj = new NumArray(new int[]{7, 2, 7, 2, 0});
        System.out.println(Arrays.toString(obj.segmentTree));
        obj.update(3, 8);
        System.out.println(Arrays.toString(obj.segmentTree));
    }

    static class NumArray {

        int[] segmentTree;
        int n;
        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
            if (n > 0) {
                int h = (int) Math.ceil(Math.log(n) / Math.log(2));
                int size = 2 * (int) Math.pow(2, h) - 1;

                segmentTree = new int[size];

                constructSegmentTree(0, n - 1, 0);
            }
        }

        private void constructSegmentTree(int start, int end, int si) {
            if (start > end) {
                return;
            }
            if (start == end) {
                segmentTree[si] = nums[start];
                return;
            }

            int mid = getMid(start, end);
            constructSegmentTree(start, mid, 2 * si + 1);
            constructSegmentTree(mid + 1, end, 2 * si + 2);
            segmentTree[si] = segmentTree[2 * si + 1] + segmentTree[2 * si + 2];
        }

        private int getMid(int start, int end) {
            return start + (end - start) / 2;
        }

        public void update(int i, int val) {
            update(i, val, 0, n - 1, 0);
        }

        // si -> segment tree index
        private void update(int pos, int val, int ss, int se, int si) {
            if (pos < ss || pos > se) {
                return;
            }
            if (ss == se) {
                segmentTree[si] = val;
                return;
            }


            int mid = getMid(ss, se);

            update(pos, val, ss, mid, 2 * si + 1);
            update(pos, val, mid + 1, se, 2 * si + 2);

            segmentTree[si] = segmentTree[2 * si + 1] + segmentTree[2 * si + 2];
        }

        public int sumRange(int i, int j) {
            return sumRange(0, n - 1, i, j, 0);
        }

        /**
         * ss -> segment start
         * se -> segment end
         * qs -> query start
         * qe -> query end
         * si -> segment tree index
         */
        private int sumRange(int ss, int se, int qs, int qe, int si) {
            if (ss >= qs && se <= qe) {
                // segment completely inside the query range
                return segmentTree[si];
            }

            if (qe < ss || qs > se) {
                // query range outside the segment
                return 0;
            }

            // partial overlap, check both side
            int mid = getMid(ss, se);
            return sumRange(ss, mid, qs, qe, 2 * si + 1) + sumRange(mid + 1, se, qs, qe, 2 * si + 2);
        }
    }
}