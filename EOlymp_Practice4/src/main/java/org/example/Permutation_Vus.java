package org.example;

import java.util.Scanner;

public class Permutation_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] arr = new boolean[n+1];
        for (int i = 0; i < n; i++){
            int x = sc.nextInt();
            if(x <= n) arr[x] = true;
        }
        System.out.println(permutation(arr));
    }

    private static int permutation(boolean[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (!arr[i]) return i;
        return 0;
    }
}
