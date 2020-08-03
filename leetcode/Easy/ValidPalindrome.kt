// 125 https://leetcode.com/problems/valid-palindrome/
fun isPalindrome(s: String): Boolean {
    if (s.isEmpty()) return true
    return recurse(s.toLowerCase(), 0, s.length - 1)
}

internal fun recurse(s: String, start: Int, end: Int): Boolean {
    if (start >= end) return true

    if (s[start] == s[end]) {
        return recurse(s, start + 1, end - 1)
    } else if (!s[start].isLetterOrDigit()) {
        return recurse(s, start + 1, end)
    } else if (!s[end].isLetterOrDigit()) {
        return recurse(s, start, end - 1)
    } else {
        return false
    }
}

fun main() {
    println(isPalindrome("0P"))
}