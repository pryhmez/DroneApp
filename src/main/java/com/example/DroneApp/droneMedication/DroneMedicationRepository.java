package com.example.DroneApp.droneMedication;

import com.example.DroneApp.medication.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Integer> {
    List<DroneMedication> findByDroneId(Integer droneId);
}
