// 930 https://leetcode.com/problems/binary-subarrays-with-sum/
fun numSubarraysWithSum(A: IntArray, S: Int): Int {
    return atMostS(A, S) - atMostS(A, S - 1)
}

fun atMostS(A: IntArray, S: Int): Int {
    var count = 0
    var left = 0
    var sum = 0
    for (right in A.indices) {
        sum += A[right]

        while (left <= right && sum > S) {
            val leftNum = A[left++]
            sum -= leftNum
        }

        count += (right - left + 1)
    }

    return count
}

fun numSubarraysWithSumWithHashMap(A: IntArray, S: Int): Int {
    val map = HashMap<Int, Int>()
    map[0] = 1 // this for cases when the cumulative sum is exactly equal to S
    var cumulativeSum = 0
    var count = 0
    for(a in A) {
        cumulativeSum += a
        val diff = cumulativeSum - S

        if(map.containsKey(diff)) {
            count += map[diff] ?: 0
        }
        map[cumulativeSum] = map.getOrDefault(cumulativeSum, 0) + 1
    }

    return count
}

fun main() {
    println(numSubarraysWithSumWithHashMap(intArrayOf(1,1,0,1,0,0,1,1), 2))
}

/*
S = 2
0 : 1
A:  1 1 0 1 0 0 1 1
S:  1 2 2 3 3 3 4 5



 */

