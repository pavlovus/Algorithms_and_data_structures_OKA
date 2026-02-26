package org.example;

import org.example.princeton.lib.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solver_Vus {
    private Board_Vus board;
    private int moves;
    private List<Board_Vus> solution;

    // знайти рішення для дошки initial
    public Solver_Vus(Board_Vus initial){
        this.board = initial;
        solve();
    }

    private void solve() {
        if(!isSolvable()){
            moves = -1;
            solution = null;
            return;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(board, 0, null));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.board.isGoal()){
                moves = node.moves;
                solution = node.getPath();
                return;
            }
            for(Board_Vus neighbour : node.board.neighbors()){
                if(node.previous != null && neighbour.equals(node.previous.board)) continue;
                pq.add(new Node(neighbour, node.moves + 1, node));
            }
        }
    }

    // чи має початкова дошка розв’язок
    public boolean isSolvable(){
        int count = 0;
        int n = board.dimension();
        int[] arr = new int[n * n - 1];
        int index = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(board.getBoard()[i][j] != 0) arr[index++] = board.getBoard()[i][j];

        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] > arr[j]) count++;

        return count % 2 == 0;
    }

    // мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
    public int moves(){ return this.moves; }

    // послідовність дошок в найкоротшому рішенні; null якщо немає рішення
    public Iterable<Board_Vus> solution(){ return this.solution; }

    private class Node implements Comparable<Node> {
        private Board_Vus board;
        private int moves;
        private Node previous;
        private int priority;

        public Node(Board_Vus board, int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = moves + board.manhattan();
        }

        public List<Board_Vus> getPath(){
            List<Board_Vus> path = new ArrayList<>();
            for(Node n = this; n != null; n = n.previous)
                path.addFirst(n.board);
            return path;
        }

        @Override
        public int compareTo(Node that) { return Integer.compare(this.priority, that.priority); }
    }

    public static void main(String[] args){
        // створюємо початкову дошку з файлу
        try {
            Scanner in = new Scanner(new File("/Users/pavlovus/Desktop/OKA/Practice9/src/main/java/org/example/PazlTestFiles/puzzle31.txt"));
            int N = in.nextInt();
            int[][] blocks = new int[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    blocks[i][j] = in.nextInt();
            Board_Vus initial = new Board_Vus(blocks);

            // розв’язати
            Solver_Vus solver = new Solver_Vus(initial);

            // надрукувати рішення
            if (!solver.isSolvable())
                System.out.println("Дошка не має розв’язку");
            else {
                StdOut.println("Мінімальна кількість кроків = " + solver.moves());
                for (Board_Vus board : solver.solution())
                    System.out.println(board);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}