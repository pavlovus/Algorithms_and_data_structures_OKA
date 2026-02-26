package org.example;

import java.util.ArrayList;
import java.util.List;

public class EulerianGraph_Vus {
    private final int V;
    private Bag<Integer>[] adj;
    private Point[] points;

    public EulerianGraph_Vus(int V, Point[] points) {
        if(points.length != V) throw new IllegalArgumentException();
        this.V=V;
        adj = new Bag[V];
        for (int v=0; v<V; v++)
            adj[v] = new Bag<>();
        this.points = points;
        //createEulerianGraph();
    }

    public void addEdge(int v, int w){
        if(v<0 || v>=V || w<0 || w>V) throw new IllegalArgumentException();
        adj[v].add(w);
        adj[w].add(v);
    }

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

    public void show(){
        StdDraw.setPenColor(StdDraw.BLUE);
        for(int v = 0; v < V; v++)
            for(int w : adj(v))
                if(v < w) StdDraw.line(points[v].getX(), points[v].getY(), points[w].getX(), points[w].getY());

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        for(Point p : points){
            StdDraw.point(p.getX(), p.getY());
        }
    }

    private void createEulerianGraph() {
        for (int i = 0; i < V; i++) addEdge(i, (i + 1) % V);

        List<Integer> odd = new ArrayList<>();
        for (int i = 0; i < V; i++) if (adj[i].size() % 2 != 0) odd.add(i);

        while (!odd.isEmpty()) {
            int v = odd.removeFirst();
            int w = odd.removeFirst();
            addEdge(v, w);
        }
    }

    public static void main(String[] args) {
        Point[] points = new Point[4];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 1);
        points[2] = new Point(1, 0);
        points[3] = new Point(1, 1);
        EulerianGraph_Vus g = new EulerianGraph_Vus(4, points);

        g.addEdge(0, 1);
        g.show();
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);

        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
    }
}