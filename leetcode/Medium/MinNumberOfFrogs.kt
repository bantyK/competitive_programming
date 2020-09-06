import java.util.HashMap

// 1419 https://leetcode.com/problems/minimum-number-of-frogs-croaking/
fun minNumberOfFrogs(croakOfFrogs: String): Int {
    val count = IntArray(5)
    var currentFrog = 0 // this represent the count of frogs which are croaking simultaneously
    var maxFrog = 0 // total number of frogs required. Final result

    for (c in croakOfFrogs) {
        val index = "croak".indexOf(c)

        if (index == 0) {
            // a new croak is starting
            currentFrog++
            maxFrog = Math.max(currentFrog, maxFrog)
        }
        if (index > 0) {
            // this condition represents an invalid order. Eg: crkao
            // hence an invalid input
            if (count[index - 1] == 0)
                return -1
            // the frog is croaking the current letter so it is donr croaking the previous one. Hence decrement
            count[index - 1] -= 1
        }
        if (index == 4) {
            //one frog has finished croaking, so that frog can be removed from simultaneous croaking group of frogs
            currentFrog--
        }

        count[index] += 1
    }

    // At the end, if the currentFrog count is not 0, that means that a frog has not finshed croaking
    // completely and the string ended, hence it is an invalid input. return -1
    return if (currentFrog != 0) -1 else maxFrog
}

fun minNumberOfFrogsUsingStateMachine(croakOfFrogs: String): Int {
    val states: MutableMap<Int, Int> = HashMap()
    var res = 0
    for (i in 0..5) {
        states[i] = 0
    }
    for (element in croakOfFrogs) {
        val c = element
        if (c == 'c') {
            states[1] = states[1]!! + 1
        } else if (c == 'r') {
            if (states[1]!! > 0) {
                states[1] = states[1]!! - 1
                states[2] = states[2]!! + 1
            } else {
                return -1
            }
        } else if (c == 'o') {
            if (states[2]!! > 0) {
                states[2] = states[2]!! - 1
                states[3] = states[3]!! + 1
            } else {
                return -1
            }
        } else if (c == 'a') {
            if (states[3]!! > 0) {
                states[3] = states[3]!! - 1
                states[4] = states[4]!! + 1
            } else {
                return -1
            }
        } else if (c == 'k') {
            if (states[4]!! > 0) {
                states[4] = states[4]!! - 1
                states[5] = states[5]!! + 1
            } else {
                return -1
            }
        }
        res = Math.max(res, getSum(states))
    }
    for (i in 0..4) {
        if (states[i] != 0) {
            return -1
        }
    }
    return res
}

private fun getSum(states: Map<Int, Int>): Int {
    var sum = 0
    for (k in states.keys) {
        sum += states[k] ?: 0
    }
    return sum
}


fun main() {
    println(minNumberOfFrogs("crcoakroak"))
}