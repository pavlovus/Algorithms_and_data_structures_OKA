package org.example;

import org.example.princeton.lib.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConvexHull_Vus {
    private Point2D_Vus[] points;

    public ConvexHull_Vus(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();

            this.points = new Point2D_Vus[n];
            for(int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[i] = new Point2D_Vus(x, y);
            }

            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void drawAllPoints(){
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D_Vus point : points) point.draw();
    }

    public void findAndDrawConvexHull(){
        Arrays.sort(points);
        Point2D_Vus p = points[0];

        Point2D_Vus[] temp = new Point2D_Vus[points.length - 1];
        System.arraycopy(points, 1, temp, 0, temp.length);
        Arrays.sort(temp, p.POLAR_ORDER);

        ArrayList<Point2D_Vus> hull = new ArrayList<>();
        hull.add(p);
        hull.add(temp[0]);

        for(int i = 1; i < temp.length; i++){
            Point2D_Vus current = temp[i];

            while(hull.size() >= 2){
                Point2D_Vus last = hull.getLast();
                Point2D_Vus secondLast = hull.get(hull.size() - 2);

                int turn = Point2D_Vus.ccw(secondLast, last, current);
                if(turn > 0) break;
                else hull.removeLast();
            }
            hull.add(current);
        }

        drawConvexHull(hull);
    }

    private void drawConvexHull(ArrayList<Point2D_Vus> hull) {
        StdDraw.setPenColor(StdDraw.BLUE);

        for(int i = 0; i < hull.size() - 1; i++)
            hull.get(i).drawTo(hull.get(i + 1));
        hull.getLast().drawTo(hull.getFirst());
    }
}