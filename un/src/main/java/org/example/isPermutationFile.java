package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class isPermutationFile {
    public boolean isPermutationFile(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            if (firstLine == null || secondLine == null) return false;

            if (firstLine.length() != secondLine.length()) return false;

            char[] chars = firstLine.toCharArray();
            char[] chars2 = secondLine.toCharArray();

            Arrays.sort(chars);
            Arrays.sort(chars2);

            for (int i = 0; i < chars.length; i++)
                if (chars[i] != chars2[i]) return false;

            return true;
        } catch (IOException e) {
            System.out.println("Щось пішло не так при зчитуванні файлу");
            return false;
        }
    }
}