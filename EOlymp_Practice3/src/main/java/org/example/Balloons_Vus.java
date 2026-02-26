package org.example;

import java.util.Scanner;

public class Balloons_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] balloons = new int[n];
        for (int i = 0; i < n; i++)
            balloons[i] = in.nextInt();
        System.out.println(minBallsToPaint(balloons));
    }

    private static int minBallsToPaint(int[] balloons) {
        int[] colors  = new int[9];
        for (int ball : balloons)
            colors[ball-1]++;
        return sum(colors) - max(colors);
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int n : arr)
            sum += n;
        return sum;
    }

    private static int max(int[] arr) {
        int max = 0;
        for (int n : arr)
            if (n > max)  max = n;
        return max;
    }
}
