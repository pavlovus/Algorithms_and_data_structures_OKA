package org.example;

import org.example.princeton.lib.StdDraw;

import java.util.Arrays;

public class Brute_Vus {
    private Point_Vus[] points;

    public Brute_Vus(Point_Vus[] points) {
        this.points = points;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }

    public void drawPointsAndLines(){
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point_Vus point : points) {
            point.draw();
        }
        StdDraw.setPenColor(StdDraw.BLUE);

        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                double slope1 = points[i].slopeTo(points[j]);

                for (int k = j+1; k < points.length; k++) {
                    double slope2 = points[i].slopeTo(points[k]);

                    for (int l = k+1; l < points.length; l++) {
                        double slope3 = points[i].slopeTo(points[l]);

                        if(slope1 == slope2 && slope1 == slope3){
                            Point_Vus[] temp = {points[i], points[j], points[k], points[l]};
                            Arrays.sort(temp);

                            System.out.println(temp[0] + " -> " + temp[1] + " -> " + temp[2] + " -> " + temp[3]);

                            temp[0].drawTo(temp[3]);
                        }
                    }
                }
            }
        }
    }
}