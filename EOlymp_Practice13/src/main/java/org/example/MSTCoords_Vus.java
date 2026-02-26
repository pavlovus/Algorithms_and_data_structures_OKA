package org.example;

import java.util.*;

public class MSTCoords_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        boolean[] marked = new boolean[n];
        double[] dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
        double res = 0;

        for (int i = 0; i < n; i++) {
            int min = -1;
            for (int j = 0; j < n; j++)
                if (!marked[j]) {
                    min = j;
                    break;
                }

            for (int j = min + 1; j < n; j++) if (!marked[j] && dist[j] < dist[min]) min = j;
            marked[min] = true;
            res += dist[min];

            for (int j = 0; j < n; j++)
                if (!marked[j]){
                    double newDist = Math.sqrt((x[j] - x[min]) * (x[j] - x[min]) + (y[j] - y[min]) * (y[j] - y[min]));
                    if (newDist < dist[j]) dist[j] = newDist;
                }
        }

        System.out.println(res);
    }
}