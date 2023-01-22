package com.example.DroneApp.medication;

import com.example.DroneApp.drone.Drone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Medication", uniqueConstraints = @UniqueConstraint(
        name = "code_unique",
        columnNames = "code"
))
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "img_id")
    private MedicationImage medicationImage;

    // getters and setters autogenerated by lombok

}