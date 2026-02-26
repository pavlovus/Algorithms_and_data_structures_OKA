package org.example;

public class Topological {
    private Iterable<Integer> order;
    private int[] rank;

    public Topological(Digraph digraph) {
        if (checkForCycles(digraph)) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph);
            order = dfs.reversePost();
            rank = new int[digraph.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    public Iterable<Integer> order() { return order; }

    public boolean hasOrder() { return order != null; }

    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return rank[v];
        else return -1;
    }

    private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    private boolean checkForCycles(Digraph digraph) {
        boolean[] marked = new boolean[digraph.V()];
        boolean[] currPath = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v] && dfs(digraph, v, marked, currPath)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Digraph g, int v, boolean[] marked, boolean[] currPath) {
        marked[v] = true;
        currPath[v] = true;
        for (int w : g.adj(v))
            if (!marked[w]){ if (dfs(g, w, marked, currPath)) return true; }
            else if (currPath[w]) return true;

        currPath[v] = false;
        return false;
    }
}