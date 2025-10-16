package com.aldisued.iot.monitoring.service;


import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
      if (deviation == null
              || deviation < 0.0
              || deviation > 1.0
              || Double.isNaN(deviation)
              || Double.isInfinite(deviation)) {
          throw new IllegalArgumentException();
      }
      if (values == null || values.isEmpty()) {
          return List.of();
      }

      double average = values.stream()
              .mapToDouble(Double::doubleValue)
              .average()
              .orElse(Double.NaN);

      double range = average * deviation;

      // keep only the values whose distance from the average is within the given deviation range
      return values.stream()
              .filter(value -> Math.abs(value - average) <= range)
              .toList();
  }

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // TODO: Task 10
    return List.of();
  }

}
