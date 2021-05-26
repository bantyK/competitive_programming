import java.util.*;

// 636 https://leetcode.com/problems/exclusive-time-of-functions/
public class FunctionExclusiveTime {
    public static void main(String[] args) {
        FunctionExclusiveTime obj = new FunctionExclusiveTime();

        System.out.println(Arrays.toString(obj.exclusiveTime(3, Arrays.asList("0:start:0", "0:end:0", "1:start:1", "1:end:1", "2:start:2", "2:end:2", "2:start:3", "2:end:3"))));
        System.out.println(Arrays.toString(obj.exclusiveTime(2, Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:8"))));
        System.out.println(Arrays.toString(obj.exclusiveTime(1, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7"))));
        System.out.println(Arrays.toString(obj.exclusiveTime(2, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"))));
        System.out.println(Arrays.toString(obj.exclusiveTime(2, Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:7", "1:end:7", "0:end:8"))));
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        if (logs.size() == 0) return res;
        Stack<Integer> stack = new Stack<>(); // {id, time}
        int currentFunction = 0;
        int startTime = 0;

        for (String log : logs) {
            String[] split = log.split(":");
            int functionId = Integer.parseInt(split[0]);
            String key = split[1];
            int timestamp = Integer.parseInt(split[2]);

            if (key.equals("start")) {
                if (currentFunction >= 0) {
                    res[currentFunction] += timestamp - startTime;
                    stack.push(currentFunction);
                }
                currentFunction = functionId;
                startTime = timestamp;
            } else {
                // function ends
                res[currentFunction] += (timestamp - startTime + 1);
                currentFunction = !stack.isEmpty() ? stack.pop() : -1;
                startTime = timestamp + 1;
            }
        }

        return res;
    }
}