package org.example;


public class ParkingCar implements Runnable {
    public int carId, arrivalTime, duration, gateNumber;
    public int waitingTime;
    public ParkingGate gate;

    public ParkingCar(int carId, int arrivalTime, int duration, int gateNumber, ParkingGate gate) {
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.gateNumber = gateNumber;
        this.gate = gate;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(arrivalTime * 1000L);
            System.out.println("Car " + carId + " from Gate " + gateNumber + " arrived at time " + arrivalTime);

            gate.TryEnter(this);

            Thread.sleep(duration * 1000L);

            gate.Leave(this);
            //System.out.println("Car " + carId + " from Gate " + gateNumber + " left. (Parking Status: "
            //    + (gate.GetCurrentSpots()) + " spots occupied)");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}