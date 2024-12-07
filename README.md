# Parking Lot Simulation  🚗🚗

Simulate a parking system with **Java**, using **threads** and **semaphores** for synchronization and concurrency management. 🚦  

## 🚀 Features  
-  **Thread Synchronization**: Manage parking spots across 3 gates.  
-  **Realistic Simulation**: Cars arrive, park, and leave based on schedules.  
- **Dynamic Reporting**: Logs parking activity and shows total cars served.  
- **Concurrency Control**: Prevents race conditions with semaphores.  

## 🛠️ How It Works  
1. **Parking Spots**: 4 spots shared among cars.  
2. **Gates**: 3 gates handle car arrivals independently.  
3. **Input**: Car schedules are read from a text file.  
4. **Output**: Logs show car arrivals, parking status, and departures.  

## 📂 Example Input  
```plaintext  
Gate 1, Car 0, Arrive 0, Parks 3  
Gate 2, Car 5, Arrive 3, Parks 4  
Gate 3, Car 10, Arrive 2, Parks 4  

## ⚙️ Technologies Used:

- Java ☕
- Threads 🧵
- Semaphores 🚦
