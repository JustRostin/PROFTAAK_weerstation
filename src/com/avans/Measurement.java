package com.avans;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Measurement {

    private RawMeasurement measurement;

    public Measurement(RawMeasurement data){
        this.measurement = data;
    }

    public void Convert(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String timestamp = measurement.getDateStamp().format(formatter);
        System.out.println("Timestamp: "+timestamp);
        System.out.println("Luchtdruk is "+String.format("%.01f", ValueConverter.airPressure(measurement.getBarometer())));
        System.out.println("InsideTemp is "+ String.format("%.01f", ValueConverter.temperature(measurement.getInsideTemp())));
        System.out.println("InsideHum is "+ValueConverter.humidity(measurement.getInsideHum()));
        System.out.println("OutsideTemp is "+String.format("%.01f",ValueConverter.temperature(measurement.getOutsideTemp())));
        System.out.println("WindSpeed is "+String.format("%.01f",ValueConverter.windSpeed(measurement.getWindSpeed())));
        System.out.println("AvgWindSpeed is "+String.format("%.01f",ValueConverter.windSpeed(measurement.getAvgWindSpeed())));
        System.out.println("WindDir is "+ValueConverter.windDirection(measurement.getWindDir()));
        System.out.println("OutsideHum is "+ValueConverter.humidity(measurement.getOutsideHum()));
        System.out.println("RainRate is "+ValueConverter.rainMeter(measurement.getRainRate()));
        System.out.println("UVLevel is "+ValueConverter.uvIndex(measurement.getUVLevel()));
        System.out.println("BattLevel is "+ValueConverter.batteryLevel(measurement.getBattLevel()));
        System.out.println("Sunrise is "+ValueConverter.sunRise(measurement.getSunrise()));
        System.out.println("Sunset is "+ValueConverter.sunSet(measurement.getSunset()));
        System.out.println("Heat index is "+String.format("%.01f",ValueConverter.heatIndex(measurement.getOutsideTemp(),ValueConverter.humidity(measurement.getOutsideHum()))));
        System.out.println("Windchill is "+String.format("%.01f",ValueConverter.windChill(measurement.getOutsideTemp(), measurement.getWindSpeed())));
        System.out.println("Dewpoint is "+String.format("%.01f",ValueConverter.dewPoint(measurement.getOutsideTemp(), measurement.getOutsideHum())));
    }
    public String toString(){
        return measurement.toString();
    }
}
