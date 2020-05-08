public class Solution {
	fun brokenCalc(x : Int, y : Int): Int {
		var steps: Int = 0
		
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
