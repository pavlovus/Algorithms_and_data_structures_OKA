package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CheckForCyclesGraph_Vus {
    Graph G;
    public CheckForCyclesGraph_Vus(Graph G) { this.G = G; }

    private boolean hasEulerianCycle(){
        for(int v = 0; v < G.V(); v++){
            if(G.degree(v) != 0){
                DepthFirstPaths dfs = new DepthFirstPaths(G, v);
                for (int i = 0; i < G.V(); i++)
                    if(G.degree(i) != 0) if(!dfs.hasPathTo(i)) return false;
                break;
            }
        }
        for(int v = 0; v < G.V(); v++)if(G.degree(v) % 2 == 1) return false;
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
                if(G.degree(v) != 0){
                    stack.push(v);
                    break;
                }

            while(!stack.isEmpty()){
                int v = stack.peek();
                if(!adj[v].isEmpty()){
                    int w = adj[v].removeFirst();
                    stack.push(w);
                    adj[w].remove((Integer) v);
                } else {
                    cycle.addFirst(v);
                    stack.pop();
                }
            }

            return cycle;
        }
        return null;
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

    private boolean checkIfEdgeExist(int v, Integer first) {
        for(int w : G.adj(v))if(w == first) return true;
        return false;
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        testGraph(g1);

        System.out.println();

        Graph g2 = new Graph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        testGraph(g2);

        System.out.println();

        Graph g3 = new Graph(4);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(0, 3);
        g3.addEdge(3, 0);
        testGraph(g3);

        System.out.println();

        Graph g4 = new Graph(5);
        g4.addEdge(0,1);
        g4.addEdge(1,2);
        g4.addEdge(2,3);
        g4.addEdge(3,4);
        g4.addEdge(4,0);
        g4.addEdge(0,2);
        testGraph(g4);
    }

    private static void testGraph(Graph G) {
        CheckForCyclesGraph_Vus checker = new CheckForCyclesGraph_Vus(G);

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