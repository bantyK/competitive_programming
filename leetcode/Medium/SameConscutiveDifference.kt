import java.lang.StringBuilder

//967 https://leetcode.com/problems/numbers-with-same-consecutive-differences/
fun numsSameConsecDiff(n: Int, k: Int): IntArray {
    val nums = ArrayList<Int>()
    if (n == 1) nums.add(0)

    for (i in 1..9) {
        val builder = StringBuilder()
        helper(i, n, k, builder, nums)
    }
    return nums.toIntArray()
}

private fun helper(currentNum: Int, totalDigits: Int, k: Int, builder: StringBuilder, nums: ArrayList<Int>) {
    if (currentNum < 0 || currentNum > 9) return

    builder.append(currentNum)

    if (builder.length == totalDigits) {
        nums.add(builder.toString().toInt())
        builder.deleteCharAt(builder.length - 1)
        return
    }

    helper(currentNum + k, totalDigits, k, builder, nums)
    if (k > 0) {
        helper(currentNum - k, totalDigits, k, builder, nums)
    }
    builder.deleteCharAt(builder.length - 1)
}

///////////////////Optimised algorithm///////////////////
fun numsSameConsecDiffOptimised(n: Int, k: Int): IntArray {
    val result = ArrayList<Int>()
    if (n == 1) result.add(0)

    for (i in 1..9) {
        recurse(i, n - 1, k, result)
    }

    return result.toIntArray()
}

private fun recurse(num: Int, remainingDigits: Int, k: Int, result: ArrayList<Int>) {
    if (remainingDigits == 0) {
        result.add(num)
        return
    }

    val lastDigit = num % 10
    if (lastDigit >= k) {
        recurse(num * 10 + lastDigit - k, remainingDigits - 1, k, result)
    }

    if (k > 0 && lastDigit + k < 10) {
        // If K becomes 0 then both the if statements will be executed without any difference and we will get
        // duplicates. To avoid that, the k > 0 check is added
        recurse(num * 10 + lastDigit + k, remainingDigits - 1, k, result)
    }
}
