package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.AlertDto;
import com.aldisued.iot.monitoring.entity.Alert;
import com.aldisued.iot.monitoring.repository.AlertRepository;
import com.aldisued.iot.monitoring.repository.SensorRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

  private final AlertRepository alertRepository;
  private final SensorRepository sensorRepository;
  private final KafkaTemplate<String, AlertDto> kafkaTemplate;

  public AlertService(AlertRepository alertRepository, SensorRepository sensorRepository,
      KafkaTemplate<String, AlertDto> kafkaTemplate) {
    this.alertRepository = alertRepository;
    this.sensorRepository = sensorRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  public Alert saveAlert(AlertDto alertDto) {
    // TODO: Task 6
    return null;
  }

  public AlertDto findLastAlertBySensorId(UUID sensorId) {
      if (sensorId == null) {
          throw new IllegalArgumentException("Sensor ID required.");
      }
      List<Alert> alerts = alertRepository.findAll();
      return alerts.stream()
              .filter(a -> a.getSensor() != null && sensorId.equals(a.getSensor().getId()))
              .max(Comparator.comparing(Alert::getTimestamp))
              .map(a -> new AlertDto(sensorId, a.getMessage(), a.getTimestamp()))
              .orElseThrow(() -> new IllegalStateException("No alert found for sensor ID: " + sensorId));
  }
}
