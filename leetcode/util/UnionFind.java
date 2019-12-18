public class UnionFind {

	// Represent the number of elements in Union-Find
	private int size;

	// Used to track the size of each component
	private int[] sz;

	// id[i] represent the parent of i. If id[i] == i then i in the root node of that Union
	private int[] id;

	private int numComponents;

	public UnionFind(int size) {

		this.size = size;
		this.numComponents = size;
		sz = new int[size];
		id = new int[size];
		
		for(int i = 0; i < size; i++) {
			id[i] = i; // Link to itself, self loop
			sz[i] = 1; // Each component is originally of size 1
		}
	}

	// Finds which component/set, p belongs to
	public int find(int p) {
		int root = p;
		while(root != id[root]) {
			root = id[root];
		}

		// compress the path leading back to root
		// This is "path compression"

		while(p != root) {
			int next = id[p];
			id[p] = root;
			p = next;
		}

		return root;
	}

	// returns true if p and q are connected
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	// returns the size of component set where 'p' belongs
	public int componentSize(int p) {
		return sz[find(p)];
	}

	// Merge smaller components into larger ones
	public void unify(int p, int q) {
		int rootp = find(p);
		int rootq = find(q);

		if(rootp == rootq) return;

		if(sz[rootp] >= sz[rootq]) {
			sz[rootp] += sz[rootq];
			id[rootq] = rootp;
		} else {
			sz[rootq] += sz[p];
			id[rootp] = rootq;
		}
	}

}