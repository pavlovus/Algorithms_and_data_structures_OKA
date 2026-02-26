package org.example;

import java.util.Comparator;

public class QuickSort {

    public static void sort(Comparable[] a) { sort(a, 0, a.length - 1); }

    public static void sort(Comparable[] a, Comparator comp) {
        sort(a, 0, a.length - 1, comp);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void sort(Comparable[] a, int lo, int hi, Comparator comp) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi, comp);
        sort(a, lo, j - 1, comp);
        sort(a, j + 1, hi, comp);
    }

    private static int partition(Comparable[] a, int lo, int hi, Comparator comp) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v, comp)) if (i == hi) break;
            while (less(v, a[--j], comp)) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w, Comparator comp) {
        return comp.compare(v, w) < 0;
    }
}
