package org.example.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DomainSorter {
    List<Domain> domains;

    public DomainSorter() {
        domains = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            System.out.println("Введіть домени (або порожній рядок для завершення вводу):");
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                domains.add(new Domain(line.trim()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sort() {Collections.sort(domains);}

    public void print() {
        for (Domain domain : domains) System.out.println(domain);
    }

    public static void main(String[] args) {
        DomainSorter sorter = new DomainSorter();
        sorter.sort();
        sorter.print();
    }
}