// 1381 https://leetcode.com/problems/design-a-stack-with-increment-operation/
public class CustomStack {
    int[] values;
    int maxSize;
    int topIndex;

    public CustomStack(int maxSize) {
        values = new int[maxSize];
        this.maxSize = maxSize;
        this.topIndex = -1;
    }

    public void push(int x) {
        if (topIndex < maxSize - 1) {
            values[++topIndex] = x;
        }
    }

    public int pop() {
        if (topIndex < 0) return -1;

        return values[topIndex--];
    }

    public void increment(int k, int val) {
        int limit = Math.min(k, maxSize);
        for (int i = 0; i < limit; i++) {
            values[i] += val;
        }
    }
}
