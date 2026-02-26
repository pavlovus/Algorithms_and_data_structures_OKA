package org.example;

import java.util.*;

public class PhoneList_Vus {
    public static void   main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            String[] arr = new String[n];
            for (int j = 0; j < n; j++) arr[j] = sc.next();
            if (checkForCompatibility(arr)) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static boolean checkForCompatibility(String[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            String curr = arr[i];
            String next = arr[i + 1];
            if (next.startsWith(curr)) return false;
        }
        return true;
    }
}