import java.util.*

//252 https://leetcode.com/problems/meeting-rooms/
fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
    // sort the intervals according to start times
    Arrays.sort(intervals) { i1, i2 -> i1[0] - i2[0] }

    for (i in 0 until intervals.size - 1) {
        val i1 = intervals[i]
        val i2 = intervals[i + 1]

        if (i1[1] > i2[0]) return false
    }

    return true
}

fun main() {
    println(canAttendMeetings(arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20))))
    println(canAttendMeetings(arrayOf(intArrayOf(0, 5), intArrayOf(5, 10))))
}