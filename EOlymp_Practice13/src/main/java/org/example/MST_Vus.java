package org.example;

import java.util.*;

public class MST_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        EdgeWeightedGraph graph = new EdgeWeightedGraph(n);
        for(int i = 0; i < m; i++){
            int v = sc.nextInt();
            int w = sc.nextInt();
            int weight = sc.nextInt();
            Edge e = new Edge(v-1, w-1, weight);
            graph.addEdge(e);
        }

        LazyPrimMST mst = new LazyPrimMST(graph);
        System.out.println(mst.weight());
    }

    private static class Edge implements Comparable<Edge> {
        private final int v, w;
        private final int weight;

        public Edge(int v, int w, int weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int either(){
            return v;
        }

        public int other(int vertex){
            if (vertex == v) return w;
            else return v;
        }

        public int compareTo(Edge that)	{
            if (this.weight < that.weight) return -1;
            else if (this.weight > that.weight) return +1;
            else return 0;
        }
    }

    private static class EdgeWeightedGraph {
        private final int V;
        private final List<Edge>[] adj;

        public EdgeWeightedGraph(int V)	{
            this.V = V;
            adj = (ArrayList<Edge>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) adj[v] = new ArrayList<>();
        }

        public void addEdge(Edge e){
            int v = e.either(), w = e.other(v);
            adj[v].add(e);
            adj[w].add(e);
        }

        public Iterable<Edge> adj(int v){ return adj[v]; }
        public int V(){ return V; }
    }

    private static class LazyPrimMST {
        private boolean[] marked; // MST vertices
        private Queue<Edge> mst; // MST edges
        private PriorityQueue<Edge> pq; // PQ of edges

        public LazyPrimMST(EdgeWeightedGraph G)	{
            pq = new PriorityQueue<>();
            mst = new ArrayDeque<>();
            marked = new boolean[G.V()];
            visit(G, 0);
            while (!pq.isEmpty() && mst.size() < G.V() - 1)	{
                Edge e = pq.poll();
                int v = e.either(), w = e.other(v);
                if (marked[v] && marked[w]) continue;
                mst.add(e);
                if (!marked[v]) visit(G, v);
                if (!marked[w]) visit(G, w);
            }
        }

        private void visit(EdgeWeightedGraph G, int v)	{
            marked[v] = true;
            for (Edge e : G.adj(v))
                if (!marked[e.other(v)]) pq.add(e);
        }

        public Iterable<Edge> mst()	{
            return mst;
        }

        int weight() {
            int weight = 0;
            for(Edge e: mst) weight += e.weight;
            return weight;
        }
    }
}