package org.example;

import java.io.*;
import java.util.*;

public class CompoundWords {
    //Сет для зберігання звичайних слів(без повторів, бо це сет)
    Set<String> words;

    public CompoundWords(File file) {
        //Зчитуємо файл по рядку
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            words = new TreeSet<>();

            String line;
            while ((line = reader.readLine()) != null) {
                //Розділяємо кожен рядок на окремі токени
                StringTokenizer tokenizer = new StringTokenizer(line);
                for (int i = 0; i < tokenizer.countTokens(); i++) words.add(tokenizer.nextToken());
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні файлу");
        }
    }

    //Метод, який знаходить складень слова і повертає сет з ними
    public Set<String> findCompoundWords() {
        Set<String> compoundWords = new TreeSet<>();
        ArrayList<String> wordList = new ArrayList<>(words);

        //Якщо слово є в списку всіх слів і складається з двох інших слів з цього списку, то воно - складене
        for (int i = 0; i < wordList.size(); i++)
            for (int j = 0; j < wordList.size(); j++) {
                String compound = wordList.get(i) + wordList.get(j);
                if (wordList.contains(compound)) compoundWords.add(compound);
            }

        return compoundWords;
    }

    //Тестування: в fileToRead - шлях до файлу зі словами, запускамо main і виводяться всі сккладені слова
    public static void main(String[] args) throws IOException {
        File fileToRead = new File("/Users/pavlovus/Desktop/test/KR_Vus/src/main/java/org/example/test.txt");
        CompoundWords cw = new CompoundWords(fileToRead);
        System.out.println(cw.findCompoundWords());
    }
}