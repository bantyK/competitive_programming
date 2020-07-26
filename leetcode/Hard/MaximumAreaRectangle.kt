package stack

import java.util.*
import kotlin.math.max

//85 https://leetcode.com/problems/maximal-rectangle/
fun main() {

    val matrix = arrayOf(
            charArrayOf('1', '1', '1', '1'),
            charArrayOf('1', '0', '1', '1'),
            charArrayOf('1', '1', '1', '1')
    )
    println(maximalRectangle(matrix))
}

// return the area of largest rectangle containing only 1's
fun maximalRectangle(matrix: Array<CharArray>): Int {
    if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

    val arr = IntArray(matrix[0].size).apply { fill(0) }
    var maxArea = Int.MIN_VALUE
    for (row in matrix.indices) {
        for (col in matrix[row].indices) {
            if (matrix[row][col] == '1') {
                arr[col] += 1
            } else {
                arr[col] = 0
            }
        }
        maxArea = Math.max(maxArea, maximumAreaRectangleOneDArray(arr))
    }

    return maxArea
}

private fun maximumAreaRectangleOneDArray(arr: IntArray): Int {
    println(arr.contentToString())
    val nsr = nearestSmallerToRight(arr)
    val nsl = nearestSmallerToLeft(arr)
    val width = IntArray(arr.size).mapIndexed { i, _ -> nsr[i] - nsl[i] - 1 }
    val maxArea = IntArray(arr.size).mapIndexed { i, _ -> arr[i] * width[i] }.max()
    return maxArea ?: 0
}

private fun nearestSmallerToRight(arr: IntArray): IntArray {
    val stack = Stack<Int>()
    val result = IntArray(arr.size).apply { fill(arr.size) }

    for (i in arr.indices) {
        while (stack.isNotEmpty() && arr[stack.peek()] >= arr[i]) {
            result[stack.pop()] = i
        }
        stack.push(i)
    }

    return result
}

private fun nearestSmallerToLeft(arr: IntArray): IntArray {
    val stack = Stack<Int>()
    val result = IntArray(arr.size).apply { fill(-1) }

    for (i in arr.indices) {
        while (stack.isNotEmpty() && arr[stack.peek()] >= arr[i]) {
            stack.pop()
        }

        if (stack.isNotEmpty()) result[i] = stack.peek()
        stack.push(i)
    }

    return result
}