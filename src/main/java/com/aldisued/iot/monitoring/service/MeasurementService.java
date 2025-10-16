package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import com.aldisued.iot.monitoring.repository.SensorReadingRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

  private final SensorReadingRepository sensorReadingRepository;

  public MeasurementService(SensorReadingRepository sensorReadingRepository) {
    this.sensorReadingRepository = sensorReadingRepository;
  }

  public List<Double> getMeasurementValuesBySensorType(SensorType sensorType, LocalDateTime from,
      LocalDateTime to) {
      if (sensorType == null || from == null || to == null) {
          throw new IllegalArgumentException("Sensor type and time range must not be null.");
      }
      return sensorReadingRepository.findAll().stream()
              .filter(sr -> sr.getSensor() != null && sr.getSensor().getType() == sensorType)
              .filter(sr -> sr.getTimestamp().isBefore(to) && sr.getTimestamp().isAfter(from))
              .sorted(Comparator.comparing(SensorReading::getTimestamp))
              .map(SensorReading::getValue)
              .toList();
  }

  public Optional<Double> getAverageTemperature(LocalDateTime from, LocalDateTime to) {
      if (from == null || to == null) {
          throw new IllegalArgumentException("Invalid time period.");
      }
      return sensorReadingRepository.findAll().stream()
              .filter(sr -> sr.getSensor() != null && sr.getSensor().getType() == SensorType.TEMPERATURE)
              .filter(sr -> sr.getTimestamp().isBefore(to) && sr.getTimestamp().isAfter(from))
              .mapToDouble(SensorReading::getValue)
              .average()
              .stream()
              .boxed()
              .findFirst();
  }

}
