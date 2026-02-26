package org.example;

import java.io.IOException;

public class Outcast_Vus {
    WordNet_Vus wordNet;

    // конструктор приймає об’єкт WordNet
    public Outcast_Vus(WordNet_Vus wordnet){ this.wordNet = wordnet; }

    // маючи масив WordNet іменників, повернути «ізгоя»
    public String outcast(String[] nouns){
        int[] sums = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++){
            int sum = 0;

            for (String noun : nouns)
                if (!nouns[i].equals(noun)) {
                    int dist = wordNet.distance(nouns[i], noun);
                    System.out.println(wordNet.sap(nouns[i], noun));
                    sum += (dist >= 0) ? dist : nouns.length;
                }

            sums[i] = sum;
        }

        int maxIndex = 0;
        int max = sums[0];
        for (int i = 1; i < sums.length; i++) {
            if (sums[i] > max) {
                max = sums[i];
                maxIndex = i;
            }
        }

        return nouns[maxIndex];
    }

    public static void main(String[] args) throws IOException {
        WordNet_Vus wordnet = new WordNet_Vus("/Users/pavlovus/Desktop/OKA/Practice12/src/main/java/org/example/Practice12/synsets.txt",
                "/Users/pavlovus/Desktop/OKA/Practice12/src/main/java/org/example/Practice12/hypernyms.txt");

        Outcast_Vus outcast = new Outcast_Vus(wordnet);

        String[] testFiles = new String[] {"/Users/pavlovus/Desktop/OKA/Practice12/src/main/java/org/example/Practice12/outcast5.txt",
        "/Users/pavlovus/Desktop/OKA/Practice12/src/main/java/org/example/Practice12/outcast8.txt",
        "/Users/pavlovus/Desktop/OKA/Practice12/src/main/java/org/example/Practice12/outcast11.txt"};

        for (String testFile : testFiles) {
            In in = new In(testFile);
            String[] nouns = in.readAllStrings();
            StdOut.println(outcast.outcast(nouns));
        }
    }
}