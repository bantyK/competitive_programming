import java.util.*;

// 582 https://leetcode.com/problems/kill-process/
public class KillProcess {

    public static void main(String[] args) {
        KillProcess obj = new KillProcess();
        List<Integer> res = obj.killProcess(
                Arrays.asList(1, 3, 10, 5),
                Arrays.asList(3, 0, 5, 3),
                5
        );

        System.out.println(res);
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // {PPID -> [PID1, PID2]}
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < ppid.size(); i++) {
            int parentId = ppid.get(i);
            map.putIfAbsent(parentId, new ArrayList<>());
            map.get(parentId).add(pid.get(i));
        }

        List<Integer> res = new ArrayList<>();
        dfs(map, kill, res);
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> map, int currentProcess, List<Integer> res) {
        res.add(currentProcess);
        for (int child : map.getOrDefault(currentProcess, Collections.emptyList())) {
            dfs(map, child, res);
        }
    }
}
