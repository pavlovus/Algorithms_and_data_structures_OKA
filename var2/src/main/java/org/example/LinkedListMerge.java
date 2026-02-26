package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListMerge {
    public LinkedList<Comparable> merge (LinkedList<Comparable> first, LinkedList<Comparable> second) {
        if (first == null || second == null) throw new NullPointerException();
        if (first.isEmpty()) return second;
        if (second.isEmpty()) return first;


        LinkedList<Comparable> result = new LinkedList<>();
        Iterator<Comparable> firstIterator = first.iterator();
        Iterator<Comparable> secondIterator = second.iterator();


        Comparable firstCurrent = firstIterator.next();
        Comparable secondCurrent = secondIterator.next();


        while (true) {
            if (firstCurrent == null && secondCurrent == null) {
                break;
            }
            if (firstCurrent == null) {
                result.addLast(secondCurrent);
                secondCurrent = secondIterator.hasNext() ? secondIterator.next() : null;
            } else if (secondCurrent == null) {
                result.addLast(firstCurrent);
                firstCurrent = firstIterator.hasNext() ? firstIterator.next() : null;
            } else if (secondCurrent.compareTo(firstCurrent) < 0) {
                result.addLast(secondCurrent);
                secondCurrent = secondIterator.hasNext() ? secondIterator.next() : null;
            } else {
                result.addLast(firstCurrent);
                firstCurrent = firstIterator.hasNext() ? firstIterator.next() : null;
            }
        }
        return result;
    }

}