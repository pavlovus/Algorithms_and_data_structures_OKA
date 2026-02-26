package org.example;

import java.util.Scanner;

public class CheckForDisorientation_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = sc.nextInt();

        if(checkForDisorientation(matrix, n)) System.out.println("YES");
        else System.out.println("NO");
    }

    private static boolean checkForDisorientation(int[][] matrix, int n) {
        for (int i = 0; i < n; i++)
            if(matrix[i][i] != 0) return false;

        for (int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                if(matrix[i][j] != 0 && matrix[i][j] != 1) return false;
                if(matrix[i][j] != matrix[j][i]) return false;
            }

        return true;
    }
}