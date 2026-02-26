package org.example;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.io.File;

public class SeamCarving_Vus {
    private Picture picture;

    public SeamCarving_Vus(Picture picture, int newWidth, int newHeight) {
        this.picture = picture;
        changeSize(newWidth, newHeight);
    }

    public void changeSize(int newWidth, int newHeight) {
        while(picture.width() > newWidth)
            deleteVerticalSeam(verticalSeam());

        while(picture.height() > newHeight)
            deleteHorizontalSeam(horizontalSeam());
    }

    private int[] verticalSeam(){
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph((picture.height() * picture.width()) + 2);
        int top = digraph.V() - 2;
        int bottom = digraph.V() - 1;

        for(int i = 0; i < picture.width(); i++) digraph.addEdge(new DirectedEdge(top, i, energy(i, 0)));

        for(int i = 0; i < picture.height(); i++)
            for(int j = 0; j < picture.width(); j++) {
                int v = i * picture.width() + j;
                for (int dx = -1; dx <= 1; dx++) {
                    int x = j + dx;
                    int y = i + 1;
                    if (x >= 0 && x < picture.width() && y < picture.height()) {
                        int w = y * picture.width() + x;
                        digraph.addEdge(new DirectedEdge(v, w, energy(x, y)));
                    }
                }
            }

        for(int i = 0; i < picture.width(); i++) {
            int v = (picture.height() - 1) * picture.width() + i;
            digraph.addEdge(new DirectedEdge(v, bottom, energy(i, picture.height() - 1)));
        }

        AcyclicSP sp = new AcyclicSP(digraph, top);
        Iterable<DirectedEdge> seam = sp.pathTo(bottom);
        int[] result = new int[picture.height()];
        for(DirectedEdge e : seam) {
            int to = e.to();
            if (to >= 0 && to < picture.height()*picture.width()) {
                int y = to / picture.width();
                int x = to % picture.width();
                result[y] = x;
            }
        }

        return result;
    }

    private void deleteVerticalSeam(int[] seam){
        Picture newPicture = new Picture(picture.width() - 1, picture.height());

        for(int i = 0; i < picture.height(); i++) {
            int pixelToDelete = seam[i];
            for(int j = 0; j < picture.width(); j++) {
                int x = (j < pixelToDelete) ? j : j + 1;
                if (j < picture.width() - 1)
                    newPicture.set(j, i, picture.get(x, i));
            }
        }

        picture = newPicture;
    }

    private int[] horizontalSeam(){
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph((picture.height() * picture.width()) + 2);
        int top = digraph.V() - 2;
        int bottom = digraph.V() - 1;
        for(int i = 0; i < picture.height(); i++)digraph.addEdge(new DirectedEdge(top, i*picture.width(), energy(0, i)));

        for(int i = 0; i < picture.height(); i++)
            for(int j = 0; j < picture.width(); j++) {
                int v = i * picture.width() + j;
                for (int dy = -1; dy <= 1; dy++) {
                    int y = i + dy;
                    int x = j + 1;
                    if (y >= 0 && y < picture.height() && x < picture.width())
                        digraph.addEdge(new DirectedEdge(v, y*picture.width() + x, energy(x, y)));
                }
            }

        for(int i = 0; i < picture.height(); i++) {
            int v = i * picture.width() + picture.width() - 1;
            digraph.addEdge(new DirectedEdge(v, bottom, energy(picture.width()-1, i)));
        }

        AcyclicSP sp = new AcyclicSP(digraph, top);
        Iterable<DirectedEdge> seam = sp.pathTo(bottom);
        int[] result = new int[picture.width()];
        for(DirectedEdge e : seam) {
            int to = e.to();
            if (to >= 0 && to < picture.height()*picture.width()) {
                int y = to / picture.width();
                int x = to % picture.width();
                result[x] = y;
            }
        }

        return result;
    }

    private void deleteHorizontalSeam(int[] seam){
        Picture newPicture = new Picture(picture.width(), picture.height() - 1);

        for(int i = 0; i < picture.width(); i++) {
            int pixelToDelete = seam[i];
            for(int j = 0; j < picture.height(); j++) {
                int y = (j < pixelToDelete) ? j : j + 1;
                if (j < picture.height() - 1)
                    newPicture.set(i, j, picture.get(i, y));
            }
        }

        picture = newPicture;
    }

    public Picture getPicture() { return picture;}

    private double energy(int x, int y) {
        if (x == 0 || y == 0 || x == picture.width() - 1 || y == picture.height() - 1)
            return 1000000;

        Color up = picture.get(x, y - 1);
        Color down = picture.get(x, y + 1);
        Color left = picture.get(x - 1, y);
        Color right = picture.get(x + 1, y);
        Color upLeft = picture.get(x - 1, y - 1);
        Color upRight = picture.get(x + 1, y - 1);
        Color downLeft = picture.get(x - 1, y + 1);
        Color downRight = picture.get(x + 1, y + 1);

        double Gx = 0;
        double Gy = 0;
        Gx += difference(right, left);
        Gx += difference(upRight, upLeft);
        Gx += difference(downRight, downLeft);
        Gy += difference(down, up);
        Gy += difference(downRight, upRight);
        Gy += difference(downLeft, upLeft);

        return Math.sqrt(Gx + Gy);
    }

    private int difference(Color a, Color b) {
        int dr = a.getRed() - b.getRed();
        int dg = a.getGreen() - b.getGreen();
        int db = a.getBlue() - b.getBlue();
        return dr * dr + dg * dg + db * db;
    }

    public static void main(String[] args) {
        Picture p = new Picture(new File("/Users/pavlovus/Desktop/OKA/Practice14/src/main/java/org/example/pictures/img_1.png"));
        p.show();
        SeamCarving_Vus sc = new SeamCarving_Vus(p, 100, 250);
        sc.getPicture().show();
    }
}