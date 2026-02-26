package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths_Vus {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private final int s;
    private int v;
    private int count;

    public BreadthFirstPaths_Vus(Graph_Vus G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    public BreadthFirstPaths_Vus(Graph_Vus G, int s, int v) {
        this.s = s;
        this.v = v;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        bfs(G, s, v);
    }

    private void bfs(Graph_Vus G, int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        count++;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : G.adj(v))
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    count++;
                }
        }
    }

    private void bfs(Graph_Vus G, int s, int v) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        count++;
        while (!q.isEmpty()) {
            int currV = q.remove();
            if (currV == v) return;
            for (int w : G.adj(currV))
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = currV;
                    distTo[w] = distTo[currV] + 1;
                    count++;
                }
        }
    }

    public boolean hasPathTo(int v) { return marked[v];}

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