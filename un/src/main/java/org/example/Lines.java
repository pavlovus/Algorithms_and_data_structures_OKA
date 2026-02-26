package org.example;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Lines {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        char[][] grid = new char[n][n];
        Cell start = null;
        Cell finish = null;
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == '@') start = new Cell(i, j);
                if (grid[i][j] == 'X') finish = new Cell(i, j);
            }
        }
        boolean result = solve(grid, n, start, finish);
        if (result) {
            System.out.println("Y");
            for (int i = 1; i < n; i++)
                System.out.println(new String(grid[i]));
        } else {
            System.out.println("N");
        }
    }

    private static boolean solve(char[][] grid, int n, Cell start, Cell finish) {
        boolean[][] visited = new boolean[n][n];
        Cell[][] parent = new Cell[n][n];
        Queue<Cell> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Cell cur = queue.poll();

            if (cur.x == finish.x && cur.y == finish.y) {
                // Відновлюємо шлях
                markPath(grid, parent, start, finish);
                return true;
            }

            for (int k = 0; k < 4; k++) {
                int nr = cur.x + dx[k];
                int nc = cur.y + dy[k];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;
                if (grid[nr][nc] == 'O' || grid[nr][nc] == '@') continue;

                visited[nr][nc] = true;
                parent[nr][nc] = cur;
                queue.add(new Cell(nr, nc));
            }
        }

        return false;
    }

    private static void markPath(char[][] grid, Cell[][] parent, Cell start, Cell finish) {
        Cell cur = finish;

        while (cur != null && !(cur.x == start.x && cur.y == start.y)) {
            if (grid[cur.x][cur.y] == '.' || grid[cur.x][cur.y] == 'X') grid[cur.x][cur.y] = '+';
            cur = parent[cur.x][cur.y];
        }
        grid[start.x][start.y] = '+';
    }

    private static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}