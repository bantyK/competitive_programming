import java.util.*

fun main() {
    var t = readLine()!!.toInt()

    while (t-- > 0) {
        val n = readLine()!!.toInt()
        val line: List<Int> = readLine()!!.split(" ").map { it.toInt() }
        println(bfs(n, line))
    }
}

private fun bfs(n: Int, permutation: List<Int>): Int {
    val queue = LinkedList<IntArray>()
    val seen = mutableSetOf<Int>()
    queue.offer(intArrayOf(1, 0)) // initial state
    seen.add(1)
    while (!queue.isEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val current = queue.poll()
            val currentPosition = current[0]
            val currentSteps = current[1]
            if (currentPosition == n) {
                return currentSteps
            }

            var nextPosition = permutation[currentPosition]
            if (!seen.contains(nextPosition)) {
                queue.offer(intArrayOf(nextPosition, currentSteps))
                seen.add(nextPosition)
            }

            nextPosition = currentPosition - 1
            if (nextPosition > 0 && !seen.contains(nextPosition)) {
                queue.offer(intArrayOf(nextPosition, currentSteps + 1))
                seen.add(nextPosition)
            }

            nextPosition = currentPosition + 1
            if (!seen.contains(nextPosition)) {
                queue.offer(intArrayOf(nextPosition, currentSteps + 1))
                seen.add(nextPosition)
            }

        }
    }
    return -1 // this should not happen
}