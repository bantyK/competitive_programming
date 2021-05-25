import java.util.LinkedList;
import java.util.Queue;

//1871 https://leetcode.com/problems/jump-game-vii/
public class JumpGame7 {
    public static void main(String[] args) {
        JumpGame7 obj = new JumpGame7();
        System.out.println(obj.canReach("00000000000000000000000000010100", 1, 8));
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int farthest = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();

//            System.out.println(curr);

            if (curr == s.length() - 1) return true;

            for (int i = Math.max(farthest, curr + minJump); i <= Math.min(s.length() - 1, curr + maxJump); i++) {
                if (s.charAt(i) == '0') {
                    queue.offer(i);
                }
            }

            farthest = curr + maxJump + 1;
        }
        return false;
    }

}