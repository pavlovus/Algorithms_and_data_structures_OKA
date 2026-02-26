package org.example;

import java.util.Random;

public class InterestingParticleTester {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 50;
        double maxVelocityX = 0.01;
        double maxVelocityY = 0.01;
        InterestingParticle_Vus[] particles = new InterestingParticle_Vus[n];

        for (int i = 0; i < n; i++) {
            double radius = rand.nextDouble(0.01, 0.05);
            double rx = rand.nextDouble(radius, 1 - radius);
            double ry = rand.nextDouble(radius, 1 - radius);
            double vx = rand.nextDouble(-maxVelocityX, maxVelocityX);
            double vy = rand.nextDouble(-maxVelocityY, maxVelocityY);

            particles[i] = new InterestingParticle_Vus(rx, ry, vx, vy, radius);
        }

        CollisionSystem_Vus collisionSystem = new CollisionSystem_Vus(particles);
        collisionSystem.simulate();
    }
}
