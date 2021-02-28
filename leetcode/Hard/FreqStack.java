// 895 https://leetcode.com/problems/maximum-frequency-stack/
import java.util.*;

class FreqStack {
    Map<Integer, Integer> count;
    Map<Integer, Stack<Integer>> freqStack;
    int maxFreq;

    public FreqStack() {
        count = new HashMap<>();
        freqStack = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int freq = count.getOrDefault(x, 0) + 1;
        count.put(x, freq);

        if (maxFreq < freq) {
            maxFreq = freq;
        }

        Stack<Integer> stack = freqStack.getOrDefault(freq, new Stack<>());
        stack.push(x);
        freqStack.put(freq, stack);
    }

    public int pop() {
        int retVal = freqStack.get(maxFreq).pop();
        count.put(retVal, count.get(retVal) - 1);

        if (freqStack.get(maxFreq).size() == 0) {
            freqStack.remove(maxFreq);
            maxFreq--;
        }
        return retVal;
    }
}
