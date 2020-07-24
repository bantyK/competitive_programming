import java.util.*;
//901 https://leetcode.com/problems/online-stock-span/
class StockSpanner {
    Stack<int[]> stack;
    int i;
    public StockSpanner() {
        stack = new Stack<int[]>();
        i = 0;
    }
    
    public int next(int price) {
        while(!stack.isEmpty() && price >= stack.peek()[0]) {
            stack.pop();
        }
        int res;
        
        if(stack.isEmpty()) res = i + 1;
        else res = i - stack.peek()[1];
        
        stack.push(new int[]{price, i++});
        return res;
    }
}


