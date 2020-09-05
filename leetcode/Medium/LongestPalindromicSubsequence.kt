//516 https://leetcode.com/problems/longest-palindromic-subsequence/
fun longestPalindromeSubsequence(s: String): Int {
    val cache = Array(s.length) { IntArray(s.length).apply { fill(-1) } }
    return helper(s, 0, s.length - 1, cache)
}

//Bottom up
fun longestPalindromeSubseq(s: String): Int {
    val len = s.length
    val dp = Array(len) { IntArray(len) }

    for (i in dp.indices) {
        dp[i][i] = 1
    }
    /*  -- start
        |
        |
        end
    */

    for (start in 1 until len) {
        for (end in start - 1 downTo 0) {
            if (s[start] == s[end]) {
                dp[start][end] = 2 + dp[start - 1][end + 1]
            } else {
                dp[start][end] = Math.max(dp[start - 1][end], dp[start][end + 1])
            }
        }
    }
    return dp[len - 1][0]
}

private fun helper(s: String, start: Int, end: Int, cache: Array<IntArray>): Int {
    if (start > end) return 0
    if (start == end) return 1

    if (cache[start][end] != -1) return cache[start][end]

    if (s[start] == s[end]) {
        // part of the palindromic subsequence, look for the inner parts of the string
        return 2 + helper(s, start + 1, end - 1, cache)

    }

    // the first and last characters are not matching, so both can't be the part of a palindrome
    // Consider one character at a time and check if they form a palindrome
    // return the maximum length of the two choices
    val lengthWhenStartCharIsConsidered = helper(s, start, end - 1, cache)
    val lengthWhenEndCharIsConsidered = helper(s, start + 1, end, cache)

    cache[start][end] = Math.max(lengthWhenStartCharIsConsidered, lengthWhenEndCharIsConsidered)
    return cache[start][end]
}

fun main() {
    println(longestPalindromeSubseq("abacd"))
    println(longestPalindromeSubseq("agbdba"))
}