package org.example;

import java.util.Scanner;

public class EasyTask_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        System.out.printf("%d %d\n", number/10, number%10);
    }
}
