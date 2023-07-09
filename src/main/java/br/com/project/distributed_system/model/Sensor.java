package br.com.project.distributed_system.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Sensor {
    private String sensorName;
    private String macAddress;
    private Double minValue;
    private Double maxValue;
    private Double averageValue;
    private String location;
    private Date date;
    
    public Sensor(String sensorName, String macAddress, Double minValue, Double maxValue, Double averageValue, String location, Date date) {
        this.sensorName = sensorName;
        this.macAddress = macAddress;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.averageValue = averageValue;
        this.location = location;
        this.date = date;
    }

}

