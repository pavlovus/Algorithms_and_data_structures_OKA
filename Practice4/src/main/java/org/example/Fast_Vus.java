package org.example;

import org.example.princeton.lib.StdDraw;

import java.util.Arrays;

public class Fast_Vus {
    private  Point_Vus[] points;

    public Fast_Vus(Point_Vus[] points) {
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

        for(int i = 0; i < points.length; i++){
            Point_Vus p = points[i];
            Point_Vus[] temp = new Point_Vus[points.length-1];

            int n = 0;
            for (int j = 0; j < points.length; j++) {
                if (j != i) temp[n++] = points[j];
            }

            Arrays.sort(temp, p.SLOPE_ORDER);
            int count = 1;
            double previous = p.slopeTo(temp[0]);

            for(int j = 1; j < temp.length; j++){
                double current = p.slopeTo(temp[j]);
                if(current == previous){
                    count++;
                } else {
                    if(count > 2){
                        Point_Vus[] collinear = new Point_Vus[count+1];
                        collinear[0] = p;
                        System.arraycopy(temp, j - count, collinear, 1, count);
                        Arrays.sort(collinear);
                        if (collinear[0].equals(p)) {
                            printAndDrawLine(collinear);
                        }
                    }
                    count = 1;
                    previous = current;
                }
            }

            if(count > 2){
                Point_Vus[] collinear = new Point_Vus[count+1];
                collinear[0] = p;
                System.arraycopy(temp, temp.length - count, collinear, 1, count);
                Arrays.sort(collinear);
                if (collinear[0].equals(p)) {
                    printAndDrawLine(collinear);
                }
            }
        }
    }

    private void printAndDrawLine(Point_Vus[] collinear) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < collinear.length; i++) {
            sb.append(collinear[i]);
            if (i < collinear.length - 1) {
                sb.append(" -> ");
            }
        }
        System.out.println(sb);

        collinear[0].drawTo(collinear[collinear.length-1]);
    }
}