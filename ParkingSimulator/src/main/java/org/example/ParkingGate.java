package org.example;

import java.util.concurrent.Semaphore;


public class ParkingGate {
    ParkingFairSemaphore semaphore;
    int gateNumber, totalSpots = 4;
    public static int time = 0;
    int totalcarServed = 0;
    public ParkingGate(int gateNumber, ParkingFairSemaphore semaphore) {
        this.semaphore = semaphore;
        this.gateNumber = gateNumber;
    }

    // Try to enter the parking lot
    public  void TryEnter(ParkingCar car) throws InterruptedException {
        boolean waiting = false;
        totalcarServed++;
        long waitStart = System.currentTimeMillis();
        if (!semaphore.tryAcquire()) {
            waiting = true;
            System.out.println("Car " + car.carId + " from Gate " + gateNumber + " waiting for a spot.");
            semaphore.acquire();
        }
        long waitTime = (System.currentTimeMillis() - waitStart) / 1000;
        if (waiting) {
            System.out.println("Car " + car.carId + " from Gate " + gateNumber + " parked after waiting." + waitTime + " (Parking Status: "
                    + (this.GetCurrentSpots()) + " spots occupied)");
        }
        else {
            System.out.println("Car " + car.carId + " from Gate " + gateNumber + " parked (Parking Status: "
                    + (this.GetCurrentSpots()) + " spots occupied)");
        }
    }
    public int getTotalcarServed(){
        return totalcarServed;
    };
    public synchronized int GetCurrentSpots() {

        return totalSpots - semaphore.availablePermits();
    }
    public synchronized void Leave(ParkingCar car) {

        System.out.println("Car " + car.carId + " from Gate " + gateNumber + " left after "+ car.duration + " units of time." + " left. (Parking Status: "
                + (GetCurrentSpots() - 1) + " spots occupied)");
        semaphore.release();
    }

}
