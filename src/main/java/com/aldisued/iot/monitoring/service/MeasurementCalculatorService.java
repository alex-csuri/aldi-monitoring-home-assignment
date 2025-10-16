package com.aldisued.iot.monitoring.service;


import java.util.ArrayList;
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
      if (data == null || data.isEmpty()) {
          throw new IllegalArgumentException("No data available to parse.");
      }
      if (windowSize <= 0 || windowSize > data.size()) {
          throw new IllegalArgumentException("Invalid window size.");
      }

      List<Double> movingAverages = new ArrayList<>();

      // calculate moving average based on given window size
      for (int i = 0; i <= data.size() - windowSize; i++) {
          double sum = 0.0;
          for (int j = i; j < i + windowSize; j++) {
              sum += data.get(j);
          }
          movingAverages.add(sum / windowSize);
      }

      return movingAverages;
  }

}
