package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SearchDictionaryTester_Vus {
    public static void main(String[] args) {
        try {
            SearchDictionary_Vus sd = new SearchDictionary_Vus(new File("/Users/pavlovus/Desktop/OKA/Practice10/src/main/java/org/example/tests/small.txt"));

            List<String> result = (List<String>) sd.query("and");
            if(result.isEmpty()) System.out.println("Схоже, що слів за таким запитом немає...");
            else for (String s : result) System.out.println(s);

            result = (List<String>) sd.query("Th*");
            if(result.isEmpty()) System.out.println("Схоже, що слів за таким запитом немає...");
            else for (String s : result) System.out.println(s);

            /*Scanner sc = new Scanner(System.in);
            System.out.println("Введіть шлях до файлу: ");
            File file = new File(sc.nextLine());
            SearchDictionary dictionary = new SearchDictionary(file);

            while (true) {
                System.out.println("Введіть слово для пошуку(з * в кінці або без) або 0 для виходу: ");
                String query = sc.nextLine().trim();
                if (query.equals("0")) break;

                List<String> result = (List<String>) dictionary.query(query);

                if(result.isEmpty()) System.out.println("Схоже, що слів за таким запитом немає...");
                else for (String s : result) System.out.println(s);
            }*/
        } catch (IOException e) {
            System.err.println("Файл не знайдено!!!");
        }
    }
}