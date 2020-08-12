import java.lang.IllegalStateException
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

// 773 https://leetcode.com/problems/sliding-puzzle/
// Solved configuration of board. Used to compare if the current state of the board is solved or not.
val solvedBoard = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 0))

// This class represent the state of a board and also expose some helper functions related to board.
class Board(private val matrix: Array<IntArray>, val steps: Int) {
    private val rows = matrix.size
    private val cols = matrix[0].size

    // Helper method to check if the board is solved.
    // The easiest way that I could find is compare with solved board configuration
    fun isDone(): Boolean {
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] != solvedBoard[r][c]) {
                    return false
                }
            }
        }
        return true
    }

    // row and col are the coordinates of the 0 tile
    fun neighbors(): List<Board> {
        val coordinates = getPositionOfEmptyCell(matrix)
        val row = coordinates.first
        val col = coordinates.second
        val list = ArrayList<Board>()

        // There are at most 4 neighbours possible because we can only move top, bottom, left and right
        if (row + 1 < rows) list.add(generateNeighbor(row, col, row + 1, col))
        if (row - 1 >= 0) list.add(generateNeighbor(row, col, row - 1, col))
        if (col + 1 < cols) list.add(generateNeighbor(row, col, row, col + 1))
        if (col - 1 >= 0) list.add(generateNeighbor(row, col, row, col - 1))

        return list
    }

    // This method returns the index of the empty cell (represented by 0)
    private fun getPositionOfEmptyCell(matrix: Array<IntArray>): Pair<Int, Int> {
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] == 0) {
                    return Pair(r, c)
                }
            }
        }
        throw IllegalStateException("Board must have an empty cell")
    }

    // row, col is where the 0 lies and neighbourRow,neighbourCol is the cell which will be swaped
    private fun generateNeighbor(row: Int, col: Int, neighbourRow: Int, neighbourCol: Int): Board {
        // A fancy way to copy a matrix
        val next = matrix.map { it.clone() }.toTypedArray()
        next[row][col] = matrix[neighbourRow][neighbourCol]
        next[neighbourRow][neighbourCol] = 0
        // this is the new state. Since we reach to this state by making 1 step from previous state,
        // we have to increment the count for this state
        return Board(next, steps + 1)
    }

    // This string representation will help us identify if we have already checked this state of the board
    // so that we dont get into an infinite loop. This is acting like the visited matrix of BFS algorithm
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

// The BFS function which will count the minimum number of steps to reach from one state to another
fun slidingPuzzle(board: Array<IntArray>): Int {
    val initialBoard = Board(board, 0)
    if (initialBoard.isDone()) {
        // the board is already solved.No steps required
        return 0
    }

    // set to maintain the list of seen boards
    val seen = HashSet<String>()
    // queue for BFS
    val queue = LinkedList<Board>()

    // add the initial state of the board
    seen.add(initialBoard.toString())
    queue.offer(initialBoard)

    while (queue.isNotEmpty()) {
        val currentBoard = queue.poll()

        for (nextBoard in currentBoard.neighbors()) {
            // we found a solved board. Return the steps
            if (nextBoard.isDone()) {
                return nextBoard.steps
            }

            // if this new state of the board is not seen before, we add it in the queue for processing and
            // also noting that this is already seen and not to add again
            if (!seen.contains(nextBoard.toString())) {
                seen.add(nextBoard.toString())
                queue.offer(nextBoard)
            }
        }
    }

    // The board cannot be solved, hence return -1
    return -1
}

fun main() {
    println(slidingPuzzle(arrayOf(intArrayOf(4, 1, 2), intArrayOf(5, 0, 3))))
    println(slidingPuzzle(arrayOf(intArrayOf(1, 2, 3), intArrayOf(5, 4, 0))))
    println(slidingPuzzle(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 0, 5))))
    println(slidingPuzzle(arrayOf(intArrayOf(3, 2, 4), intArrayOf(1, 5, 0))))
}