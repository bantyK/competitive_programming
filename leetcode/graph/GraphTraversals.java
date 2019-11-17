import java.util.*;

public class GraphTraversals {

	static class Edge {
		final int to;
		final int from;
		final int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	/**
	 * DFS traversal of a graph represented using adjacency list.
	 * @param graph
	 * @param V
	 * @param start
	 */
	public static void dfs(Map<Integer, List<Edge>> graph, int V, int start) {
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		
		while(!stack.isEmpty()) {
			int current = stack.pop();
			visited[current] = true;
			System.out.print(current + " ");

			List<Edge> edges = graph.get(current);
			if(null != edges) {
				for(Edge edge : edges) {
					if(!visited[edge.to]) {
						stack.push(edge.to);
					}
				}
			}
		}
	}

	public static void dfsMatrix(int[][] graph, int V, int start) {
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[V];

		stack.push(start);

		while(!stack.isEmpty()) {
			int current = stack.pop();
			System.out.print(current + " ");
			visited[current] = true;

			for(int i = 0; i < graph[current].length; i++) {
				if(graph[current][i] == 1 && !visited[i]) {
					stack.push(i);
				}
			}
		}

	}

	public static void addEdge(int[][] matrix, int from, int to) {
		matrix[from][to] = 1;
	}

	public static void main(String[] args) {
		final int V = 5;
		Map<Integer, List<Edge>> graph = new HashMap<>();
		graph.put(0, Arrays.asList(new Edge(0,1,0)));
		graph.put(1, Arrays.asList(new Edge(0,2,0)));
		graph.put(2, Arrays.asList(new Edge(1,3,0)));
		graph.put(3, Arrays.asList(new Edge(2,4,0)));
		graph.put(4, Arrays.asList(new Edge(3,4,0)));

		dfs(graph, V, 0);

		int[][] graphMatrix = new int[V][V];
		Arrays.fill(graphMatrix, 0);

		addEdge(graphMatrix, 0, 1);
		addEdge(graphMatrix, 0, 2);
		addEdge(graphMatrix, 1, 3);
		addEdge(graphMatrix, 3, 4);
		addEdge(graphMatrix, 2, 4);

		dfsMatrix(graphMatrix, V, 0);
	}
}
























