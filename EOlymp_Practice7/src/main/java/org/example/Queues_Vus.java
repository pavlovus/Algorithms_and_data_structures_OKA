package org.example;

import java.util.*;

public class Queues_Vus {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();

        if(n <= k){
            System.out.println(Arrays.stream(arr).max().getAsInt());
            return;
        }

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++)
            pq.add((long) arr[i]);

        for (int i = k; i < n; i++){
            long cur = pq.poll();
            pq.add(cur + arr[i]);
        }

        System.out.println(Collections.max(pq));
    }
}