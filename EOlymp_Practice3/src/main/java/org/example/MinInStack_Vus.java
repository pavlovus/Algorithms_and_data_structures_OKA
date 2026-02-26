package org.example;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MinInStack_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        long x0 = sc.nextInt();
        System.out.println(minInStack(n, a, b, c, x0));
    }

    private static long minInStack(int n, int a, int b, int c, long x) {
        long sum = 0;
        ArrayDeque<Long> stack = new ArrayDeque<>();
        ArrayDeque<Long> minStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            x = (a*x*x + b*x + c) / 100 % 1000000;
            if((x % 5) < 2){
                if(!stack.isEmpty()) {
                    long peek = stack.pop();
                    if (peek == minStack.peek())  minStack.pop();
                }
            } else {
                stack.push(x);
                if (minStack.isEmpty() || x <= minStack.peek())  minStack.push(x);
            }
            if(!minStack.isEmpty()) sum +=  minStack.peek();
        }
        return sum;
    }
}
