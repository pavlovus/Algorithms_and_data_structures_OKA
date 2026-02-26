package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board_Vus {
    private int[][] board;
    // конструюємо дошку у вигляді двовимірного масиву N на N
    // (blocks[i][j] =блок в ряду i, колонці j)
    public Board_Vus(int[][] blocks){ board = blocks; }

    // розмірність дошки N
    public int dimension() { return board.length; }

    // кількість блоків не на своєму місці
    public int hamming()  {
        int count = 0;
        int n = board.length;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] != ((i == n - 1 && j == n - 1) ? 0 : i * n + j + 1)) count++;

        return count;
    }

    // сума Манхатенських відстаней між блоками і цільовим станом
    public int manhattan() {
        int count = 0;
        int n = board.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int curr = board[i][j];
                if (curr != 0) {
                    int row = (curr - 1) / n;
                    int col = (curr - 1) % n;
                    count += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }

        return count;
    }

    // чи є ця дошка цільовим станом
    public boolean isGoal(){ return hamming() == 0; }

    // чи ця дошка рівна y?
    public boolean equals(Object y) {
        if(y == null) return false;
        if (y == this) return true;
        if (!(y instanceof Board_Vus)) return false;
        Board_Vus that = (Board_Vus) y;
        if (this.dimension() != that.dimension()) return false;

        for (int i = 0; i < this.dimension(); i++)
            for (int j = 0; j < this.dimension(); j++)
                if (this.board[i][j] != that.board[i][j]) return false;

        return true;
    }

    // всі сусдні дошки
    public Iterable<Board_Vus> neighbors(){
        int n = board.length;
        List<Board_Vus> neighbors = new ArrayList<>(n*n);
        int emptyRow = 0, emptyCol = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(board[i][j] == 0){
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int newRow = emptyRow + dx[k];
            int newCol = emptyCol + dy[k];
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                int[][] copy = new int[n][n];
                for (int i = 0; i < n; i++) copy[i] = board[i].clone();
                copy[emptyRow][emptyCol] = copy[newRow][newCol];
                copy[newRow][newCol] = 0;
                neighbors.add(new Board_Vus(copy));
            }
        }

        return neighbors;
    }

    // строкове подання
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                sb.append(this.board[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int[][] getBoard() { return this.board; }
}