package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Concordance {
    TreeMap<String, TreeSet<Integer>> map;

    public Concordance(File file){
        map = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null){
                count++;
                StringTokenizer st = new StringTokenizer(line, " ,.!?;:-()[]\"'{}|/");
                while(st.hasMoreTokens()){
                    String token = st.nextToken();
                    if(map.containsKey(token)) map.get(token).add(count);
                    else{
                        TreeSet<Integer> set = new TreeSet<>();
                        set.add(count);
                        map.put(token, set);
                    }
                }
            }
            printConcordance();
        } catch (IOException e) {
            System.err.println("Щось пішло не так при зчитуванні файлу");
        }
    }

    private void printConcordance() {
        for(String token : map.keySet()){
            System.out.print(token + " : ");
            TreeSet<Integer> set = map.get(token);
            for(Integer i : set)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}