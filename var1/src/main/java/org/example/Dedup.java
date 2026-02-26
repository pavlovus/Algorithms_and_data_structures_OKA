package org.example;

import java.util.TreeSet;

public class Dedup {
    public String[] dedup(String[] array) {
        if (array == null || array.length == 0) return new String[0];
        TreeSet<String> set = new TreeSet<>();
        for (String s : array) set.add(s);
        return set.toArray(new String[0]);
    }
}