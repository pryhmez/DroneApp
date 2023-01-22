package com.example.DroneApp.drone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DroneRepositoryTests {

    @Autowired
    private DroneRepository droneRepository;

    @Test
    public void saveDrone() {
        Drone drone = Drone.builder()
                .model(DroneModelType.CRUISERWEIGHT)
                .weightLimit("300kg")
                .batteryCapacity(50)
                .state(DroneStateType.DELIVERED)
                .build();

        droneRepository.save(drone);
    }

    @Test
    public void getAllDrones() {

        List<Drone> dronesList = droneRepository.findAll();

        System.out.println("Drones list = " + dronesList );
    }
}
