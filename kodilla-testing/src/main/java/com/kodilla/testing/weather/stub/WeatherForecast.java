package com.kodilla.testing.weather.stub;

import java.util.*;

public class WeatherForecast {
    private Temperatures temperatures;

    public WeatherForecast(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    public Map<String, Double> calculateForecast() {
        Map<String, Double> resultMap = new HashMap<>();

        for(Map.Entry<String, Double> temperature: temperatures.getTemperatures().entrySet()) {

            // adding 1 celsius degree to current value
            // as a temporary weather forecast
            resultMap.put(temperature.getKey(), temperature.getValue() + 1.0);
        }
        return resultMap;
    }

    public double calculateAverage() {
        double sum = 0;
        for(Map.Entry<String, Double> temperature : temperatures.getTemperatures().entrySet()) {
            sum += temperature.getValue();
        }
        sum /= temperatures.getTemperatures().size();
        sum = Math.round(sum * 100.0) / 100.0;
        return sum;
    }

    public double calculateMedian() {
        double median;

        List<Double> temperaturesArray = new ArrayList<>();
        for(Map.Entry<String, Double> temperature : temperatures.getTemperatures().entrySet()) {
            temperaturesArray.add(temperature.getValue());
        }

        Collections.sort(temperaturesArray);

        if (temperaturesArray.size() % 2 == 0) {
            median = (temperaturesArray.get((temperaturesArray.size()/2)-1) + temperaturesArray.get(temperaturesArray.size()/2))/2;
        } else {
            median = temperaturesArray.get(((temperaturesArray.size()+1)/2)-1);
        }
        return median;
    }
}
