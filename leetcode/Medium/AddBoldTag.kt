//616 https://leetcode.com/problems/add-bold-tag-in-string/
fun addBoldTag(s: String, dict: Array<String>): String {

    if (s.isEmpty()) return s

    val intervals: MutableList<IntArray> = ArrayList()

    for (word in dict) {
        val indices = findAllOccurrenceOf(s, word)
        if (indices.isNotEmpty()) {
            intervals.addAll(indices)
        }
    }

    if (intervals.size > 0) {

        val mergedIntervals = mergeIntervals(intervals)

        val stringBuilder = StringBuilder(s)

        var offset = 0
        for (interval in mergedIntervals) {
            stringBuilder.insert(interval[0] + offset, "<b>")
            stringBuilder.insert(interval[1] + 3 + offset, "</b>")
            offset += 7
        }
        return stringBuilder.toString()
    }
    return s
}


fun mergeIntervals(intervals: MutableList<IntArray>): List<IntArray> {
    intervals.sortWith { i1, i2 -> i1[0] - i2[0] }

    val result: MutableList<IntArray> = ArrayList()

    var currentInterval = intervals[0]
    result.add(currentInterval)
    for (i in 1 until intervals.size) {
        val interval = intervals[i]

        if (currentInterval[1] >= interval[0]) {
            currentInterval[1] = Math.max(currentInterval[1], interval[1])
        } else {
            currentInterval = interval
            result.add(currentInterval)
        }
    }

    return result
}

private fun findAllOccurrenceOf(s: String, word: String): List<IntArray> {
    var index = 0
    val indices: MutableList<IntArray> = ArrayList()
    while (index != -1) {
        index = s.indexOf(word, index)
        if (index >= 0) {
            indices.add(intArrayOf(index, index + word.length))
            index++
        }

    }
    return indices
}

fun addBoldTag2(s: String, dict: Array<String>): String {
    // the indices with true value in this array will be bold
    val boldParts = BooleanArray(s.length)

    var end = 0
    for(i in s.indices) {
        for(word in dict) {
            if(s.startsWith(word, i)) {
                // end is the index which indicates the end index of the bold string for the current index at string `i`
                end = Math.max(end, i + word.length)
            }
        }

        // we have already calculated end and we know that all the indices which are before end are bold.
        //"abc" when we are 'a', and if we have "abc" in the dict. we know that from i to i + 3 will be bold
        // so i will be pointing to 3
        // when we reach 'b', the startsWith will return false but i is still less than end which indicates that
        // even though startsWith is returning false, we still need to bold it

        // We are doing this because we are traversing the string char by char
        if(i < end) {
            boldParts[i] = true
        }
    }

    val builder = StringBuilder()
    var i = 0
    while(i < s.length) {
        if(!boldParts[i]) {
            builder.append(s[i])
            i++
            continue
        }

        var j = i
        while(j < s.length && boldParts[j]) j++
        builder.append("<b>" + s.substring(i,j) + "</b>")
        i = j
    }

    return builder.toString()
}

fun main() {

    println(addBoldTag2("aaabcaaa", arrayOf("aaa")))
//    println(addBoldTag2("aaabbcc", arrayOf("aaa", "aab", "bc", "aaabbcc")))


//    println(addBoldTag("abc", arrayOf("")))


}
