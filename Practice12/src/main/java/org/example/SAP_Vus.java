package org.example;

import java.io.File;
import java.util.*;

public class SAP_Vus {
    private Digraph_Vus graph;

    // конструктор приймає орграф (не обов’язково DAG)
    public SAP_Vus(Digraph_Vus G){ this.graph = G; }

    // довжина найкоротшого шляху до спільного батька v та w, -1 якщо такого шляху немає
    public int length(int v, int w){ return length(List.of(v), List.of(w));}

    // спільний батько v та w, з найкоротшого шляху, -1 якщо такого шляху немає
    public int ancestor(int v, int w){ return ancestor(List.of(v), List.of(w)); }

    // довжина найкоротшого шляху між будь-якою вершиною з v та з w; -1 якщо такого шляху немає
    public int length(Iterable<Integer> v, Iterable<Integer> w){ return findMinDistance(v, w); }

    // спільний батько з найкоротшого шляху …; -1 якщо такого шляху немає
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){ return findMinAncestor(v, w); }

    private int findMinAncestor(Iterable<Integer> v, Iterable<Integer> w) {
        int[] distFromV = bfs(graph, v);
        int[] distFromW = bfs(graph, w);

        int min = Integer.MAX_VALUE;
        int minAncestor = -1;
        for (int s = 0; s < graph.V(); s++)
            if (distFromV[s] != -1 && distFromW[s] != -1) {
                int sum = distFromV[s] + distFromW[s];
                if (sum < min) {
                    min = sum;
                    minAncestor = s;
                }
            }

        return minAncestor;
    }

    private int findMinDistance(Iterable<Integer> v, Iterable<Integer> w) {
        int[] distFromV = bfs(graph, v);
        int[] distFromW = bfs(graph, w);

        int min = Integer.MAX_VALUE;
        for (int s = 0; s < graph.V(); s++)
            if (distFromV[s] != -1 && distFromW[s] != -1) {
                int sum = distFromV[s] + distFromW[s];
                if (sum < min) min = sum;
            }

        if (min == Integer.MAX_VALUE) return -1;
        return min;
    }

    private int[] bfs(Digraph_Vus G, Iterable<Integer> s){
        int[] distTo = new int[G.V()];
        boolean[] marked = new boolean[G.V()];
        Arrays.fill(distTo, -1);
        Queue<Integer> q = new LinkedList<>();
        for (int start : s) {
            distTo[start] = 0;
            marked[start] = true;
            q.add(start);
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }

        return distTo;
    }

    public static void main(String[] args){
        In in = new In(new File("/Users/pavlovus/Desktop/OKA/Practice12/src/main/java/org/example/Practice12/digraph1.txt"));
        Digraph_Vus G = new Digraph_Vus(in);
        SAP_Vus sap = new SAP_Vus(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}