package org.example;

import java.util.Scanner;

public class LostCard_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] arr = new boolean[n+1];
        for (int i = 0; i < n-1; i++){
            int x = sc.nextInt();
            arr[x] = true;
        }
        System.out.println(findLostCard(arr));
    }

    private static int findLostCard(boolean[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (!arr[i]) return i;
        return 0;
    }
}
