import java.util.*
import kotlin.collections.HashSet

//1284 https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/
class State(private val matrix: Array<IntArray>, val steps: Int) {

    private val rows: Int = matrix.size
    private val cols: Int = matrix[0].size

    fun done(): Boolean {
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] == 1) return false
            }
        }
        return true
    }

    fun neighbors(): List<State> {
        val neigh = mutableListOf<State>()

        // Generate matrices by flipping cells at all positions
        // This basically generates all possible matrices
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                neigh.add(flipNeighbours(r, c))
            }
        }

        return neigh
    }

    private fun flipNeighbours(row: Int, col: Int): State {
        val next = matrix.map { it.clone() }.toTypedArray()

        next[row][col] = 1 - matrix[row][col]
        if (row + 1 < rows) next[row + 1][col] = 1 - matrix[row + 1][col]
        if (row - 1 >= 0) next[row - 1][col] = 1 - matrix[row - 1][col]
        if (col + 1 < cols) next[row][col + 1] = 1 - matrix[row][col + 1]
        if (col - 1 >= 0) next[row][col - 1] = 1 - matrix[row][col - 1]

        return State(next, steps + 1)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                builder.append(matrix[r][c])
            }
        }

        return builder.toString()
    }
}

fun minFlips(mat: Array<IntArray>): Int {
    val initialState = State(mat, 0)

    if (initialState.done()) return 0 // matrix is a zero matrix, no processing required

    val visited = HashSet<String>()
    visited.add(initialState.toString())

    val queue = LinkedList<State>()
    queue.offer(initialState)

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        for (nextState in current.neighbors()) {
            if (nextState.done()) {
                return nextState.steps
            }
            val str = nextState.toString()
            if (!visited.contains(str)) {
                visited.add(str)
                queue.offer(nextState)
            }
        }
    }

    return -1
}

fun main() {
    println(minFlips(arrayOf(intArrayOf(0, 0), intArrayOf(0, 1))))
    println(minFlips(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(0, 0, 0))))
}