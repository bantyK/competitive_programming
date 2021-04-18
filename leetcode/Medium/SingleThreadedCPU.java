import java.util.PriorityQueue;

// 18344 https://leetcode.com/problems/single-threaded-cpu/
public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        if (tasks.length == 0) return new int[0];

        PriorityQueue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> t1.startTime - t2.startTime);
        PriorityQueue<Task> availableTasks = new PriorityQueue<>((t1, t2) -> {
            if (t1.processingTime == t2.processingTime) {
                return t1.index - t2.index;
            }
            return t1.processingTime - t2.processingTime;
        });

        for (int i = 0; i < tasks.length; i++) {
            taskQueue.offer(new Task(i, tasks[i][0], tasks[i][1]));
        }

        // get the initial set of tasks
        updateAvailableQueue(taskQueue, availableTasks);

        int[] res = new int[tasks.length];
        int resIdx = 0;
        long currentTime = availableTasks.peek().startTime;

        while (!taskQueue.isEmpty() || !availableTasks.isEmpty()) {
            if (availableTasks.isEmpty()) {
                // this is required because lets say the last task ended at time t = 3 and the next task
                // start at time t = 10
                // in this case the while loop below will not add that task
                updateAvailableQueue(taskQueue, availableTasks);
            }

            Task task = availableTasks.poll();
            res[resIdx++] = task.index;
            currentTime += task.processingTime;

            // all the tasks with start time less than equal to current time becomes available to be picked
            while (!taskQueue.isEmpty() && taskQueue.peek().startTime <= currentTime) {
                availableTasks.add(taskQueue.poll());
            }
        }

        return res;
    }

    private void updateAvailableQueue(PriorityQueue<Task> taskQueue, PriorityQueue<Task> availableTasks) {
        int startTime = taskQueue.peek().startTime;

        while (!taskQueue.isEmpty() && taskQueue.peek().startTime == startTime) {
            availableTasks.offer(taskQueue.poll());
        }
    }

    static class Task {
        int index;
        int startTime;
        int processingTime;

        public Task(int index, int startTime, int processingTime) {
            this.index = index;
            this.startTime = startTime;
            this.processingTime = processingTime;
        }
    }
}