package org.example;

import org.example.princeton.lib.StdDraw;

import java.util.Random;

public class BouncingBalls_Vus {
    private int n;
    private double radius;
    private Ball_Vus[] balls;

    public BouncingBalls_Vus(int n, double radius, double maxVelocityX, double maxVelocityY){
        this.n = n;
        this.radius = radius;
        this.balls = new Ball_Vus[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            double rx = radius + (1.0 - 2 * radius) * rand.nextDouble();
            double ry = radius + (1.0 - 2 * radius) * rand.nextDouble();
            double vx = rand.nextDouble(-maxVelocityX, maxVelocityX);
            double vy = rand.nextDouble(-maxVelocityY, maxVelocityY);

            balls[i] = new Ball_Vus(rx, ry, vx, vy, radius);
        }
    }

    public void simulate(){
        while(true){
            StdDraw.clear();
            StdDraw.line(0, 0, 0, 1);
            StdDraw.line(0, 0, 1, 0);
            StdDraw.line(1, 0, 1, 1);
            StdDraw.line(0, 1, 1, 1);
            for (int i = 0; i < n; i++){
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }

    public static void main(String[] args){
        BouncingBalls_Vus bb = new BouncingBalls_Vus(100, 0.02, 0.03, 0.03);
        bb.simulate();
    }
}