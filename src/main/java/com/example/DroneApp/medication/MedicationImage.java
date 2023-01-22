package com.example.DroneApp.medication;

import jakarta.persistence.*;


@Entity
public class MedicationImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(length = 50000000)
    private byte [] image;

    public MedicationImage(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public MedicationImage(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }




}

