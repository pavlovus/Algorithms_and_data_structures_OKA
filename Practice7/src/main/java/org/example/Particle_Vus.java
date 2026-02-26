package org.example;

import org.example.princeton.lib.StdDraw;

public class Particle_Vus {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final double mass; // mass
    private int count; // number of collisions

    public Particle_Vus(double rx, double ry, double vx, double vy , double radius, double mass) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.mass = mass;
    }

    public void move(double dt) {
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw() { StdDraw.filledCircle(rx, ry, radius); }

    public double timeToHit(Particle_Vus that){
        if (this == that) return Double.POSITIVE_INFINITY;
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if( dvdr > 0) return Double.POSITIVE_INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        if (d < 0) return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        if(this.vx > 0) return (1 - this.radius - this.rx)/this.vx;
        else if(this.vx < 0) return (radius - this.rx)/this.vx;
        else return Double.POSITIVE_INFINITY;
    }

    public double timeToHitHorizontalWall() {
        if(this.vy > 0) return (1 - this.radius - this.ry)/this.vy;
        else if(this.vy < 0) return (radius - this.ry)/this.vy;
        else return Double.POSITIVE_INFINITY;
    }

    public void bounceOff(Particle_Vus that){
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() {
        this.vx = -this.vx;
        this.count++;
    }

    public void bounceOffHorizontalWall() {
        this.vy = -this.vy;
        this.count++;
    }

    public int getCount() { return count; }

    public double getRx() { return rx; }

    public double getRy() { return ry; }

    public double getRadius() { return radius; }
}