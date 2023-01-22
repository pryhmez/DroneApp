package com.example.DroneApp.drone;

public enum DroneModelType {

    LIGHTWEIGHT ("lightWeight"),
    MIDDLEWEIGHT ("middleWeight"),
    CRUISERWEIGHT ("cruiserWeight"),
    HEAVYWEIGHT ("heavyWeight");

    private final String name;

    DroneModelType(String name) {
        this.name = name;
    }
}
