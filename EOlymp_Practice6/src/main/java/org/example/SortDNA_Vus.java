package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class SortDNA_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int len = sc.nextInt();
            int num = sc.nextInt();
            DNA[] arr = new DNA[num];

            for (int j = 0; j < num; j++)
                arr[j] = new DNA(sc.next(), len);

            Arrays.sort(arr);

            for(DNA dna:arr)
                System.out.println(dna.toString());

            if(i!=n-1) System.out.println();
        }
    }

    private static class DNA implements Comparable<DNA>{
        private String value;
        private int length;
        private int numOfInversions;

        public DNA(String value, int length ) {
            this.value = value;
            this.length = length;
            numOfInversions = calcNumOfInversions();
        }

        private int calcNumOfInversions() {
            int count = 0;
            for(int i = 0; i < length; i++){
                char ch = value.charAt(i);

                for(int j = i+1; j < length; j++)
                    if(ch > value.charAt(j)) count++;
            }
            return count;
        }

        @Override
        public int compareTo(DNA o) {
            return Integer.compare(this.numOfInversions, o.numOfInversions);
        }

        @Override
        public String toString() {
            return value;
        }
    }
}