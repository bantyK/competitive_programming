// 1753 https://leetcode.com/problems/maximum-score-from-removing-stones/
import java.util.*;
public class MaximumScoreFromThreePiles {
	public int maximumScore(int a, int b, int c) {
		// {item(A,B,C}, remaining}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((num1,num2) -> num2 - num1);
		
		maxHeap.add(a);
		maxHeap.add(b);
		maxHeap.add(c);
		
		int score = 0;
		
		while(maxHeap.size() > 1) {
			int item1 = maxHeap.poll();
			int item2 = maxHeap.poll();
			
			score++;
			if(--item1 > 0) {
				maxHeap.offer(item1);
			}
			if(--item2 > 0) {
				maxHeap.offer(item2);
			}
		}
		
		return score;
	}
}
