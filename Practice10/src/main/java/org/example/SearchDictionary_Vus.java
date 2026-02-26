package org.example;

import java.io.*;
import java.util.*;

public class SearchDictionary_Vus {
    TreeSet<String> dictionary;

    public SearchDictionary_Vus(File file) throws IOException {
        dictionary = new TreeSet<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line, " ,.!?;:-()[]\"'{}|/");
            while(st.hasMoreTokens()) dictionary.add(st.nextToken());
        }
    }

    public void addWord(String word){
        if(word != null && !word.isEmpty()) dictionary.add(word);
    }

    public String delWord(String word){
        if(dictionary.remove(word)) return word;
        return null;
    }

    public boolean hasWord(String word){ return dictionary.contains(word); }

    public Iterable<String> query(String query){
        if(query.endsWith("*")){
            List<String> result = new ArrayList<>();
            String search = query.substring(0, query.length()-1);

            String word = dictionary.ceiling(search);
            while(word != null && word.startsWith(search)){
                result.add(word);
                word = dictionary.higher(word);
            }

            return result;
        } else {
            if(dictionary.contains(query)) return List.of(query);
            return Collections.emptyList();
        }
    }

    public int countWords(){ return dictionary.size(); }
}