// 346 https://leetcode.com/problems/moving-average-from-data-stream/
class MovingAverage {

    Queue<Integer> deque;
    int maxSize;
    double runningSum;
    public MovingAverage(int size) {
        maxSize = size;
        runningSum = 0;
        deque = new LinkedList<>();
    }

    public double next(int val) {
        if(!deque.isEmpty() && deque.size() == maxSize) {
            runningSum -= deque.poll();
        }

        runningSum += val;
        deque.offer(val);
        return runningSum / deque.size();
    }
}
