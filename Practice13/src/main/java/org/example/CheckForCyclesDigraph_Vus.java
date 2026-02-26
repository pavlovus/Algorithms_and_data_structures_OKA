package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CheckForCyclesDigraph_Vus {
    Digraph G;
    public CheckForCyclesDigraph_Vus(Digraph G){ this.G = G; }

    private boolean hasEulerianCycle(){
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
        for (int v = 0; v < G.V(); v++)
            if(!scc.stronglyConnected(0, v)) return false;

        for(int v = 0; v < G.V(); v++)
            if(G.outdegree(v) != G.indegree(v)) return false;
        return true;
    }

    public Iterable<Integer> eulerianCycle() {
        if(hasEulerianCycle()){
            Stack<Integer> stack = new Stack<>();
            LinkedList<Integer> cycle = new LinkedList<>();
            LinkedList<Integer>[] adj = new LinkedList[G.V()];
            for (int v=0; v<G.V(); v++) {
                adj[v] = new LinkedList<>();
                for (int w : G.adj(v)) adj[v].add(w);
            }

            for(int v = 0; v < G.V(); v++)
                if(G.outdegree(v) != 0){
                    stack.push(v);
                    break;
                }

            while(!stack.isEmpty()){
                int v = stack.peek();
                if(!adj[v].isEmpty()){
                    int w = adj[v].removeFirst();
                    stack.push(w);
                } else {
                    cycle.addFirst(v);
                    stack.pop();
                }
            }

            return cycle;
        }
        return null;
    }

    public Iterable<Integer> hamiltonPath(){
        Topological t  = new Topological(G);
        if(hasHamiltonPath(t.order()))
            return t.order();
        return null;
    }

    private boolean hasHamiltonPath(Iterable<Integer> order) {
        Integer prev = null;
        for (int curr : order) {
            if(prev != null)
                if(!checkIfEdgeExist(prev, curr)) return false;
            prev = curr;
        }
        return true;
    }

    private boolean checkIfEdgeExist(int v, Integer first) {
        for(int w : G.adj(v))if(w == first) return true;
        return false;
    }

    public Iterable<Integer> hamiltonCycle(){
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        return hamiltonCycle(0, new LinkedList<>(List.of(0)), visited);
    }

    public List<Integer> hamiltonCycle(int v, List<Integer> path, boolean[] visited) {
        if(path.size() == G.V())
            if(checkIfEdgeExist(v, path.getFirst())){
                path.add(path.getFirst());
                return path;
            } else return null;

        for(int w : G.adj(v))
            if(!visited[w]){
                path.add(w);
                visited[w] = true;
                List<Integer> result = hamiltonCycle(w, path, visited);
                if(result != null) return result;
                path.removeLast();
                visited[w] = false;
            }

        return null;
    }

    public static void main(String[] args) {
        Digraph g1 = new Digraph(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        testDigraph(g1);

        System.out.println();

        Digraph g2 = new Digraph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        testDigraph(g2);

        System.out.println();

        Digraph g3 = new Digraph(5);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 3);
        g3.addEdge(3, 4);
        g3.addEdge(4, 0);
        testDigraph(g3);

        System.out.println();

        Digraph g4 = new Digraph(4);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 3);
        testDigraph(g4);

        System.out.println();

        Digraph g5 = new Digraph(4);
        g5.addEdge(0, 1);
        g5.addEdge(1, 2);
        g5.addEdge(2, 0);
        g5.addEdge(0, 3);
        g5.addEdge(3, 0);
        testDigraph(g5);

        System.out.println();

        Digraph g6 = new Digraph(5);
        g6.addEdge(0,1);
        g6.addEdge(1,2);
        g6.addEdge(2,3);
        g6.addEdge(3,4);
        g6.addEdge(4,0);
        g6.addEdge(0,2);
        testDigraph(g6);
    }

    private static void testDigraph(Digraph G) {
        CheckForCyclesDigraph_Vus checker = new CheckForCyclesDigraph_Vus(G);

        System.out.print("Цикл Ейлера: ");
        Iterable<Integer> euler = checker.eulerianCycle();
        if (euler != null) System.out.println(euler);
        else System.out.println(" - ");

        System.out.print("Цикл Гамільтона: ");
        Iterable<Integer> hamilton = checker.hamiltonCycle();
        if (hamilton != null) System.out.println(hamilton);
        else System.out.println(" - ");
    }
}