package org.example;

import java.util.PriorityQueue;

public class SumOfCubes {
    private final PriorityQueue<Pair> pq;
    private final int n;

    private static class Pair implements Comparable<Pair> {
        int a;
        int b;
        long sum;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
            sum = (long)a*a*a + (long) b*b*b;
        }

        @Override
        public int compareTo(Pair o) { return Long.compare(sum, o.sum); }
    }

    public SumOfCubes(int n){
        this.n = n;
        pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++)
            pq.add(new Pair(i, i));

        printAllSumsSorted();
    }

    private void printAllSumsSorted() {
        System.out.println("Для N = " + n);
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            System.out.println(p.sum + " = " + p.a + "^3 + " + p.b + "^3");
            Pair newPair = new Pair(p.a, p.b + 1);
            if(newPair.b <= n) pq.add(newPair);
        }
    }

    public  static void main(String[] args) {
        SumOfCubes sum = new SumOfCubes(10);
    }
}