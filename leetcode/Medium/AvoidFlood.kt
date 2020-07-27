import java.util.*
import kotlin.collections.HashMap

// 1488 https://leetcode.com/problems/avoid-flood-in-the-city/
fun avoidFlood(rains: IntArray): IntArray {
    val result = IntArray(rains.size)
    if (rains.isEmpty()) return result

    val noRainDays = TreeSet<Int>()
    val filledLakes = HashMap<Int, Int>()

    for (i in rains.indices) {
        if (rains[i] == 0) {
            noRainDays.add(i)
            result[i] = 1
        } else {
            val lake = rains[i]
            if (filledLakes.containsKey(lake)) {
                // we have to find a no rains day which is higher than the day when it rains. otherwise we will be drying an already dry lake which is a waste
                val dryDay = noRainDays.higher(filledLakes[lake]) ?: return IntArray(0)
                result[dryDay] = lake
                filledLakes.remove(lake)
                noRainDays.remove(dryDay)
            }

            filledLakes[lake] = i
            result[i] = -1
        }
    }
    return result
}


fun main() {
    println(avoidFlood(intArrayOf(1, 0, 0)).contentToString())
    println(avoidFlood(intArrayOf(1, 2, 0, 1, 2)).contentToString())
    println(avoidFlood(intArrayOf(1, 2, 0, 1, 0, 2)).contentToString())
    println(avoidFlood(intArrayOf(1, 2, 0, 0, 1, 0, 2)).contentToString())
    println(avoidFlood(intArrayOf(1, 2, 3, 0, 3, 0, 2, 0, 1)).contentToString())
    println(avoidFlood(intArrayOf(10, 20, 0, 10, 30, 0, 30, 20)).contentToString())

}