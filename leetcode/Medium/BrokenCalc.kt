public class Solution {
	fun brokenCalc(X : Int, Y : Int): Int {
		var steps: Int = 0
		var x = X
		var y = Y
		while(true) {
			if(x >= y) {
			    break
			}

			if(y % 2 == 0) y = y / 2
			else y++;

			steps++;
		}

		steps += (x - y) // if x and y are not same

		return steps
	}	
}
