package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            robots[i] = new Robot(x, y);
        }

        Arrays.sort(robots);

        for (int i = 0; i < n; i++) {
            System.out.println(robots[i].toString());
        }
    }

    private static class Robot implements Comparable<Robot>{
        private int primaryNumber;
        private int secondaryNumber;

        public Robot(int n, int m){
            this.primaryNumber = n;
            this.secondaryNumber = m;
        }

        @Override
        public int compareTo(Robot r) {
            if (this.primaryNumber < r.primaryNumber) return -1;
            if (this.primaryNumber > r.primaryNumber) return 1;
            return 0;
        }

        public String toString(){
            return primaryNumber + " " + secondaryNumber;
        }
    }
}