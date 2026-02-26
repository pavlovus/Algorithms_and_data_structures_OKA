package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Master_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];
        int[] t = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            t[i] = sc.nextInt();
        }

        int sum = Arrays.stream(a).sum();
        int[] minTime = new int[sum + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[0] = 0;

        fillMinTimeArray(a, t, minTime);

        System.out.println(findMin(minTime, k));
    }

    private static void fillMinTimeArray(int[] pointsArr, int[] timeArr, int[] minTimeArr) {
        for (int i = 0; i < pointsArr.length; i++) {
            int currentPoints = pointsArr[i];
            int currentTime = timeArr[i];

            for (int j = minTimeArr.length -1; j >= 0; j--) {
                if(minTimeArr[j] != Integer.MAX_VALUE) {
                    minTimeArr[j + currentPoints] = Math.min(minTimeArr[j + currentPoints], minTimeArr[j] + currentTime);
                }
            }
        }
    }

    private static int findMin(int[] minTime, int k) {
        if (k == 0) return 0;
        int min = Integer.MAX_VALUE;

        for (int i = k; i < minTime.length; i++) {
            if(minTime[i] < min)
                min = minTime[i];
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}