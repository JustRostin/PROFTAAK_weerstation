package com.avans;

import sun.security.util.Length;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class Period {

    private LocalDate beginDate;
    private LocalDate endDate;

    public Period() {
        beginDate = LocalDate.now();
        endDate = LocalDate.now();
    }

    public Period(LocalDate beginPeriod, LocalDate endPeriod) {
        this.beginDate = beginPeriod;
        this.endDate = endPeriod;
    }

    public Period(LocalDate beginPeriod) {
        this.beginDate = beginPeriod;
        this.endDate = LocalDate.now();
    }

    public Period(int days) {
        this.beginDate = LocalDate.now().minus(java.time.Period.ofDays(days));
        this.endDate = LocalDate.now();
    }

    public void setStart(int day, int month, int year) {
        this.beginDate = LocalDate.of(year, month, day);
    }

    public void setStart(LocalDate beginPeriod) {
        this.beginDate = beginPeriod;
    }

    public void setEnd(int day, int month, int year) {
        this.beginDate = LocalDate.of(year, month, day);
    }

    public void setEnd(LocalDate endPeriod) {
        this.beginDate = endPeriod;
    }

    public ArrayList<RawMeasurement> getRawMeasurements() {
        return DatabaseConnection.getMeasurementsBetween(LocalDateTime.of(beginDate, LocalTime.of(0, 1)), LocalDateTime.of(endDate, LocalTime.of(23, 59)));
    }

    public ArrayList<Measurement> getMeasurements() {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            boolean IsGood = true;
            if(!(measurement.barometerConvert() < 1099 && measurement.barometerConvert() > 900))        {IsGood = false;}
            if(!(measurement.avgWindSpeedConvert() > 0.0 && measurement.avgWindSpeedConvert() < 20.0))  {IsGood = false;}
            if(!(measurement.battLevelConvert() > 0.0 && measurement.battLevelConvert() < 2.0))         {IsGood = false;}
            if(!(measurement.insideHumConvert() > 0 && measurement.insideHumConvert() < 100))           {IsGood = false;}
            if(!(measurement.insideTempConvert() > 15.0 && measurement.insideTempConvert() < 40.0))     {IsGood = false;}
            if(!(measurement.outsideHumConvert() > 0 && measurement.outsideHumConvert() < 100))         {IsGood = false;}
            if(!(measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0))    {IsGood = false;}
            if(!(measurement.rainRateConvert() >= 0 && measurement.rainRateConvert() < 1000))           {IsGood = false;}
            if(!(measurement.UVLevelConvert() >= 0.0 && measurement.UVLevelConvert() < 10.0))           {IsGood = false;}
            if(!(measurement.windDirConvert() >= 0 && measurement.windDirConvert() <= 360))             {IsGood = false;}
            if(!(measurement.windSpeedConvert() >= 0.0 && measurement.windSpeedConvert() < 20.0))       {IsGood = false;}
            if(!(measurement.xmitBattConvert() >= 0.0 && measurement.xmitBattConvert() < 100.0))        {IsGood = false;}
            if (IsGood) {
                measurements.add(measurement);
            }
        }
        return measurements;
    }

    public void getLowest(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        double lowest = -40;
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0) {
                if (measurement.outsideTempConvert() < lowest) {
                    lowest = measurement.outsideTempConvert();
                }
            }
        }
    }

    public void getHighest(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        double highest = 60;
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0) {
                if (measurement.outsideTempConvert() > highest) {
                    highest = measurement.outsideTempConvert();
                }
            }
        }
    }

    public double getAverage(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        double average = -40;
        double total = 0.0;
        int amount = 0;
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0) {
                amount++;
                total += measurement.outsideTempConvert();
            }
        }
        average = total / amount;
        return average;
    }

    public void getmode() {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        ArrayList<Double> temperatures = new ArrayList<>();
        HashMap<Double, Integer> elementCountMap = new HashMap<>();


        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if((measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0)){
                temperatures.add(measurement.outsideTempConvert());
            }

        }

        for (double t : temperatures){
            if (elementCountMap.containsKey(t)){
                elementCountMap.put(t, elementCountMap.get(t)+1);
            } else {
                elementCountMap.put(t,1);
            }

        }

        Set<Map.Entry<Double, Integer>> entrySet = elementCountMap.entrySet();
        double element = 0;
        int frequency = 1;
        for (Map.Entry<Double, Integer> entry :entrySet ){
            if (entry.getValue() > frequency){
                element = entry.getKey();
                frequency = entry.getValue();
            }
        }
        System.out.println("De meest voorkomende temp: " + String.format("%.01f",element) + " komt " + frequency + " keer voor");
    }

//    public double getMedian(RawMeasurement rawmeasurement) {
//        ArrayList<Measurement> measurements = new ArrayList<>();
//        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
//       Collections.sort(rawMeasurements);
//        double median = rawMeasurements.get(rawMeasurements.size() / 2);
//    }
}