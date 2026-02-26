package org.example;

import java.util.TreeSet;

public class Digraph_Vus {
	private final int V;
	private TreeSet<Integer>[] adj;

	public Digraph_Vus(int V){
		this.V=V;
		adj = new TreeSet[V];
		for (int v=0; v<V; v++)
			adj[v] = new TreeSet<Integer>();
	}

    public Digraph_Vus(In in){
        this(in.readInt());
        int edgesCount = in.readInt();
        for(int i = 0; i < edgesCount; i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }

	public void addEdge(int v, int w){ adj[v].add(w); }

	public int degree(int v){
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}

	public Iterable<Integer> adj(int v){
		return adj[v];
	}

    public int V() { return V; }
}