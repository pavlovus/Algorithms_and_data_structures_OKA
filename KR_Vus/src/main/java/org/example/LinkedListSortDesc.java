package org.example;

import java.util.LinkedList;

public class LinkedListSortDesc {
    public void sort(LinkedList<Comparable> list) {
        if (list == null || list.size() == 0) return;

        //Сортування вибором
        for (int i = 0; i < list.size(); i++) {
            int max = i;

            for (int j = i + 1; j < list.size(); j++)
                if (list.get(max).compareTo(list.get(j)) < 0) max = j;

            if (max != i) {
                Comparable temp = list.get(i);
                list.set(i, list.get(max));
                list.set(max, temp);
            }
        }
    }

    //Тестування: в масиві testCases вказуємо те, що хотіли би посортувати в необхідному порядку, запускаємо main - виводиться відсортований масив
    public static void main(String[] args) {
        LinkedListSortDesc sorter = new LinkedListSortDesc();
        LinkedList<Comparable> list = new LinkedList<>();
        Comparable[] testCase = {10, 3, 9, 1, 5, 7, 6, 8, 4, 2};
        for (int i = 0; i < testCase.length; i++) list.add(testCase[i]);

        sorter.sort(list);
        System.out.println(list);
    }
}