import java.util.*;

// 754 https://leetcode.com/problems/reach-a-number/
public class ReachANumber {

    public static void main(String[] args) {
        ReachANumber obj = new ReachANumber();
//        System.out.println(obj.reachNumber(0));
//        System.out.println(obj.reachNumber(3));
//        System.out.println(obj.reachNumber(2));
        System.out.println(obj.reachNumber2(1000));
    }

    public int reachNumber(int target) {
        target = Math.abs(target);
        int sum = 0;
        int step = 0;

        while (true) {
            step++;
            sum += step;
            if (sum == target) {
                return step;
            } else if (sum > target && (sum - target) % 2 == 0) {
                return step;
            }
        }
    }

    public int reachNumber2(int target) {
        int move = 1;
        int jumpsReq = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int currentPos = queue.poll();

                if (currentPos == target) return jumpsReq;

                visited.add(currentPos);

                if (!visited.contains(currentPos + move)) {
                    queue.offer(currentPos + move);
                }

                if (!visited.contains(currentPos - move)) {
                    queue.offer(currentPos - move);
                }
            }
            move++;
            jumpsReq++;
        }

        return jumpsReq;
    }
}
