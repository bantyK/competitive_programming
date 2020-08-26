//227 https://leetcode.com/problems/basic-calculator-ii/

import java.util.*

const val ADD = 1
const val SUB = 2
const val MUL = 3
const val DIV = 4

fun calculateUsingStack(s: String): Int {
    val stack = Stack<Int>()
    var op = ADD

    var i = 0
    while (i < s.length) {
        val char = s[i]

        when (char) {
            in '0'..'9' -> {
                var num = 0
                var j = i
                while (j < s.length && s[j] >= '0' && s[j] <= '9') {
                    num = (num * 10) + (s[j] - '0')
                    j++
                }
                i = j - 1

                when (op) {
                    ADD -> {
                        stack.push(num)
                    }
                    SUB -> {
                        stack.push(-num)
                    }
                    MUL -> {
                        stack.push(stack.pop() * num)
                    }
                    DIV -> {
                        stack.push(stack.pop() / num)
                    }
                }

            }
            '+' -> {
                op = ADD
            }
            '-' -> {
                op = SUB
            }
            '*' -> {
                op = MUL
            }
            '/' -> {
                op = DIV
            }
        }

        i++
    }

    var res = 0
    while (stack.isNotEmpty()) {
        res += stack.pop()
    }

    return res
}


// We dont have to worry about the previous sums, for any index i, if the current operator is + or -, simply calculate the previous sums,
// store in res and update the tail with current num
// tail will basically contain the current num in progress and res contains all the sums which occured before and already accounted for
fun calculate(s: String): Int {
    var tail = 0
    var res = 0
    var opr = '+'
    var i = 0
    val operators = setOf('+', '-', '*', '/')
    while (i < s.length) {
        val char = s[i]

        if (char in '0'..'9') {
            var num = char - '0'
            while (i + 1 < s.length && s[i + 1] in '0'..'9') {
                num = (num * 10) + (s[i + 1] - '0')
                i++
            }

            when (opr) {
                '+' -> {
                    res += tail
                    tail = num
                }
                '-' -> {
                    res += tail
                    tail = -num
                }
                '*' -> {
                    tail *= num
                }
                '/' -> {
                    tail /= num
                }
            }
        } else if (char in operators) {
            opr = char
        }

        i++
    }

    return res + tail
}

fun main() {
    println(calculate("1*4+4"))

}
