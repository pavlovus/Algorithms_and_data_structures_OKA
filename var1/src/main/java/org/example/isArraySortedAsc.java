package org.example;

public class isArraySortedAsc {
    public boolean isSortedAsc (Comparable[] a) {
        if (a == null || a.length <= 1) {
            return true;
        }
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (a[i].compareTo(a[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
