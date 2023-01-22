package com.example.DroneApp.medication;

import com.example.DroneApp.drone.Drone;
import com.example.DroneApp.drone.DroneRepository;
import com.example.DroneApp.drone.DroneStateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private DroneRepository droneRepository;

    @PostMapping(value = {"/register"})
    public ResponseEntity<Medication> registerMedication(
            @RequestParam("name") String name,
            @RequestParam("code") String code,
            @RequestParam("weight") int weight,
            @RequestPart("imageFile") MultipartFile file
            ) {
        try {

            Medication medication = new Medication();
            MedicationImage medicationImage = new MedicationImage(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            medication.setMedicationImage(medicationImage);
            medication.setCode(code);
            medication.setName(name);
            medication.setWeight(weight);


            if (!medication.getName().matches("^[A-Za-z0-9-_]+$")) {

                throw new IllegalStateException("the name " + medication.getName() );

//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!medication.getCode().matches("^[A-Z0-9_]+$")) {

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            medicationRepository.save(medication);
            return new ResponseEntity<>(medication, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            return null;
        }
    }

    @GetMapping("/getallmedications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = medicationRepository.findAll();
        if (medications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }


    @GetMapping("/available-drones")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        List<Drone> availableDrones = droneRepository.findByState(DroneStateType.IDLE);
        return new ResponseEntity<>(availableDrones, HttpStatus.OK);
    }

    @GetMapping("/{droneId}/battery-level")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable Integer droneId) {
        Drone drone = droneRepository.findById(droneId).orElse(null);
        if (drone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(drone.getBatteryCapacity(), HttpStatus.OK);
    }
}
