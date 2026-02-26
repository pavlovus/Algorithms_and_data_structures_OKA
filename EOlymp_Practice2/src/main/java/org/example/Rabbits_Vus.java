package org.example;

import java.util.Scanner;

public class Rabbits_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.println(calculateNumberOfRabbits(n, m));
    }

    private static int calculateNumberOfRabbits(int n, int m) {
        int currentNumberOfRabbits = 1;
        for (int i = 1; i <= n; i++) {
            if (currentNumberOfRabbits > m) {
                currentNumberOfRabbits -= m;
            }
            currentNumberOfRabbits = currentNumberOfRabbits * 2;
        }
        return currentNumberOfRabbits;
    }
}
