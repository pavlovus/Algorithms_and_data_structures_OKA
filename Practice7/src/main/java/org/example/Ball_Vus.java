package org.example;

import org.example.princeton.lib.StdDraw;

public class Ball_Vus {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius

    public Ball_Vus(double rx, double ry, double vx, double vy , double radius){
        /* initialize position and velocity */
        this.radius = radius;
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
    }

    public void move(double dt){
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) {vx = -vx;}
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) { vy = -vy; }
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw(){ StdDraw.filledCircle(rx, ry, radius); }
}