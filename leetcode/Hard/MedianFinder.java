import java.util.*;

// 295 https://leetcode.com/problems/find-median-from-data-stream/
public class MedianFinder {
    PriorityQueue<Integer> lower; // this is going to be a max heap
    PriorityQueue<Integer> higher; // this will be a min heap

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        lower = new PriorityQueue<>((a, b) -> b - a);
        higher = new PriorityQueue<>();
    }

    public static void main(String[] args) {
        /**
         * ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
         * [[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
         */
        MedianFinder obj = new MedianFinder();
        obj.addNum(-1);
        System.out.println(obj.findMedian());
        obj.addNum(-2);
        System.out.println(obj.findMedian());
        obj.addNum(-3);
        System.out.println(obj.findMedian());
//        obj.addNum(6);
//        obj.addNum(0);
    }

    public void addNum(int num) {
        if (lower.isEmpty()) {
            lower.add(num);
        } else if (num < lower.peek()) {
            lower.add(num);
        } else {
            higher.add(num);
        }

        if (Math.abs(higher.size() - lower.size()) > 1) {
            balanceHeaps();
        }
    }

    private void balanceHeaps() {
        PriorityQueue<Integer> hasMoreElement = higher.size() > lower.size() ? higher : lower;
        PriorityQueue<Integer> hasLessElement = higher.size() < lower.size() ? higher : lower;
        while ((hasMoreElement.size() - hasLessElement.size()) > 1) {
            hasLessElement.add(hasMoreElement.poll());
        }
    }

    public double findMedian() {
        if (lower.size() == higher.size()) {
            return (lower.peek() + higher.peek()) / 2.0;
        } else if (higher.size() > lower.size()) {
            return higher.peek();
        } else {
            return lower.peek();
        }
    }
}

