import java.util.*;

// 218 https://leetcode.com/problems/the-skyline-problem/
public class SkylineProblemDnC {

    public static void main(String[] args) {
        SkylineProblemDnC obj = new SkylineProblemDnC();
        System.out.println(obj.getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}));
    }

    public List<List<Integer>> getSkyline(int[][] building) {
        return recurse(building, 0, building.length - 1);
    }

    private LinkedList<List<Integer>> recurse(int[][] building, int start, int end) {
        if (start == end) {
            LinkedList<List<Integer>> res = new LinkedList<>();
            res.add(Arrays.asList(building[start][0], building[start][2])); // start point
            res.add(Arrays.asList(building[start][1], 0));
            return res;
        }

        int mid = start + (end - start) / 2;

        LinkedList<List<Integer>> skyline1 = recurse(building, start, mid);
        LinkedList<List<Integer>> skyline2 = recurse(building, mid + 1, end);
        return mergeSkyline(skyline1, skyline2);
    }

    private LinkedList<List<Integer>> mergeSkyline(LinkedList<List<Integer>> skyline1, LinkedList<List<Integer>> skyline2) {
        int h1 = 0, h2 = 0;
        LinkedList<List<Integer>> res = new LinkedList<>();
        while (skyline1.size() > 0 && skyline2.size() > 0) {
            List<Integer> sky1 = skyline1.getFirst();
            List<Integer> sky2 = skyline2.getFirst();
            int x, h;

            if (sky1.get(0) < sky2.get(0)) {
                // skyline1's x is smaller than skyline2's x
                h1 = sky1.get(1);
                h = Math.max(h1, h2);
                x = sky1.get(0);
                skyline1.removeFirst();
            } else if (sky1.get(0) > sky2.get(0)) {
                // skyline2's x is smaller than skyline1's x
                h2 = sky2.get(1);
                h = Math.max(h1, h2);
                x = sky2.get(0);
                skyline2.removeFirst();
            } else {
                // both have same height
                h1 = sky1.get(1);
                h2 = sky2.get(1);
                h = Math.max(h1, h2);
                x = sky1.get(0);
                skyline1.removeFirst();
                skyline2.removeFirst();
            }

            if (res.size() == 0 || res.get(res.size() - 1).get(1) != h) {
                // dont add redundant key points
                // two key points of same height should not be added
                res.add(Arrays.asList(x, h));
            }
        }

        // Add the remaining buildings
        while (skyline1.size() > 0) {
            List<Integer> skyline = skyline1.removeFirst();
            res.add(Arrays.asList(skyline.get(0), skyline.get(1)));
        }

        while (skyline2.size() > 0) {
            List<Integer> skyline = skyline2.removeFirst();
            res.add(Arrays.asList(skyline.get(0), skyline.get(1)));
        }


        return res;
    }
}
