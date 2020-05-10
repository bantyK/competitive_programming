//997 https://leetcode.com/problems/find-the-town-judge/
public class TownJudge {
    public static void main(String[] args) {
        TownJudge obj = new TownJudge();
        System.out.println(obj.findJudge(2, new int[][]{{1,2}}));
        System.out.println(obj.findJudge(3, new int[][]{{1,3},{2,3}}));
        System.out.println(obj.findJudge(3, new int[][]{{1,3},{2,3},{3,1}}));
        System.out.println(obj.findJudge(4, new int[][]{{1,3},{1,4},{2,3},{2,4},{4,3}}));
    }

    public int findJudge(int n, int[][] trust) {
        int[] inDegrees = new int[n];
        int[] outDegrees = new int[n];


        for(int[] t : trust) {
            int src = t[0] - 1;
            int dest = t[1] - 1;
            outDegrees[src]++;
            inDegrees[dest]++;
        }

        for(int i = 0; i < n; i++) {
            if(inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i + 1;
            }
        }

        return -1;
    }
}
