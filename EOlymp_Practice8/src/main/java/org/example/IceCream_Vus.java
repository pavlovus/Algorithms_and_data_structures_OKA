package org.example;

import java.util.Scanner;

public class IceCream_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] coordinates = new int[n];

        for (int i = 0; i < n; i++)
            coordinates[i] = in.nextInt();

        System.out.println(findMaxPossibleDistance(coordinates, k));
    }

    private static int findMaxPossibleDistance(int[] coordinates, int k) {
        int left = 0;
        int right = coordinates[coordinates.length - 1] - coordinates[0];
        int curr = -1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            if(tryToPlaceWithDistance(coordinates, mid, k)){
                left = mid + 1;
                curr = mid;
            } else right = mid-1;
        }

        return curr;
    }

    private static boolean tryToPlaceWithDistance(int[] coordinates, int mid, int k) {
        int count = 1;
        int currentPlaced = coordinates[0];

        for(int i = 1; i < coordinates.length; i++){
            if(coordinates[i] - currentPlaced >= mid){
                count++;
                currentPlaced = coordinates[i];
            }
        }

        return count >= k;
    }
}