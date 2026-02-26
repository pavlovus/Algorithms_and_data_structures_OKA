package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/pavlovus/Desktop/OKA/Practice4/src/main/java/org/example/pr4_5_data/input400.txt"));
        int n = scanner.nextInt();
        Point_Vus[] points = new Point_Vus[n];
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point_Vus(x, y);
        }
        Fast_Vus f = new Fast_Vus(points);
        f.drawPointsAndLines();
    }
}
