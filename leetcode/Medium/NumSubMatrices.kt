fun numSubmat(mat: Array<IntArray>): Int {
    val rows = mat.size
    val cols = mat[0].size

    var count = 0

    for (row in 0 until rows) {
        for (col in cols - 2 downTo 0) {
            if (mat[row][col] == 1) {
                mat[row][col] += mat[row][col + 1]
            }
        }
    }

    for (row in 0 until rows) {
        for (col in 0 until cols) {
            var minWidth = mat[row][col]
            for (depth in row until rows) {
                if (mat[depth][col] == 0) {
                    break;
                }
                minWidth = Math.min(mat[depth][col], minWidth)
                count += minWidth

            }
        }
    }

    return count
}

fun main() {
    numSubmat(arrayOf(intArrayOf(1, 0, 1), intArrayOf(1, 1, 1), intArrayOf(1, 0, 1)))
}