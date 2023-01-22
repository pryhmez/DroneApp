package com.example.DroneApp.drone;

import com.example.DroneApp.medication.Medication;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Drone", uniqueConstraints = @UniqueConstraint(
        name = "serialNo_unique",
        columnNames = "serialNumber"
))
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    @NonNull
    private DroneModelType model;

    @Column(name = "weight_limit")
    @NonNull
    private Integer weightLimit;

    @Column(name = "battery_capacity")
    @NonNull
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @NonNull
    private DroneStateType state;

    // getters and setters autogenerated by lombok

}

