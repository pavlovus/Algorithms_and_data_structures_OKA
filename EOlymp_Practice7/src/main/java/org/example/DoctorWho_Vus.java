package org.example;

import java.util.Scanner;

public class DoctorWho_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            String[] parts = s.split(" ");

            int[] arr = new int[parts.length];
            for (int i = 0; i < parts.length; i++)
                arr[i] = Integer.parseInt(parts[i]);

            if (checkIfCorrect(arr))
                System.out.println("ok");
            else
                System.out.println("fail");

            System.out.println();
        }
    }

    private static boolean checkIfCorrect(int[] arr) {
        int sum = 0;
        for (int k : arr) {
            sum += k;
            if (k > arr.length - 1)
                return false;
        }

        if((sum % 2 != 0))
            return false;

        for(int i = 1; i <= arr.length; i++){
            int prevSum = 0;
            int aftSum = 0;
            for(int j = 1; j <= i; j++)
                prevSum += arr[j-1];

            for(int j = i+1; j <= arr.length; j++)
                aftSum += arr[j-1];

            if(!(prevSum <= i*(i-1) + aftSum))
                return false;
        }

        return true;
    }
}