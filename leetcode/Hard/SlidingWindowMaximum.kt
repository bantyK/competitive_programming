import java.util.*

//239 https://leetcode.com/problems/sliding-window-maximum/
class SlidingWindowMaximum {
    fun maxSlidingWindow(a: IntArray?, k: Int): IntArray {
        if (a == null || k <= 0) {
            return IntArray(0)
        }
        val n = a.size
        val res = IntArray(n - k + 1)
        var resultIndex = 0 // used to keep track of indices in the result array
        val deque: Deque<Int> = LinkedList()
        for (i in 0 until n) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                // remove the elements which are not in the range k
                // the lowest index which should be present in the deque is i - k - 1. If the index is lower than this
                // then it should be removed. This is also the reason we are storing the index of the numbers and not the actual numbers
                deque.poll() // this removes from the number from the head of the deque
            }

            // start from the last of the deque and start removing the element which is less than the current element,
            // because they are useless
            // this also ensures that the head of the deque represent the largest element(in the window) in the deque
            // we are removing the item from the right side(this is the side where 'i' is)
            // this is because if there is a number which is less than a[i] and its index in the array is less than i
            // this number can never be a max number. Because for all the possible windows a[i] will be greater than the number
            // at a[q.peekLast]
            while (!deque.isEmpty() && a[deque.peekLast()] < a[i]) {
                deque.pollLast()
            }
            deque.offer(i) // ads an element at the head of the deque

            // do not put the first k - 1 numbers
            if (i >= k - 1) {
                res[resultIndex++] = a[deque.peek()]
            }
        }
        return res
    }
}

fun main() {

}