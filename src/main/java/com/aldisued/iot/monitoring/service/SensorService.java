package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.SensorDto;
import com.aldisued.iot.monitoring.entity.Sensor;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

  private final SensorRepository sensorRepository;

  public SensorService(SensorRepository sensorRepository) {
    this.sensorRepository = sensorRepository;
  }

  public Sensor saveSensor(SensorDto sensor) {
    if (sensor.name() == null || sensor.name().isBlank()) {
        throw new IllegalArgumentException("Sensor name must not be null or empty.");
    }
    List<Sensor> sensors = sensorRepository.findAll();
    if (sensors.stream().anyMatch(s -> s.getName().equalsIgnoreCase(sensor.name()))) {
        throw new IllegalStateException("A sensor with that name already exists.");
    }
    return sensorRepository.save(new Sensor(
        sensor.name(),
        sensor.type()
    ));
  }
}
