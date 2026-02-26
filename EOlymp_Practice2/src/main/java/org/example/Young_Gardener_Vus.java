package org.example;

import java.util.Scanner;

public class Young_Gardener_Vus {
    private static final int[] cache = new int[1001];

    public static int calculateAmountOfWater(int n){
        if(n == 0) return 1;
        if(cache[n] != 0){ return cache[n]; }

        int amountOfWater = n*2 + calculateAmountOfWater(n-1);

        cache[n] = amountOfWater;

        return amountOfWater;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        System.out.println(calculateAmountOfWater(number));
    }
}
