package org.example;

import java.util.Arrays;

public class Bolts {
    private Comparable[] sortedBolts;
    private Comparable[] sortedNuts;

    public Bolts(Comparable[] bolts, Comparable[] nuts) {
        this.sortedBolts = bolts;
        this.sortedNuts = nuts;
        if(bolts.length == nuts.length && bolts.length > 0)
            matchPairs(sortedBolts, sortedNuts, 0, bolts.length-1);
    }

    public void matchPairs(Comparable[] bolts, Comparable[] nuts, int low, int high) {
        if (low < high) {
            int pivot = partition(nuts, low, high, bolts[low]);
            partition(bolts, low, high, nuts[pivot]);

            matchPairs(bolts, nuts, low, pivot - 1);
            matchPairs(bolts, nuts, pivot + 1, high);
        }
    }

    private int partition(Comparable[] arr, int low, int high, Comparable pivot) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                swap(arr, i, j);
                i++;
            } else if (arr[j].equals(pivot)) {
                swap(arr, j, high);
                j--;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void print(){
        System.out.print("Відсортований масив болтів " + Arrays.toString(sortedBolts));
        System.out.println("Відсортовааний масив гайок" + Arrays.toString(sortedNuts));
    }
}