package org.example;

public class Digraph {
	private final int V;
	private Bag<Integer>[] adj;

	public Digraph(int V){
		this.V=V;
		adj = new Bag[V];
		for (int v=0; v<V; v++)
			adj[v] = new Bag<>();
	}

	public void addEdge(int v, int w){ adj[v].add(w); }

    public int outdegree(int v){
        return adj[v].size();
    }

    public int indegree(int v){
        int indegree = 0;
        for(int i = 0; i < V(); i++)
            for(int w : adj(i))
                if(w == v) indegree++;

        return indegree;
    }

	public Iterable<Integer> adj(int v){
		return adj[v];
	}

    public int V() { return V; }

    public Digraph reverse() {
        Digraph g = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v)) g.addEdge(w, v);
        return g;
    }
}