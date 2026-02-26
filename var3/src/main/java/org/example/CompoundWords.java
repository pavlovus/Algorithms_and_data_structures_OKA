package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CompoundWords {
    Set<String> compounds;

    public CompoundWords(Set<String> words) {
        compounds = new TreeSet<>();
        ArrayList<String> wordList = new ArrayList<>(words);
        HashSet<String> wordSet = new HashSet<>(words);
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < wordList.size(); j++) {
                String compound = wordList.get(i) + wordList.get(j);
                if (wordSet.contains(compound)) compounds.add(compound);
            }
        }
    }

    public void printWords() {
        if (!compounds.isEmpty())
            for (String compound : compounds)
                System.out.println(compound);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введіть ваші слова: ");
        TreeSet<String> words = new TreeSet<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) break;
            words.add(line.trim());
        }
        CompoundWords compoundWords = new CompoundWords(words);
        compoundWords.printWords();
    }
}