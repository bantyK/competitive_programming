import java.util.*;

//1104 https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/
public class PathInInvertedTree {
    public static void main(String[] args) {
        PathInInvertedTree obj = new PathInInvertedTree();
        System.out.println(obj.pathInZigZagTree(14));
        System.out.println(obj.pathInZigZagTree(12));
    }

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<>();
        tracePath(label, result);
        return result;
    }

    private void tracePath(int label, List<Integer> result) {
        if (label == 0) return;
        result.add(0, label);
        int level = getLevel(label);
        //inversion formula. Max elem in level + Min elem in level - label
        final int val = (int) (Math.pow(2, level) + Math.pow(2, level + 1) - 1 - label);
        tracePath(val / 2, result);
    }

    private int getLevel(int label) {
        int level = 0;
        while (label > 1) {
            label /= 2;
            level++;
        }
        return level;
    }
}
