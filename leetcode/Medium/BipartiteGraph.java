// 785 https://leetcode.com/problems/is-graph-bipartite/
public class BipartiteGraph {
	public boolean isBipartite(int[][] adj) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
	    int numVertex = adj.length;
       	 
       	for(int i = 0; i < numVertex; i++) graph.put(i, new ArrayList<>());
        
	    for(int i = 0; i < adj.length; i++) {
        	for(int edge: adj[i]) {
                graph.get(i).add(edge);
                graph.get(edge).add(i);
            }
        }
        
        int[] colors = new int[numVertex];
        Arrays.fill(colors, -1);
        
        for(int i = 0; i < numVertex; i++) {
            if(colors[i] == -1 && !dfs(graph, colors, i, 0)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean dfs(Map<Integer, List<Integer>> graph, int[] colors, int vertex, int color) {
        if(colors[vertex] != -1) {
            return colors[vertex] == color;
        }
        colors[vertex] = color;
        
        for(int neigh : graph.getOrDefault(vertex, new ArrayList<>())) {
            if(!dfs(graph, colors, neigh, 1 - color)) {
                return false;
            }
        }
        return true;
    }
}
