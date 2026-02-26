package org.example;

import org.example.princeton.lib.StdDraw;

import java.util.Comparator;


public class Point_Vus implements Comparable<Point_Vus> {
    // порівняти точки за градієнтом до цієї точки
    public final Comparator<Point_Vus> SLOPE_ORDER = new SOrder();

    private final int x;
    private final int y;

    public Point_Vus(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point_Vus that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // нахил між цією і that точкою
    public double slopeTo(Point_Vus that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    // чи ця точка лексикографічно менша за that точку?
    public int compareTo(Point_Vus that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y && this.x < that.x) return -1;
        if (this.y > that.y) return 1;
        return 0;
    }

    private class SOrder implements Comparator<Point_Vus>{
        public int compare(Point_Vus p, Point_Vus q){
            double s1 = Point_Vus.this.slopeTo(p);
            double s2 = Point_Vus.this.slopeTo(q);
            if (s1 > s2) return 1;
            else if (s1 < s2)  return -1;
            return 0;
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}