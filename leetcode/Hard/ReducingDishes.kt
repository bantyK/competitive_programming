//1402 https://leetcode.com/problems/reducing-dishes/submissions/
fun maxSatisfaction(satisfaction: IntArray): Int {
    satisfaction.sort()
    val len = satisfaction.size
    val cache = Array(len + 1) { IntArray(len + 1).apply { fill(-1) } }

    return helper(satisfaction, 0, 1, cache)
}

private fun helper(satisfaction: IntArray, index: Int, coeff: Int, cache: Array<IntArray>): Int {
    if (index == satisfaction.size) {
        return 0
    }
    if (cache[index][coeff] != -1) return cache[index][coeff]
    // take the element at current index
    val choice1 = (satisfaction[index] * coeff) + helper(satisfaction, index + 1, coeff + 1, cache)
    // did not take the element at current index
    val choice2 = helper(satisfaction, index + 1, coeff, cache)

    cache[index][coeff] = Math.max(choice1, choice2)
    return cache[index][coeff]
}

fun main() {
//    println(maxSatisfaction(intArrayOf(-1, -8, 0, 5, -9)))
    println(maxSatisfaction(intArrayOf(4, 3, 2)))
}

/*
    -9 -8 -1 0 5

 */