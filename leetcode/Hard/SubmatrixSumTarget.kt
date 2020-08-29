//1074 https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
fun numSubmatrixSumTarget(matrix: Array<IntArray>, target: Int): Int {
    var count = 0
    if (matrix.isNullOrEmpty() || matrix[0].isEmpty()) return count

    val rows = matrix.size
    val cols = matrix[0].size + 1
    val sum = Array(rows) { IntArray(cols) }

    // Row wise prefix sum
    for (i in sum.indices) {
        for (j in 1 until sum[0].size) {
            sum[i][j] = sum[i][j - 1] + matrix[i][j - 1]
        }
    }

    for (colStart in 0 until cols) {
        for (colEnd in colStart + 1 until cols) {
            // matrices between the 2 cols
            var sumOfSubmatrix = 0
            val map = HashMap<Int, Int>()
            map[0] = 1
            for (l in 0 until rows) {
                sumOfSubmatrix += sum[l][colEnd] - sum[l][colStart]
                if (map.containsKey(sumOfSubmatrix - target)) {
                    count += map[sumOfSubmatrix - target] ?: 0
                }
                map[sumOfSubmatrix] = map.getOrDefault(sumOfSubmatrix, 0) + 1
            }
        }
    }
    return count
}


// This is the brute force approach, Time complexity is O(n^6)
fun countSubmatrices(matrix: Array<IntArray>, target: Int) : Int {
    var count = 0

    for(row1 in matrix.indices) {
        for(row2 in row1 until matrix.size) {
            for(col1 in matrix[0].indices) {
                for(col2 in col1 until matrix[0].size) {
                    var sum = 0
                    for (i in row1..row2) {
                        for (j in col1..col2) {
                            sum += matrix[i][j]
                        }
                    }

                    if (target == sum) {
                        count++
                    }
                }
            }
        }
    }
    return count
}


fun main() {
    println(countSubmatrices(arrayOf(intArrayOf(0,1,0), intArrayOf(1,1,1), intArrayOf(0,1,0)), 0))
    println(numSubmatrixSumTarget(arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, -1)), 0))
//    println(countSubmatrices(arrayOf(intArrayOf(1,-1), intArrayOf(-1,1)), 0))
}