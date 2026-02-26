package org.example;

import java.util.Scanner;

public class Task3nPlus1_Vus {
    private static final int LIMIT = 1000000;
    private static final int[] cache = new int[LIMIT];

    public static int algoLength(int n){
        if(n == 1) return 1;
        if(n < LIMIT && cache[n] != 0){ return cache[n]; }

        int length;
        if(n % 2 != 0){
            length = 1 + algoLength(3*n +1);
        } else {
            length = 1 + algoLength(n/2);
        }

        if(n < LIMIT){ cache[n] = length; }

        return length;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int firstNumber = in.nextInt();
            int secondNumber = in.nextInt();
            int n = Math.min(firstNumber, secondNumber);
            int m = Math.max(firstNumber, secondNumber);
            int maxLength = 0;
            for(int i = n; i <= m; i++){
                maxLength = Math.max(maxLength, algoLength(i));
            }
            System.out.printf("%d %d %d\n", firstNumber, secondNumber, maxLength);
        }
    }
}
