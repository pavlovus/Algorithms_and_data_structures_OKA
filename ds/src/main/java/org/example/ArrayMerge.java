package org.example;

public class ArrayMerge {
    public static Comparable[] merge(Comparable[] first, Comparable[] second) {
        if (first == null || second == null) throw new NullPointerException();
        int m = first.length;
        if (m == 0) return second;
        int n = second.length;
        if (n == 0) return first;
        Comparable[] result = new Comparable[m+n];
        int i = 0;
        int j = 0;
        for (int k = 0; k < m + n; k++) {
            if (i >= m) {
                result[k] = second[j++];
            } else if (j >= n) {
                result[k] = first[i++];
            } else if (second[j].compareTo(first[i]) < 0) {
                result[k] = second[j++];
            } else {
                result[k] = first[i++];
            }
        }
        return result;
    }
}
