package org.example;

import java.util.LinkedList;

public class Degrees_Vus {
    Digraph G;

    public Degrees_Vus(Digraph G){ this.G = G; }

    public int outdegree(int v){
        int outdegree = 0;
        for (int w : G.adj(v))
            outdegree++;
        return outdegree;
    }

    public int indegree(int v){
        int indegree = 0;
        for(int i = 0; i < G.V(); i++)
                for(int w : G.adj(i))
                    if(w == v) indegree++;

        return indegree;
    }

    public Iterable<Integer> sources(){
        LinkedList<Integer> sources = new LinkedList<>();
        for(int v = 0; v < G.V(); v++)
            if(indegree(v) == 0)  sources.add(v);
        return sources;
    }

    public Iterable<Integer> sinks(){
        LinkedList<Integer> sinks = new LinkedList<>();
        for(int v = 0; v < G.V(); v++)
            if(outdegree(v) == 0)  sinks.add(v);
        return sinks;
    }

    public boolean isMap(){
        for(int v = 0; v < G.V(); v++) if(outdegree(v) != 1)  return false;
        return true;
    }

    public static void main(String[] args){
        Digraph G = new Digraph(4);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 0);
        Degrees_Vus degrees = new Degrees_Vus(G);

        for (int v = 0; v < G.V(); v++)
            System.out.println("Outdegree " + v + " = " + degrees.outdegree(v));
        System.out.println();

        for (int v = 0; v < G.V(); v++)
            System.out.println("indegree " + v + " = " + degrees.indegree(v));
        System.out.println();

        System.out.println("Sources:");
        for (int v : degrees.sources())
            System.out.println(v);
        System.out.println();

        System.out.println("Sinks:");
        for (int v : degrees.sinks())
            System.out.println(v);
        System.out.println();

        System.out.println("IsMap: " + degrees.isMap());
    }
}