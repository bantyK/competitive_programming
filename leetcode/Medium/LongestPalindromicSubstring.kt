//5 https://leetcode.com/problems/longest-palindromic-substring/
var palinStart = 0
var plainLen = 1
fun longestPalindromicSubstring(s: String): String {
    val len = s.length
    val cache = Array(len) { IntArray(len).apply { fill(-1) } }
    palinStart = 0
    plainLen = 1
    helper(s, 0, s.length - 1, cache)
    return s.substring(palinStart, palinStart + plainLen)
}

private fun helper(s: String, start: Int, end: Int, cache: Array<IntArray>): Int {
    if (start > end) return 0

    if (start == end) return 1

    if (cache[start][end] != -1) {
        return cache[start][end]
    }

    if (s[start] == s[end]) {
        val remainingLen = end - start - 1
        if (remainingLen == helper(s, start + 1, end - 1, cache)) {
            cache[start][end] = 2 + remainingLen
            if (cache[start][end] > plainLen) {
                plainLen = cache[start][end]
                palinStart = start
            }
            return cache[start][end]
        }
    }

    val lengthConsideringFirstChar = helper(s, start, end - 1, cache)
    val lengthConsideringLastChar = helper(s, start + 1, end, cache)

    cache[start][end] = Math.max(lengthConsideringFirstChar, lengthConsideringLastChar)
    if (cache[start][end] > plainLen) {
        plainLen = cache[start][end]
        palinStart = start
    }
    return cache[start][end]
}

// Bottom up algorithm
fun longestPalindrome(s: String): String {
    val len = s.length
    if(len == 0) return s

    val dp = Array(len){ BooleanArray(len) }

    var substringStart = 0
    var substringLen = 1

    for(i in dp.indices) dp[i][i] = true

    for(start in len - 1 downTo 0) {
        for(end in start + 1 until len) {
            if(s[start] == s[end]) {
                if(end - start == 1 || dp[start + 1][end - 1]) {
                    dp[start][end] = true
                }
                if(dp[start][end] && end - start + 1 > substringLen) {
                    substringLen = end - start + 1
                    substringStart = start
                }
            }
        }
    }

    return s.substring(substringStart, substringStart + substringLen)
}

fun main() {
    println(longestPalindrome("aacbaa"))
    println(longestPalindrome("aabaacaaaaaaaaa"))
}
