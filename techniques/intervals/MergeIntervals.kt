import java.util.*

//56 https://leetcode.com/problems/merge-intervals/
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    Arrays.sort(intervals) { i1, i2 ->
        if (i1[0] == i2[0]) i1[1] - i2[1]
        else i1[0] - i2[0]
    }
    val result = mutableListOf<IntArray>()

    var currentInterval = intervals[0]
    result.add(currentInterval)

    for (interval in intervals) {
        if (currentInterval[1] >= interval[0]) {
            // keep updating the upper bound of interval untill there is an overlap
            // the lower bound need not change because the array is sorted, so it will be minimum from start
            currentInterval[1] = Math.max(currentInterval[1], interval[1])
        } else {
            // We have found a non overlapping interval, so update the current interval and repeat the same process all
            // over again
            currentInterval = interval
            result.add(currentInterval)
        }
    }

    return result.toTypedArray()
}

fun main() {
    val result1 = merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18)))
    result1.forEach { print(it.contentToString()) }

    println()

    val result2 = merge(arrayOf(intArrayOf(1, 5), intArrayOf(2, 10), intArrayOf(8, 9)))
    result2.forEach { println(it.contentToString()) }


}