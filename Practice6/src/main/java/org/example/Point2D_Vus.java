package org.example;

import org.example.princeton.lib.StdDraw;

import java.util.Comparator;

public class Point2D_Vus implements Comparable<Point2D_Vus> {
    public final Comparator<Point2D_Vus> POLAR_ORDER = new PolarOrder();

    private final int x;
    private final int y;

    public Point2D_Vus(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D_Vus that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int compareTo(Point2D_Vus that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y && this.x < that.x) return -1;
        if (this.y > that.y) return 1;
        return 0;
    }

    public static int ccw(Point2D_Vus a, Point2D_Vus b, Point2D_Vus c){
        return  (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
    }

    public boolean equals(Object other){
        if (other == this) return true;
        if (other == null) return false;
        if (!(other instanceof Point2D_Vus)) return false;
        Point2D_Vus that = (Point2D_Vus)other;
        return this.x == that.x && this.y == that.y;
    }

    private class PolarOrder implements Comparator<Point2D_Vus>{
        public int compare(Point2D_Vus q1, Point2D_Vus q2){
            if(q1.y > Point2D_Vus.this.y && q2.y < Point2D_Vus.this.y) return -1;
            if(q1.y < Point2D_Vus.this.y && q2.y > Point2D_Vus.this.y) return 1;

            int turn = ccw(Point2D_Vus.this, q1, q2);
            if (turn > 0) return -1;
            if (turn < 0) return 1;
            return 0;
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}