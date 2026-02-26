package org.example;

import java.math.BigInteger;
import java.util.Scanner;

public class Evolution_Vus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        BigInteger m = sc.nextBigInteger();
        BigInteger k = sc.nextBigInteger();
        System.out.println(findFirstCommonAncestor(m, k));
    }

    private static BigInteger findFirstCommonAncestor(BigInteger firstNumber, BigInteger secondNumber) {
        while (!firstNumber.equals(secondNumber)) {
            if (firstNumber.compareTo(secondNumber) > 0) firstNumber = firstNumber.divide(BigInteger.valueOf(2));
            else secondNumber = secondNumber.divide(BigInteger.valueOf(2));
        }
        return firstNumber;
    }
}