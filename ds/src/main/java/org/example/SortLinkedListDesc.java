package org.example;

import java.util.LinkedList;

public class SortLinkedListDesc {
    public void sortDes(LinkedList<Comparable> list) {
        if (list == null || list.isEmpty()) return;

        for (int i = 0; i < list.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < list.size(); j++)
                if (list.get(j).compareTo(list.get(maxIndex)) > 0) maxIndex = j;

            if (maxIndex != i) {
                Comparable temp = list.get(i);
                list.set(i, list.get(maxIndex));
                list.set(maxIndex, temp);
            }
        }
    }
}