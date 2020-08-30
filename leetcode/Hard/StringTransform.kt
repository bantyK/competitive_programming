//1153 https://leetcode.com/problems/string-transforms-into-another-string/
// We can see that we can solve this by transforming one character to another, hence this could be a graph problem.
// one character is transformed into another. Hence we can make a directed edge from char in str1 to char in str2.
// There should not be any forks, meaning one character cannot have outgoing edges.
// Example: aa -> bc; This transformation is impossible to achieve because we everytime we do a
// transformation, it will change chars of str1 to same char aa -> bb -> cc -> dd... etc. We can never have 2 different characters like str2 has.

// Hence if a character has more than one outgoing edge (fork) that means that transformation is impossible.

// Cycles are allowed as long as there is a intermediate char between them
// Example: str1 = aabcc && str2 = ccdaa. If we make an edge between chars we will find a cycle between a and c. But we can have a transformation like this
// a -> x aabcc -> xxbcc;  c -> a xxbcc -> xxbaa; b -> d xxbcc -> xxdaa;  x -> c ccbaa (There is a cycle between a c and x)
// importand thing here is that there should be a character which is not in string 2 which we can use as temporary variable. If str2 contains 26 characters and the
// strings are not exactly equal than that definetely means that there is atleast one character which will have a fork meaning has to be converted into 2 different chars


fun canConvert(str1: String, str2: String): Boolean {
    if (str1 == str2) {
        return true
    }
    if (countCharFrequency(str2) >= 26) {
        return false
    }

    val map = mutableMapOf<Char, Char>()

    for (i in str1.indices) {
        val char1 = str1[i]
        val char2 = str2[i]

        if (map.containsKey(char1) && map[char1] != char2) {
            return false
        } else {
            map[char1] = char2
        }
    }
    return true
}

private fun countCharFrequency(str: String): Int {
    val set = mutableSetOf<Char>()
    for (char in str) {
        set.add(char)
    }
    return set.size
}

fun main() {
    println(canConvert("leetcode", "codeleet"))
}