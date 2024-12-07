package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class ParkingFairSemaphore {
    private int parkingSpots;
    // store Cars trying to access the resource
    // so we could give priority to the Thread that came first
    private final Queue<Thread> currentThreads;

    public ParkingFairSemaphore(int permits) {
        this.parkingSpots = permits;
        currentThreads = new LinkedList<>();
    }

    public synchronized void acquire() throws InterruptedException {
        currentThreads.add(Thread.currentThread());
        // This condition gives the spot to the thread that is at the beginning of the queue
        while (parkingSpots <= 0 || currentThreads.peek() != Thread.currentThread()) {
            wait();
        }
        // now we could acquire a recourse
        parkingSpots--;
        // remove the current car because it is served
        currentThreads.poll();
    }

    public synchronized void release() {
        parkingSpots++;
        notifyAll();
    }

    public synchronized boolean tryAcquire() throws InterruptedException {
        if (availablePermits() > 0) {
            acquire();
            return true;
        }
        return false;
    }

    public synchronized int availablePermits() {
        return parkingSpots;
    }
}
