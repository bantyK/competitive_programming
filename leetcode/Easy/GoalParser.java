//1678 https://leetcode.com/problems/goal-parser-interpretation/
public class GoalParser {
	public String interpret(String command) {
        StringBuilder builder = new StringBuilder();
        int j = 0;
        while(j < command.length()) {
            char ch = command.charAt(j);
            switch(ch) {
                case 'G':
                    builder.append("G");
                    j++;
                    break;
                case '(':
                    if(command.charAt(j + 1) == ')') {
                        builder.append("o");
                        j+=2;
                    } else {
                        builder.append("al");
                        j+=4;
                    }
            }
        }

        return builder.toString();
    }    
}
