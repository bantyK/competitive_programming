import java.util.*;
// 682 https://leetcode.com/problems/baseball-game/
public class BaseballGame {
    public int calPoints(String[] ops) {
        List<Integer> points = new ArrayList<>();
        
        for(String op : ops) {
            switch(op) {
                case "+":
                    if(points.size() < 2) { return 0; }
                    int last = points.get(points.size()-1);
                    int secondLast = points.get(points.size()-2);
                    points.add(last+secondLast);
                    break;
                case "D":
                    points.add(2 * points.get(points.size() - 1));
                    break;
                case "C":
                    points.remove(points.get(points.size() - 1));
                    break;
                default:
                    points.add(Integer.parseInt(op));
                    break;
            }
        }
        
        int sum = 0;
        for(int i : points) sum += i;
        return sum;
    }
}