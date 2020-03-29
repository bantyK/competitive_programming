// 1381 https://leetcode.com/problems/design-a-stack-with-increment-operation/

public class CustomStack {
    private int top;
    private int[] values;
    private int[] inc;
    private int size;

    public CustomStack(int maxSize) {
        this.size = maxSize;
        inc = new int[size];
        values = new int[size];
        top = 0;
    }

    public void push(int val) {
        if (top < size) {
            values[top++] = val;
        }
    }

    public void increment(int k, int val) {
        int i = Math.min(top, k) - 1;
        if(i >= 0) {
            inc[i] += val;
        }
    }

    public int pop() {
        if (top == 0) return -1;
        int i = top - 1;

        if (i > 0) {
            inc[i - 1] += inc[i];
        }

        int res = values[i] + inc[i];
        inc[i] = 0;
        top--;
        return res;
    }
}
