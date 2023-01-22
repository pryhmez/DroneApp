package com.example.DroneApp.config;

import com.example.DroneApp.auditLog.AuditLog;
import com.example.DroneApp.auditLog.AuditLogRepository;
import com.example.DroneApp.drone.Drone;
import com.example.DroneApp.drone.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
public class Scheduler {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Scheduled(fixedRate = 100000, initialDelay = 200000) // run every minute
    public void checkBatteryLevels() {
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            if (drone.getBatteryCapacity() < 25) {
                AuditLog log = new AuditLog(drone.getSerialNumber(), "Battery level below 25%", new Date());
                auditLogRepository.save(log);
            }
        }
    }
}
