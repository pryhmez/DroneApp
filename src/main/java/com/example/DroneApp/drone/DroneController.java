package com.example.DroneApp.drone;

import com.example.DroneApp.droneMedication.DroneMedication;
import com.example.DroneApp.droneMedication.DroneMedicationRepository;
import com.example.DroneApp.medication.Medication;
import com.example.DroneApp.medication.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drone")
public class DroneController {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMedicationRepository droneMedicationRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) {
        Drone savedDrone = droneRepository.save(drone);
        return new ResponseEntity<>(savedDrone, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/{droneId}/load"})
    public ResponseEntity<Drone> loadDrone(
            @PathVariable int droneId,
            @RequestBody List<Integer> medicationIds
    ) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        if (drone == null) {
            throw new IllegalArgumentException("Drone with serial number " + droneId + " not found.");
        }
        if (drone.getState() != DroneStateType.IDLE) {
            throw new IllegalStateException("Drone with serial number " + droneId + " is not in the IDLE state.");
        }

        List<Medication> medications = medicationRepository.findAllById(medicationIds);

        int totalWeight = medications.stream().mapToInt(Medication::getWeight).sum();
        if (totalWeight > drone.getWeightLimit()) {
            throw new IllegalStateException("Too much load");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone with serial number " + droneId + " has less than 25% battery.");
        }
        drone.setState(DroneStateType.LOADING);
        droneRepository.save(drone);
        for (Medication medication : medications) {

                DroneMedication droneMedication = new DroneMedication();
                droneMedication.setDrone(drone);
                droneMedication.setMedication(medication);
                droneMedicationRepository.save(droneMedication);

        }

        return new ResponseEntity<>(drone, HttpStatus.OK);
    }


    @PutMapping("/updatestate")
    public ResponseEntity<Drone> updateDroneState(@PathVariable Integer droneId, @RequestBody DroneStateType state) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        if (drone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        drone.setState(state);
        droneRepository.save(drone);
        return new ResponseEntity<>(drone, HttpStatus.OK);
    }

    @GetMapping("/findbystate/{state}")
    public ResponseEntity<List<Drone>> getDronesByState(@PathVariable DroneStateType state) {
        List<Drone> drones = droneRepository.findByState(state);
        if (drones.isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        System.out.println("out put = " + drones);
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @GetMapping("/{droneId}/battery")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Integer droneId) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        if (drone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(drone.getBatteryCapacity(), HttpStatus.OK);
    }

    @GetMapping("/{id}/loadedmedications")
    public ResponseEntity<List<DroneMedication>> getMedicationsByDroneId(@PathVariable("id") Integer droneId) {
        System.out.println("this");
        Drone drone = droneRepository.findById(droneId).orElse(null);
        if (drone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DroneMedication> medications = droneMedicationRepository.findByDroneId(droneId);
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }



}
