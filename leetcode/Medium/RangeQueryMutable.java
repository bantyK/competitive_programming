package segmenttree;

// 307 https://leetcode.com/problems/range-sum-query-mutable/
public class RangeQueryMutable {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(numArray.sumRange(1, 4));
        numArray.update(2, 5);
        System.out.println(numArray.sumRange(1, 4));

    }

    static class NumArray {

        SegmentTreeNode root = null;

        public NumArray(int[] nums) {
            root = buildSegmentTree(nums, 0, nums.length - 1);
        }

        private SegmentTreeNode buildSegmentTree(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            } else {
                SegmentTreeNode root = new SegmentTreeNode(start, end);
                if (start == end) {
                    // base case, leaf nodes
                    // leaf nodes will have same start and end values
                    root.sum = nums[start];
                } else {
                    int mid = start + (end - start) / 2;
                    root.left = buildSegmentTree(nums, start, mid);
                    root.right = buildSegmentTree(nums, mid + 1, end);
                    root.sum = root.left.sum + root.right.sum;
                }
                return root;
            }

        }


        public void update(int i, int val) {
            update(root, i, val);
        }

        private void update(SegmentTreeNode root, int pos, int val) {
            if (root.start == root.end) {
                // base case, leaf node
                root.sum = val;
            } else {
                int mid = root.start + (root.end - root.start) / 2;

                if (pos <= mid) {
                    update(root.left, pos, val);
                } else if (pos >= mid + 1) {
                    update(root.right, pos, val);
                }

                // root's value needs to be updated in any case
                root.sum = root.left.sum + root.right.sum;
            }
        }

        public int sumRange(int i, int j) {
            return sumRange(root, i, j);
        }

        public int sumRange(SegmentTreeNode root, int start, int end) {
            if (root.start == start && root.end == end) {
                return root.sum;
            }

            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) {
                // If this condition is true, it means the entire query segment lies on the left child of the current root
                // hence query only on the left child
                return sumRange(root.left, start, end);
            } else if (start >= mid + 1) {
                // If this condition is true, it means the entire segment lies on the right child of the current root
                // hence query only on right child
                return sumRange(root.right, start, end);
            } else {
                // the query segment lies both in the left hand side and on the right hand side.
                // query both the sides
                return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
            }
        }

        class SegmentTreeNode {
            int start;
            int end;
            SegmentTreeNode left;
            SegmentTreeNode right;
            int sum;

            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                left = null;
                right = null;
                sum = 0;
            }
        }
    }
}