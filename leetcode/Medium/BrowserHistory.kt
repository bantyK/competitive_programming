import java.util.*

// 1472 https://leetcode.com/problems/design-browser-history/
class BrowserHistory(homepage: String) {

    private val backStack = Stack<String>()
    private val forwardStack = Stack<String>()

    init {
        backStack.push(homepage)
        forwardStack.push(homepage)
    }


    fun visit(url: String) {
        backStack.push(url)
        forwardStack.clear()
        forwardStack.push(url)
    }

    fun back(steps: Int): String {
        var i = steps
        while (backStack.size > 1 && i-- > 0) {
            val top = backStack.pop()
            if (forwardStack.peek() != top)
                forwardStack.push(top)
        }
        return backStack.peek()
    }

    fun forward(steps: Int): String {
        var i = steps
        var top = forwardStack.peek()
        while (forwardStack.isNotEmpty() && i-- > 0) {
            top = forwardStack.pop()
            if (backStack.peek() != top) {
                backStack.push(top)
            }
        }
        if (forwardStack.isEmpty()) forwardStack.push(top)
        return top
    }
}

fun main() {
    val browserHistory = BrowserHistory("a")
    browserHistory.visit("b")
    browserHistory.visit("c")
    browserHistory.visit("d")
    println(browserHistory.back(1))
    println(browserHistory.back(1))
    println(browserHistory.forward(1))
    browserHistory.visit("e")
    println(browserHistory.forward(2))
    println(browserHistory.back(2))
    println(browserHistory.back(7))

}

/*
["BrowserHistory","visit","visit","visit","back","forward"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[3],[3]]

["BrowserHistory","visit","visit","visit","back","forward"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[3],[30]]

["BrowserHistory","visit","visit","visit","back","forward"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[30],[30]]

["BrowserHistory","visit","visit","visit","back","visit","forward"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[3],["flipkart.com"],[1]]

["BrowserHistory","visit","visit","visit","back","visit","back","forward"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[3],["flipkart.com"],[1],[1]]

["BrowserHistory","visit","visit","visit","back","visit","back","forward"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[3],["flipkart.com"],[3],[2]]

["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]

["BrowserHistory","visit","back","back","forward","forward","visit","visit","back"]
[["zav.com"],["kni.com"],[7],[7],[5],[1],["pwrrbnw.com"],["mosohif.com"],[9]]

["BrowserHistory","back","back","visit","forward","visit","visit","visit","back","visit","visit","visit","back","visit","visit","visit","visit","back","visit","visit","visit","visit","visit","visit","visit","back","forward","back","forward","visit","back","visit","visit"]
[["icpbj.com"],[1],[10],["xbepk.com"],[8],["kls.com"],["dlkwxpf.com"],["pnep.com"],[9],["rmis.com"],["bxf.com"],["dz.com"],[2],["acuqsax.com"],["dcvo.com"],["ijbg.com"],["nlott.com"],[7],["dd.com"],["vssnq.com"],["teur.com"],["pn.com"],["szi.com"],["brhldg.com"],["yulyoqv.com"],[4],[10],[8],[5],["av.com"],[3],["okr.com"],["meli.com"]]
 */