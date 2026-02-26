package org.example;

import java.util.List;
import java.util.Random;

public class Labyrinth {
    Random rand = new Random();
    int start;
    int end;

    public Labyrinth(In in) {
        Graph_Vus G = new Graph_Vus(in);
        start = 0;
        end = rand.nextInt(1, G.V());

        DepthFirstPaths_Vus dfs = new DepthFirstPaths_Vus(G, start, end);
        System.out.println("DFS:");
        if(dfs.hasPathTo(end)) {
            printPath(dfs.pathTo(end));
            System.out.println("К-сть відвіданих вузлів " + dfs.getCount());
        }
        else System.out.println("Схоже, що шляху, добратись до точки " + end + " немає!");

        BreadthFirstPaths_Vus bfs = new BreadthFirstPaths_Vus(G, start, end);
        System.out.println("BFS:");
        if(bfs.hasPathTo(end)){
            printPath(bfs.pathTo(end));
            System.out.println("К-сть відвіданих вузлів " + bfs.getCount());
        }
        else System.out.println("Схоже, що шляху, добратись до точки " + end + " немає!");
    }

    public void printPath(List<Integer> path) {
        for (int x : path)
            if (x == start) System.out.print(x);
            else        System.out.print("-" + x);

        System.out.println();
        System.out.println("Довжина шляху: " + (path.size() - 1));
    }

    public static void main(String[] args) {
        In in = new In("/Users/pavlovus/Desktop/OKA/Practice11/src/main/java/org/example/test100.txt");
        Labyrinth lab = new Labyrinth(in);
    }
}