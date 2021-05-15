import java.util.*;

// 715 https://leetcode.com/problems/range-module/
public class RangeModuleSolver {

    public static void main(String[] args) {
        RangeModule obj = new RangeModuleSolver().new RangeModule();
        obj.addRange(10, 20);
        obj.removeRange(14, 16);

        System.out.println(obj.queryRange(10, 14));
        System.out.println(obj.queryRange(13, 15));
        System.out.println(obj.queryRange(16, 17));
    }


    class RangeModule {

        // key is the starting point and value is the ending point
        TreeMap<Integer, Integer> intervals;

        public RangeModule() {
            intervals = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            if (left >= right) return; // invalid sequence

            // less than or equal to left
            // a starting point which is less than or equal to left
            Integer startPointNearestToLeft = intervals.floorKey(left);

            // less than or equal to right
            // a starting point which is less than or equal to right
            // these points are the *starting* points
            Integer startPointNearestToRight = intervals.floorKey(right);

            if (startPointNearestToLeft != null && intervals.get(startPointNearestToLeft) >= left) {
                // the existing sequence ends at a point farther than or equal to left
                // meaning the left is in between the existing sequence
                // in this case either the new sequence will completely lie inside the existing sequence
                // or extend the existing sequence
                // in either case the starting point will be same as the starting point of an existing sequence
                left = startPointNearestToLeft;
            }
            if (startPointNearestToRight != null && intervals.get(startPointNearestToRight) > right) {
                // there is a sequence which starts just before the new segment ends
                // in this case the existing segment and the new segment will combine into one
                /*
                       |---------|
                    |-----|   |------|
                           ||
                           \/
                    |----------------|

                 */

                right = intervals.get(startPointNearestToRight);
            }

            intervals.put(left, right);

            // remove the redundant segments from the map
            intervals.subMap(left, false, right, true).clear();
        }


        public boolean queryRange(int left, int right) {
            Integer start = intervals.floorKey(left);
            if (start == null) return false;
            return intervals.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            Integer start = intervals.floorKey(left);
            Integer end = intervals.floorKey(right);


            if (start != null && intervals.get(start) > left) {
                intervals.put(start, left);
            }

            if (end != null && intervals.get(end) > right) {
                intervals.put(right, intervals.get(end));
            }

            intervals.subMap(left, true, right, false).clear();
        }
    }

}