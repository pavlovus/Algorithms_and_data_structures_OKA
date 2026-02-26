package org.example;

import java.util.LinkedList;

public class sortLinkedListAsc {
    public void sortAsc(LinkedList<Comparable> list) {
        if (list == null || list.isEmpty()) return;

        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++)
                if (list.get(j).compareTo(list.get(minIndex)) < 0) minIndex = j;

            if (minIndex != i) {
                Comparable temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }
}