package org.example;

import java.util.Arrays;

public class isPermutation {
    public boolean isPermutation (String firstString, String secondString) {
        if (firstString == null || secondString == null) return false;


        if (firstString.length() != secondString.length()) return false;


        char[] first = firstString.toCharArray();
        char[] second = secondString.toCharArray();


        Arrays.sort(first);
        Arrays.sort(second);


        for (int i = 0; i < first.length; i++)
            if (first[i] != second[i]) return false;

        return true;
    }
}