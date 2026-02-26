package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstPaths_Vus {
	private boolean[] marked;    
    private int[] edgeTo;        
    private final int s;
    private int v;
    private int count;
    
    public DepthFirstPaths_Vus(Graph_Vus G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DepthFirstPaths_Vus(Graph_Vus G, int s, int v) {
        this.s = s;
        this.v = v;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s, v);
    }

    private void dfs(Graph_Vus G, int s) {
        Stack<Integer> q = new Stack<>();
        q.push(s);
        marked[s] = true;
        count++;
        while (!q.isEmpty()) {
            int v = q.pop();
            for (int w : G.adj(v))
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    count++;
                }
        }
    }

    private void dfs(Graph_Vus G, int s, int v) {
        Stack<Integer> q = new Stack<>();
        q.push(s);
        marked[s] = true;
        count++;
        while (!q.isEmpty()) {
            int currV = q.pop();
            if (v == currV) return;
            for (int w : G.adj(currV))
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = currV;
                    count++;
                }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            stack.push(x);
        stack.push(s);

        List<Integer> path = new LinkedList<>();
        while (!stack.isEmpty())
            path.add(stack.pop());
        return path;
    }

    public int getCount() { return count; }
}