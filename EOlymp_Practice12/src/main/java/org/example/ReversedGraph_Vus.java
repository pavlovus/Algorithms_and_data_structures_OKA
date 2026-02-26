package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ReversedGraph_Vus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line.trim());
        LinkedList<Integer>[] arr = new LinkedList[n];

        for (int i = 0; i < n; i++) arr[i] = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            line = br.readLine();
            if(line != null && !line.isEmpty()) {
                StringTokenizer st = new StringTokenizer(line);
                while(st.hasMoreTokens()) arr[Integer.parseInt(st.nextToken()) - 1].add(i);
            }
        }

        printResult(arr);
    }

    private static void printResult(LinkedList<Integer>[] arr) {
        System.out.println(arr.length);
        for (LinkedList<Integer> l : arr) {
            if (l.isEmpty()) System.out.println();
            else {
                for (Integer i : l) System.out.print(i + " ");
                System.out.println();
            }
        }
    }
}