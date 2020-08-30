import java.util.*
import kotlin.math.min

// 452 https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

fun findMinArrowShots(intervals: Array<IntArray>): Int {
    if (intervals.size <= 1) return intervals.size
    Arrays.sort(intervals) { o1, o2 -> if (o1[0] == o2[0]) o1[1] - o2[1] else o1[0] - o2[0] }
    var count = 0
    var minEnd = Int.MAX_VALUE

    for (interval in intervals) {
        if (interval[0] > minEnd) {
            count++
            minEnd = interval[1]
        }

        minEnd = Math.min(minEnd, interval[1])
    }
    return count + if (intervals.isEmpty()) 0 else 1
}

fun main() {
//    println(findMinArrowShots(arrayOf(intArrayOf(1, 6), intArrayOf(2, 8), intArrayOf(10, 16), intArrayOf(7, 12))))
//    println(findMinArrowShots(arrayOf(intArrayOf(1, 10), intArrayOf(2, 9), intArrayOf(3, 7), intArrayOf(4, 5))))
    println(findMinArrowShots(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 7), intArrayOf(8, 10))))
}