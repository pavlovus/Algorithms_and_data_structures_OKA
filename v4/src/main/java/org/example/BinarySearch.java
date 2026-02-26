package org.example;

public class BinarySearch {
    public static int binarySearch(Comparable[] array, Comparable element) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmp = element.compareTo(array[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}