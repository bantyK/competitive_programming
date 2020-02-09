import java.util.*;

//1345 https://leetcode.com/problems/jump-game-iv/
public class JumpGame4 {
    public static void main(String[] args) {
        JumpGame4 obj = new JumpGame4();
        int[] arr = new int[]{11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13};
        System.out.println(obj.minJumps(arr));
    }

    public int minJumps(int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int jumps = 0;
        boolean[] visited = new boolean[arr.length];
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            indices.putIfAbsent(arr[i], new ArrayList<>());
            indices.get(arr[i]).add(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIndex = queue.poll(); // index
                if (visited[currIndex]) continue;

                visited[currIndex] = true;

                if (currIndex == arr.length - 1) {
                    return jumps;
                }

                if (currIndex + 1 < arr.length) queue.offer(currIndex + 1);
                if (currIndex - 1 >= 0) queue.offer(currIndex - 1);
                // add all the indices whose value is equal to arr[currIndex]
                int currValue = arr[currIndex];
                for(int index : indices.get(currValue)) {
                    if(!visited[index]) {
                        queue.offer(index);
                    }
                }
                indices.get(currValue).clear();
            }

            jumps += 1;
        }

        return -1;
    }

}
