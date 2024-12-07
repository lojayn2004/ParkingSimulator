package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        // Create a list of ParkingCar objects
        ArrayList<ParkingCar> car = new ArrayList<>();
        int totalcarServed = 0;
        // Initialize the parkingGates array with 3 gates
        ParkingGate[] parkingGates = new ParkingGate[3];
        ParkingFairSemaphore s = new ParkingFairSemaphore(4);
        // Initialize the parking gates
        parkingGates[0] = new ParkingGate(1, s);
        parkingGates[1] = new ParkingGate(2, s);
        parkingGates[2] = new ParkingGate(3, s);
        List<Thread> threads = new ArrayList<>();
        addCar(car,parkingGates);


        // Start each parking gate in a new thread
        for (ParkingCar c: car) {
            Thread th = new Thread(c);
            th.start();
            threads.add(th);
        }

        for (Thread th : threads) {
            try {
                th.join();
                // Wait for each thread to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (ParkingGate gate : parkingGates){
            totalcarServed += gate.getTotalcarServed();
        }
        System.out.println("Total Cars Served: "+totalcarServed );
        System.out.println("Current Cars in Parking: "+ parkingGates[0].GetCurrentSpots());
        System.out.println("Details:");
        for(ParkingGate gate : parkingGates){
            System.out.println("-Gate " +gate.gateNumber + " Served " + gate.totalcarServed + " cars.");
        }
    }

    public static void addCar(ArrayList<ParkingCar> car, ParkingGate[] parkingGates) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lojay\\IdeaProjects\\osassignment2\\osassignment2\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by ", " to extract each part
                String[] parts = line.split(", ");

                // Extract gate number, car ID, arrival time, and parking time
                String gateInfo = parts[0]; // "Gate 1"
                String carInfo = parts[1];  // "Car 0"
                String arriveInfo = parts[2]; // "Arrive 0"
                String parksInfo = parts[3]; // "Parks 3"

                // Get the numerical values
                int gateNumber = Integer.parseInt(gateInfo.split(" ")[1]);
                int carId = Integer.parseInt(carInfo.split(" ")[1]);
                int arriveTime = Integer.parseInt(arriveInfo.split(" ")[1]);
                int parksTime = Integer.parseInt(parksInfo.split(" ")[1]);

                // Create a new ParkingCar object and add it to the list
                ParkingCar parkingCar = new ParkingCar(carId, arriveTime, parksTime, gateNumber, parkingGates[gateNumber - 1]);
                car.add(parkingCar);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
