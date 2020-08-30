import java.util.*
//435 https://leetcode.com/problems/non-overlapping-intervals/
fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
    if (intervals.size <= 1) return 0

    // Make one interval currently included and iterate over the rest of the intervals


    // if there is an interval which is completely covering another interval, then we should remove that (larger) interval
    // because that larger interval has more chance of overlapping with other intervals in the future and that's going to increase our count
    // hence remove the larger interval

    //If the two intervals are overlapping but two are not completely covering each other, keep the last included as is and remove the current one

    // Basically check that removing which interval will decrement the possibility of intersection in the future

    // sort the intervals by their start values. If the start values are same then by their end values.
    //Smallest of either comes first
    Arrays.sort(intervals) { o1, o2 -> if (o1[0] == o2[0]) o1[1] - o2[1] else o1[0] - o2[0] }

    var lastIncludedIntervalIndex = 0
    var eraseCount = 0
    var i = 1
    while (i < intervals.size) {
        if (intervals[i][0] >= intervals[lastIncludedIntervalIndex][1]) {
            // there is no overlap, the lastIncluded interval will never be removed, move to the next one
            lastIncludedIntervalIndex = i
        } else {
            // there is an overlap
            eraseCount++
            if(intervals[i][1] < intervals[lastIncludedIntervalIndex][1]) {
                lastIncludedIntervalIndex = i
            }
        }
        i++
    }
    return eraseCount
}


fun main() {
    println(eraseOverlapIntervals(arrayOf(intArrayOf(1,3), intArrayOf(2,4), intArrayOf(3,4))))
}