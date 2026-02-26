package org.example;

import java.io.*;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordNet_Vus {
    private TreeMap<Integer, String[]> idOfSynsets;
    private TreeMap<String, Set<Integer>> appearancesOfNoun;
    private Digraph_Vus digraph;
    private SAP_Vus sap;

    // конструктор приймає назви двох файлів
    public WordNet_Vus(String synsets, String hypernyms) throws IOException {
        idOfSynsets = new TreeMap<>();
        appearancesOfNoun = new TreeMap<>();
        BufferedReader br = new BufferedReader(new FileReader(synsets));
        String line;
        while ((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line, ",");
            int id = Integer.parseInt(st.nextToken());
            String[] nouns = st.nextToken().split(" ");
            idOfSynsets.put(id, nouns);

            for (String noun : nouns) {
                if (!appearancesOfNoun.containsKey(noun)) {
                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(id);
                    appearancesOfNoun.put(noun, set);
                } else appearancesOfNoun.get(noun).add(id);
            }
        }

        br = new BufferedReader(new FileReader(hypernyms));
        digraph = new Digraph_Vus(idOfSynsets.size());
        while ((line = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line, ",");
            int v = Integer.parseInt(st.nextToken());
            if (v < 0 || v >= idOfSynsets.size()) throw new IllegalArgumentException();
            while (st.hasMoreTokens()){
                int w = Integer.parseInt(st.nextToken());
                if (w < 0 || w >= idOfSynsets.size()) throw new IllegalArgumentException();
                digraph.addEdge(v, w);
            }
        }
        sap = new SAP_Vus(digraph);

        checkForSingleRoot(digraph);
        checkForCycles(digraph);
    }

    private void checkForCycles(Digraph_Vus digraph) {
        boolean[] marked = new boolean[digraph.V()];
        boolean[] currPath = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v] && dfs(digraph, v, marked, currPath)) {
                throw new IllegalArgumentException("Graph has a cycle");
            }
        }
    }

    private boolean dfs(Digraph_Vus g, int v, boolean[] marked, boolean[] currPath) {
        marked[v] = true;
        currPath[v] = true;
        for (int w : g.adj(v))
            if (!marked[w]) if (dfs(g, w, marked, currPath)) return true;
            else if (currPath[w]) return true;

        currPath[v] = false;
        return false;
    }

    private void checkForSingleRoot(Digraph_Vus digraph) {
        int roots = 0;
        for (int v = 0; v < digraph.V(); v++) {
            if (digraph.degree(v) == 0) {
                roots++;
                if (roots > 1) throw new IllegalArgumentException();
            }
        }
        if (roots == 0) throw new IllegalArgumentException();
    }

    // множина іменників, що повертається як ітератор (без дублікатів)
    public Iterable<String> nouns(){ return appearancesOfNoun.keySet(); }

    // чи є слово серед WordNet іменників?
    public boolean isNoun(String word){ return appearancesOfNoun.containsKey(word); }

    // відстань між nounA і nounB
    public int distance(String nounA, String nounB){
       if(!appearancesOfNoun.containsKey(nounA) || !appearancesOfNoun.containsKey(nounB)) throw new IllegalArgumentException();
       return sap.length(appearancesOfNoun.get(nounA), appearancesOfNoun.get(nounB));
    }

    // синсет що є спільним предком nounA і nounB в найкоршому шляху до предка
    public String sap(String nounA, String nounB){
        if(!appearancesOfNoun.containsKey(nounA) || !appearancesOfNoun.containsKey(nounB)) throw new IllegalArgumentException();
        return String.join(" ", idOfSynsets.get(sap.ancestor(appearancesOfNoun.get(nounA), appearancesOfNoun.get(nounB))));
    }

    public static void main(String[] args) throws IOException {

    }
}