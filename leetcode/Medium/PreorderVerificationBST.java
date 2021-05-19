import java.util.Stack;

// 255 https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
public class PreorderVerificationBST {
    public static void main(String[] args) {
        PreorderVerificationBST obj = new PreorderVerificationBST();
        System.out.println(obj.verifyPreorder(new int[]{2, 3, 1}));
//        System.out.println(obj.verifyPreorder(new int[]{5,2,1,3,6}));
//        System.out.println(obj.verifyPreorder(new int[]{5,2,6,1,3}));
    }


    public boolean verifyPreorder(int[] preorder) {
//        return verify(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);

        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int val : preorder) {
            while (!stack.isEmpty() && val > stack.peek()) {
                lowerBound = stack.pop();
            }

            if (val < lowerBound) return false;

            stack.push(val);
        }

        return true;
    }

    public boolean verify(int[] preorder, int left, int right, int min, int max) {
        if (left > right) return true;
        if (left == right) {
            // single element is a valid BST (leaf node)
            return preorder[left] > min && preorder[left] < max;
        }

        int rootVal = preorder[left];

        if (rootVal < min || rootVal > max) return false;

        int leftEnd = left + 1;
        while (leftEnd < preorder.length && preorder[leftEnd] < rootVal) {
            leftEnd++;
        }

        boolean isLeftBST = verify(preorder, left + 1, leftEnd - 1, min, rootVal);
        boolean isRightBST = verify(preorder, leftEnd, right, rootVal, max);

        return isLeftBST && isRightBST;

    }
}