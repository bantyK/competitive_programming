package solutions.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


//https://leetcode.com/problems/task-scheduler/
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int cycles = 0;
        HashMap<Character, Integer> numTasks = new HashMap<>();

        for (char task : tasks) {
            numTasks.put(task, numTasks.getOrDefault(task, 0) + 1);
        }

        PriorityQueue<Integer> taskHeap = new PriorityQueue<>((a, b) -> b - a); // max heap

        taskHeap.addAll(numTasks.values());

        while (!taskHeap.isEmpty()) {
            List<Integer> sequence = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                if (!taskHeap.isEmpty()) {
                    sequence.add(taskHeap.remove());
                } else {
                    break;
                }
            }

            for (int i : sequence) {
                // there are more number of this task, hence decrement by one and add it again in the heap
                if (--i > 0) {
                    taskHeap.add(i);
                }
            }

            if (taskHeap.isEmpty()) {
                // At the last sequence, the heap will be empty so we should only add the number of tasks which are
                // present in the sequence
                cycles += sequence.size();
            } else {
                cycles += n + 1;
            }
        }

        return cycles;

    }


    public static void main(String[] args) {
        TaskScheduler obj = new TaskScheduler();

        int i = obj.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2);
        System.out.println(i);
    }
}
