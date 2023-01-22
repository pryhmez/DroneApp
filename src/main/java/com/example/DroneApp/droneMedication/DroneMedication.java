package com.example.DroneApp.droneMedication;

import com.example.DroneApp.drone.Drone;
import com.example.DroneApp.medication.Medication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "drone_id")
    private Drone drone;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    // getters and setters autogenerated by lombok
}