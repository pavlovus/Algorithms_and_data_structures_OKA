package org.example;

import java.util.Random;

public class PercolationStats_Vus {
    private final int trials;
    private final double[] data;
    Random rand = new Random();
    private double mean = 0.0;
    private double stddev = 0.0;

    public PercolationStats_Vus(int n, int t) {
        if (n <= 0 || t <= 0) throw new IllegalArgumentException();
        this.trials = t;
        this.data = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation_Vus p = new Percolation_Vus(n);
            double count = 0;
            while (!p.percolates()) {
                int row = rand.nextInt(n);
                int col = rand.nextInt(n);
                while(p.isOpened(row, col)) {
                    row = rand.nextInt(n);
                    col = rand.nextInt(n);
                }
                p.open(row, col);
                count++;
            }
            data[i] = count/(n*n);
        }
    }

    public double mean() {
        if(mean == 0.0){
            double sum = 0.0;
            for (int i = 0; i < trials; i++) { sum += data[i]; }
            mean = sum / trials;
        }
        return mean;
    }

    public double stddev() {
        if(stddev == 0.0){
            double sum = 0.0;
            double m = mean();
            for (int i = 0; i < trials; i++) { sum += Math.pow((data[i] - m), 2);}
            stddev = Math.sqrt(sum / (trials-1));
        }
        return stddev;
    }

    public double confidenceOne() {
        return mean() - (1.96 * stddev() / Math.sqrt(trials));
    }

    public double confidenceTwo() {
        return mean() + (1.96 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        PercolationStats_Vus ps = new PercolationStats_Vus(200, 100);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceOne() + ", " + ps.confidenceTwo());
    }
    /*
    Results:
    mean = 0.5921367499999999
    stddev = 0.01055662010148948
    95% confidence interval = 0.590067652460108, 0.5942058475398918

    mean = 0.5912730000000002
    stddev = 0.00921298742937042
    95% confidence interval = 0.5894672544638435, 0.5930787455361568
     */
}
