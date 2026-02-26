package org.example;

import java.util.Random;

public class CollisionSystemTester {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 100;
        double radius = 0.02;
        double maxVelocityX = 0.01;
        double maxVelocityY = 0.01;
        Particle_Vus[] particles = new Particle_Vus[n];

        for (int i = 0; i < n; i++) {
            double rx = rand.nextDouble(radius, 1 - radius);
            double ry = rand.nextDouble(radius, 1 - radius);
            double vx = rand.nextDouble(-maxVelocityX, maxVelocityX);
            double vy = rand.nextDouble(-maxVelocityY, maxVelocityY);

            particles[i] = new Particle_Vus(rx, ry, vx, vy, radius, rand.nextDouble(0.1, 0.5));
        }

        CollisionSystem_Vus collisionSystem = new CollisionSystem_Vus(particles);
        collisionSystem.simulate();
    }
}
