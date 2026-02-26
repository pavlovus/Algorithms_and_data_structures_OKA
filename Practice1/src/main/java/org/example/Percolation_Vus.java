package org.example;

public class Percolation_Vus {

    private final boolean[][] grid;
    private final QuickFindUF uf;
    private final int n;
    private int numberOfOpen;
    private final int top;
    private final int bottom;

    public Percolation_Vus(int n){
        if (n < 0) throw new IllegalArgumentException();
        this.n = n;
        this.grid = new boolean[n][n];
        this.uf = new QuickFindUF(n * n + 2);
        this.top = n * n;
        this.bottom = n * n + 1;
        this.numberOfOpen = 0;
    }

    private int id(int i, int j){
        return i * n + j;
    }

    public int getOpenedCount(){ return numberOfOpen; }

    public void  open(int i, int j){
        if (i < 0 || i > n || j < 0 || j > n) throw new IllegalArgumentException();
        if (!grid[i][j]){
            grid[i][j] = true;

            int currentId = id(i, j);

            if(i == 0) {uf.union(currentId, top);}
            if(i == n-1) {uf.union(currentId, bottom);}

            if(i > 0 && isOpened(i - 1, j)){ uf.union(currentId, id(i - 1,j)); }
            if(i < n - 1 && isOpened(i + 1, j)){ uf.union(currentId, id(i + 1,j)); }
            if(j > 0 && isOpened(i, j - 1)){ uf.union(currentId, id(i,j - 1)); }
            if(j < n - 1 && isOpened(i, j + 1)){ uf.union(currentId, id(i,j + 1)); }

            numberOfOpen++;
        }
    }

    public boolean isOpened(int i, int j){
        if (i < 0 || i > n || j < 0 || j > n) throw new IllegalArgumentException();
        return grid[i][j];
    }

    public boolean percolates(){
        return uf.connected(top,bottom);
    }
}
