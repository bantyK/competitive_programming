import java.util.*;

// 1182 https://leetcode.com/problems/shortest-distance-to-target-color/
public class ShortestDistanceToColor {

    public static void main(String[] args) {
        ShortestDistanceToColor obj = new ShortestDistanceToColor();
        System.out.println(obj.shortestDistanceColor(new int[]{1, 1, 2, 1, 3, 2, 2, 3},
                new int[][]{{1, 3}, {2, 2}, {6, 1}}));

        System.out.println(obj.shortestDistanceColor(new int[]{1, 2}, new int[][]{{0, 3}}));

        System.out.println(obj.shortestDistanceColor(new int[]{2, 1, 2, 2, 1}, new int[][]{{1, 1}, {4, 3}, {1, 3}, {4, 2}, {2, 1}}));
    }

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int[][] left = precomputeLeft(colors);
        int[][] right = precomputeRight(colors);

        List<Integer> distances = new ArrayList<>();

        for (int[] query : queries) {
            int index = query[0];
            int targetColor = query[1];

            if (left[targetColor][index] == -1 || right[targetColor][index] == -1) {
                distances.add(Math.max(left[targetColor][index], right[targetColor][index]));
            } else {
                distances.add(Math.min(left[targetColor][index], right[targetColor][index]));
            }
        }
        return distances;
    }

    private int[][] precomputeRight(int[] colors) {
        int[][] right = new int[4][colors.length];
        for (int i = 1; i <= 3; i++) {
            Arrays.fill(right[i], -1);
        }
        for (int color = 1; color <= 3; color++) {
            for (int i = colors.length - 1; i >= 0; i--) {
                if (colors[i] == color) {
                    right[color][i] = 0;
                } else if (i < colors.length - 1 && right[color][i + 1] != -1) {
                    right[color][i] = right[color][i + 1] + 1;
                }
            }
        }
        return right;
    }

    private int[][] precomputeLeft(int[] colors) {
        int[][] left = new int[4][colors.length];
        for (int i = 1; i <= 3; i++) {
            Arrays.fill(left[i], -1);
        }
        for (int color = 1; color <= 3; color++) {
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] == color) {
                    left[color][i] = 0;
                } else if (i > 0 && left[color][i - 1] != -1) {
                    left[color][i] = left[color][i - 1] + 1;
                }
            }
        }
        return left;
    }

    // Solution using Binary search
    public List<Integer> shortestDistanceColorBinarySearch(int[] colors, int[][] queries) {
        List<Integer> distances = new ArrayList<>();
        Map<Integer, List<Integer>> colorListMap = new HashMap<>();
        colorListMap.put(1, new ArrayList<>()); // 1 4
        colorListMap.put(2, new ArrayList<>()); // 0 2 3
        colorListMap.put(3, new ArrayList<>());

        for (int i = 0; i < colors.length; i++) {
            colorListMap.get(colors[i]).add(i);
        }

        for (int[] query : queries) {
            int index = query[0];
            int target = query[1];

            if (colorListMap.get(target).size() == 0) distances.add(-1);
            else distances.add(binarySearch(colorListMap.get(target), index));

        }
        return distances;
    }


    private int binarySearch(List<Integer> values, int index) {
        int left = 0;
        int right = values.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (values.get(mid) < index) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int insertPosition = left;

        if (insertPosition == 0) {
            // index is smaller than all the values of the list, so the closest point to it will be the first element in the list
            return values.get(0) - index;
        } else if (insertPosition == values.size()) {
            // index is larger than all the values of the list, so the closest point to it will be the last element in the list
            return index - values.get(insertPosition - 1);
        } else {
            // the element lies between 2 values in the list.
            int leftPos = index - values.get(insertPosition - 1);
            int rightPos = values.get(insertPosition) - index;
            return Math.min(leftPos, rightPos);
        }
    }


    // Brute force
    public List<Integer> shortestDistanceColorBruteForce(int[] colors, int[][] queries) {
        List<Integer> distances = new ArrayList<>();
        for (int[] query : queries) {
            int index = query[0];
            int targetColor = query[1];

            int rightDistance = Math.abs(rightDistance(colors, index, targetColor) - index);
            int leftDistance = Math.abs(leftDistance(colors, index, targetColor) - index);

            int distance = Math.min(leftDistance, rightDistance) == Integer.MAX_VALUE
                    ? -1
                    : Math.min(leftDistance, rightDistance);

            distances.add(distance);
        }
        return distances;
    }

    private int rightDistance(int[] colors, int start, int targetColor) {
        for (int i = start; i < colors.length; i++) {
            if (colors[i] == targetColor) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    private int leftDistance(int[] colors, int start, int targetColor) {
        for (int i = start; i >= 0; i--) {
            if (colors[i] == targetColor) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }
}
