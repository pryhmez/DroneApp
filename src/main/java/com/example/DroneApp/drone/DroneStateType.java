package com.example.DroneApp.drone;

public enum DroneStateType {

    IDLE("idle"),
    LOADING("loading"),
    LOADED("loaded"),
    DELIVERING("delivering"),
    DELIVERED("delivered"),
    RETURNING("returning");

    private final String name;

    DroneStateType(String name) {
        this.name = name;
    }
}
