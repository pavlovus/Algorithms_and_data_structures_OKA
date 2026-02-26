package org.example;

import java.util.*;

public class DestructionOfRoads_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n  = sc.nextInt();
            int[][] v = new int[n][n];

            for (int i = 0; i < n; i++) {
                String s = sc.next();
                for (int j = 0; j < n; j++) {
                    int w = s.charAt(j) - '0';
                    v[i][j] = w;
                    v[j][i] = w;
                }
            }

            System.out.println(findMinCut(v));
        }
    }

    public static int findMinCut(int[][] w) {
        int minCut = Integer.MAX_VALUE;
        int n = w.length;

        int[] vertices = new int[n];
        for (int i = 0; i < n; i++) vertices[i] = i;

        int currN = n;
        while (currN > 1) {
            boolean[] added = new boolean[n];
            int[] weight = new int[n];

            int prevVertice = -1;
            for (int i = 0; i < currN; i++) {
                int maxWeightVertice = -1;
                for (int j = 0; j < currN; j++) {
                    int v = vertices[j];
                    if (!added[v] && (maxWeightVertice == -1 || weight[v] > weight[maxWeightVertice])) maxWeightVertice = v;
                }

                added[maxWeightVertice] = true;
                if (i == currN - 1) {
                    minCut = Math.min(minCut, weight[maxWeightVertice]);
                    for (int j = 0; j < n; j++) {
                        w[prevVertice][j] += w[maxWeightVertice][j];
                        w[j][prevVertice] = w[prevVertice][j];
                    }

                    int pos = -1;
                    for (int j = 0; j < currN; j++) if (vertices[j] == maxWeightVertice) pos = j;
                    vertices[pos] = vertices[currN - 1];
                    currN--;
                } else {
                    prevVertice = maxWeightVertice;
                    for (int j = 0; j < currN; j++) {
                        int v = vertices[j];
                        if (!added[v]) weight[v] += w[maxWeightVertice][v];
                    }
                }
            }
        }
        return minCut;
    }
}