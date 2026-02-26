package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class isListSortedDesc {
    public boolean isSortedDes(LinkedList<Comparable> list) {
        if (list == null) throw new NullPointerException();
        if (list.isEmpty()) return true;

        Iterator<Comparable> iterator = list.iterator();
        Comparable previous = iterator.next();

        while (iterator.hasNext()) {
            Comparable current = iterator.next();
            if (previous.compareTo(current) < 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}