package org.example;

import java.io.File;

public class Tester {
    public static void main(String[] args) {
        ConvexHull_Vus convexHull = new ConvexHull_Vus(new File("/Users/pavlovus/Desktop/OKA/Practice6/src/main/java/org/example/pr4_5_data/rs1423.txt"));
        convexHull.drawAllPoints();
        convexHull.findAndDrawConvexHull();
    }
}
