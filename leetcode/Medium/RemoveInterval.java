import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 5113 https://leetcode.com/problems/remove-interval/
public class RemoveInterval {
    public static void main(String[] args) {
        RemoveInterval obj = new RemoveInterval();

        int[][] intervals = new int[][]{
                {0, 3},
                {1, 4},
                {4, 7},
                {6, 12},
                {12, 19},
                {1, 13}
        };

        int[] toBeRemoved = new int[]{3, 10};

        List<List<Integer>> result = obj.removeInterval(intervals, toBeRemoved);

        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        if (intervals.length == 0) return result;

        for (int[] interval : intervals) {
            if (interval[0] >= toBeRemoved[0] && interval[1] <= toBeRemoved[1]) {
                continue;
            }

            int[] overlap = new int[]{Math.max(interval[0], toBeRemoved[0]), Math.min(interval[1], toBeRemoved[1])};

            if (interval[0] <= toBeRemoved[0] && interval[1] <= toBeRemoved[1]) {
                // left side
                if (overlap[0] < interval[1]) {
                    // there is an overlap
                    result.add(Arrays.asList(interval[0], overlap[0]));
                } else {
                    result.add(Arrays.asList(interval[0], interval[1]));
                }
            } else if (interval[0] >= toBeRemoved[0] && interval[1] >= toBeRemoved[1]) {
                // right side
                if (overlap[0] < overlap[1]) {
                    result.add(Arrays.asList(overlap[1], interval[1]));
                } else {
                    result.add(Arrays.asList(interval[0], interval[1]));
                }
            } else {
                result.add(Arrays.asList(interval[0], overlap[0]));
                result.add(Arrays.asList(overlap[1], interval[1]));
            }


        }

        return result;
    }

/*if(interval[0] < toBeRemoved[0] && interval[1] > toBeRemoved[1]) {
        result.add(Arrays.asList(interval[0], overlap[0]));
        result.add(Arrays.asList(overlap[1], interval[1]));
    } else if(interval[0] < toBeRemoved[0] && interval[1] < toBeRemoved[1]) {
        result.add(Arrays.asList(interval[0], overlap[0]));
    } else if(interval[0] > toBeRemoved[0] && interval[1] > toBeRemoved[1]){
        result.add(Arrays.asList(overlap[1], interval[1]));
    } else if(interval[1] <= toBeRemoved[0]) {
        result.add(Arrays.asList(interval[0], interval[1]));
    } else if(interval[0] >= toBeRemoved[1]) {
        result.add(Arrays.asList(interval[0], interval[1]));
    }*/

}
