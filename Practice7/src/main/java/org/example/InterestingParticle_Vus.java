package org.example;

import org.example.princeton.lib.StdDraw;

import java.awt.*;
import java.util.Random;

public class InterestingParticle_Vus extends Particle_Vus {
    private Color color;
    private final Random rand = new Random();

    public InterestingParticle_Vus(double rx, double ry, double vx, double vy, double radius) {
        super(rx, ry, vx, vy, radius, radius*10);
        this.color = randomColor();
    }

    private Color randomColor() { return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)); }

    @Override
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(getRx(), getRy(), getRadius());
    }

    @Override
    public void bounceOff(Particle_Vus that) {
        super.bounceOff(that);
        this.color = randomColor();
    }

    @Override
    public void bounceOffVerticalWall() {
        super.bounceOffVerticalWall();
        this.color = randomColor();
    }

    @Override
    public void bounceOffHorizontalWall() {
        super.bounceOffHorizontalWall();
        this.color = randomColor();
    }
}