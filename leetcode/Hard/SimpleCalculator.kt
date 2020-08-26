import java.util.*

// 224 https://leetcode.com/problems/basic-calculator/
fun calculate(s: String): Int {
    val stack = Stack<Int>()
    var sign = 1

    var sum = 0
    var i = 0
    while (i < s.length) {
        var char = s[i]
        when (char) {
            in '0'..'9' -> {
                var num = 0
                var j = i
                while (j < s.length && s[j] in '0'..'9') {
                    num = (num * 10) + (s[j] - '0')
                    j++
                }
                i = j - 1
                num *= sign
                sum += num
            }

            '+' -> {
                sign = 1
            }

            '-' -> {
                sign = -1
            }

            '(' -> {
                stack.push(sum)
                stack.push(sign)
                sum = 0
                sign = 1
            }

            ')' -> {
                sum *= stack.pop() // first pop the sign and update the current sum
                sum += stack.pop() // then add whatever we have in the stack
            }
        }
        i++
    }

    return sum
}

fun main() {
    println(calculate("1+(2+3+4)-9"))
    println(calculate("12 + 22"))
    println(calculate("1 + 2"))
    println(calculate(""))
    println(calculate("   "))
}