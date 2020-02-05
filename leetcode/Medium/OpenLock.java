import java.util.*;

// 752 https://leetcode.com/problems/open-the-lock/
public class OpenLock {
    public static void main(String[] args) {
        OpenLock obj = new OpenLock();
        String[] deadlocks = new String[]{"8888"};
        String target = "0009";

        System.out.println(obj.openLock(deadlocks, target));
    }

    public int openLock(String[] deadlocks, String target) {
        Set<String> deadends = new HashSet<>(Arrays.asList(deadlocks));

        if (deadends.contains("0000") || deadends.contains(target)) {
            return -1;
        }

        int count = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    char ch = curr.charAt(j);
                    String backward = curr.substring(0, j) + (ch == '0' ? 9 : (ch - '0') - 1) + curr.substring(j + 1);
                    String forward = curr.substring(0, j) + (ch == '9' ? 0 : (ch - '0') + 1) + curr.substring(j + 1);

                    if (!visited.contains(backward) && !deadends.contains(backward)) {
                        queue.add(backward);
                        visited.add(backward);
                    }

                    if (!visited.contains(forward) && !deadends.contains(forward)) {
                        queue.add(forward);
                        visited.add(forward);
                    }
                }
            }

            count += 1;
        }

        return -1;
    }
}
