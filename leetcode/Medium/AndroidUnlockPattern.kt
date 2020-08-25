import java.util.*

// 351 https://leetcode.com/problems/android-unlock-patterns/
fun numberOfPatterns(m: Int, n: Int): Int {
    // this map keeps track of the intermediate values which must be visited before a pair is visited
    // for example, if we are visiting 3 from 1, we have to make sure that we have already visited 2
    // because 2 lies between 1 and 3.
    val intermediateMap: MutableMap<String, Int> = HashMap()
    prepareMap(intermediateMap)
    val visited = BooleanArray(10)
    var count = 0
    for (j in m..n) {
        // // 1 3 7 9 will generate same number of patterns, so calculate just for one of these and multiply by 4
        // same is true for 2 4 6 8
        // 5 is separate and does not fall under these symmetries
        count += backtrack(visited, intermediateMap, 1, j - 1) * 4
        count += backtrack(visited, intermediateMap, 2, j - 1) * 4
        count += backtrack(visited, intermediateMap, 5, j - 1)
    }
    return count
}

private fun backtrack(visited: BooleanArray, map: Map<String, Int>, current: Int, remaining: Int): Int {
    if (remaining < 0) return 0
    if (remaining == 0) return 1
    visited[current] = true
    var count = 0
    for (next in 1..9) {
        if (!visited[next]) {
            val key = getKey(current, next)
            if (map.containsKey(key) && !visited[map[key]!!]) {
                // we cannot visit this path yet
                continue
            }
            count += backtrack(visited, map, next, remaining - 1)
        }
    }
    visited[current] = false
    return count
}

private fun getKey(i: Int, j: Int): String {
    return i.toString() + "" + j
}

private fun prepareMap(intermediateMap: MutableMap<String, Int>) {
    /*
    1 2 3
    4 5 6
    7 8 9
    */
    intermediateMap[getKey(1, 3)] = 2
    intermediateMap[getKey(3, 1)] = 2
    intermediateMap[getKey(4, 6)] = 5
    intermediateMap[getKey(6, 4)] = 5
    intermediateMap[getKey(7, 9)] = 8
    intermediateMap[getKey(9, 7)] = 8
    intermediateMap[getKey(1, 7)] = 4
    intermediateMap[getKey(7, 1)] = 4
    intermediateMap[getKey(2, 8)] = 5
    intermediateMap[getKey(8, 2)] = 5
    intermediateMap[getKey(3, 9)] = 6
    intermediateMap[getKey(9, 3)] = 6
    intermediateMap[getKey(1, 9)] = 5
    intermediateMap[getKey(9, 1)] = 5
    intermediateMap[getKey(3, 7)] = 5
    intermediateMap[getKey(7, 3)] = 5
}

fun main() {
    println(numberOfPatterns(3, 4))
}