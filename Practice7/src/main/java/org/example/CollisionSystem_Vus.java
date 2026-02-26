package org.example;

import org.example.princeton.lib.StdDraw;

import java.util.PriorityQueue;
import java.util.Random;

public class CollisionSystem_Vus {
    private PriorityQueue<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle_Vus[] particles; // the array of particles

    public CollisionSystem_Vus(Particle_Vus[] particles) {
        this.particles = particles;
    }

    private void predict(Particle_Vus a){
        if (a == null) return;
        for (int i = 0; i < particles.length; i++){
            double dt = a.timeToHit(particles[i]);
            pq.add(new Event(t + dt, a, particles[i]));
        }
        pq.add(new Event(t + a.timeToHitVerticalWall() , a, null));
        pq.add(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

    private void redraw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(0, 0, 0, 1);
        StdDraw.line(0, 0, 1, 0);
        StdDraw.line(1, 0, 1, 1);
        StdDraw.line(0, 1, 1, 1);
        for (Particle_Vus particle : particles)  particle.draw();
        pq.add(new Event(t + 1.0, null, null));
        StdDraw.show(50);
    }

    public void simulate(){
        pq = new PriorityQueue<>();
        for(int i = 0; i < particles.length; i++) predict(particles[i]);
        pq.add(new Event(0, null, null));
        while(!pq.isEmpty()){
            Event event = pq.poll();
            if(!event.isValid()) continue;
            Particle_Vus a = event.a;
            Particle_Vus b = event.b;
            for(int i = 0; i < particles.length; i++)
                particles[i].move(event.time - t);
            t = event.time;
            if (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) a.bounceOffVerticalWall();
            else if (a == null && b != null) b.bounceOffHorizontalWall();
            else if (a == null && b == null) redraw();
            predict(a);
            predict(b);
        }
    }


    private class Event implements Comparable<Event>{
        private double time; // time of event
        private Particle_Vus a, b; // particles involved in event
        private int countA, countB; // collision counts for a and b

        public Event(double t, Particle_Vus a, Particle_Vus b) {
            this.time = t;
            this.a = a;
            this.b = b;
            this.countA = (a != null) ? a.getCount() : -1;
            this.countB = (b != null) ? b.getCount() : -1;
        }

        public int compareTo(Event that){ return Double.compare(this.time, that.time); }

        public boolean isValid(){
            if (a != null && a.getCount() != countA) return false;
            if (b != null && b.getCount() != countB) return false;
            return true;
        }
    }
}