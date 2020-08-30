import java.util.*
//253 https://leetcode.com/problems/meeting-rooms-ii/
fun minMeetingRooms(intervals: Array<IntArray>?): Int {
    if (intervals.isNullOrEmpty()) return 0

    Arrays.sort(intervals) { i1, i2 -> i1[0] - i2[0] }

    val heap = PriorityQueue<IntArray> { i1, i2 -> i1[1] - i2[1] }

    heap.add(intervals[0])

    for (i in 1 until intervals.size) {
        val current = intervals[i]
        val latestEndingInterval = heap.peek()
        if (latestEndingInterval[1] > current[0]) {
            heap.add(current)
        } else {
            heap.poll()
            heap.add(current)
        }
    }
    return heap.size
}