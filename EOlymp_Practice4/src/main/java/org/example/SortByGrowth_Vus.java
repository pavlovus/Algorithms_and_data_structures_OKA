package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SortByGrowth_Vus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);

            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());

            System.out.println(findAllNumbersInInterval(arr, min, max));
        }
    }

    private static int findAllNumbersInInterval(int[] arr, int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : arr)
            if (num >= min && num <= max) list.add(num);
        return list.size();
    }
}
