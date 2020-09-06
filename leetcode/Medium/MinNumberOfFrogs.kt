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

fun main() {
    println(minNumberOfFrogs("crcoakroak"))
}