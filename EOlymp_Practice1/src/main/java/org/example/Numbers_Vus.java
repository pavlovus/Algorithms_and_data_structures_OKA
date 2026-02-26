package org.example;

import java.util.Scanner;

public class Numbers_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int count = 0;
        do {
            count++;
            number /= 10;
        } while (number > 0);
        System.out.println(count);
    }
}
