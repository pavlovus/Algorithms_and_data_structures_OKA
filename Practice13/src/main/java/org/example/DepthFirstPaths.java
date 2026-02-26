package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int v;
    private int count;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DepthFirstPaths(Digraph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
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

    private void dfs(Digraph G, int s) {
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