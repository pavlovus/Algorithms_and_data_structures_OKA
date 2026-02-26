package org.example;

import static org.example.Timing.trial;

public class TimingTest {
    public  static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        trial(512000, 777280);
        System.out.println(stopwatch.elapsedTime());
    }
}
