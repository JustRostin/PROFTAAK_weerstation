package com.avans;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.text.DateFormatSymbols;

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

    public long getNumberOfDays(){
        return ChronoUnit.DAYS.between(beginDate, endDate);
    }

    public ArrayList<RawMeasurement> getRawMeasurements() {
        return DatabaseConnection.getMeasurementsBetween(LocalDateTime.of(beginDate, LocalTime.of(0, 1)), LocalDateTime.of(endDate, LocalTime.of(23, 59)));
    }

    public ArrayList<Measurement> getMeasurements() {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if(measurement.barometerConvert() < 1099 && measurement.barometerConvert() > 900) {
                measurements.add(measurement);
            }
            if(measurement.avgWindSpeedConvert() > 0.0 && measurement.avgWindSpeedConvert() < 20.0){
                measurements.add(measurement);
            }
            if(measurement.battLevelConvert() > 0.0 && measurement.battLevelConvert() < 2.0){
                measurements.add(measurement);
            }
            if(measurement.insideHumConvert() > 0 && measurement.insideHumConvert() < 100){
                measurements.add(measurement);
            }
            if(measurement.insideTempConvert() > 15.0 && measurement.insideTempConvert() < 40.0){
                measurements.add(measurement);
            }
            if(measurement.outsideHumConvert() > 0 && measurement.outsideHumConvert() < 100){
                measurements.add(measurement);
            }
            if(measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0){
                measurements.add(measurement);
            }
            if(measurement.rainRateConvert() >= 0 && measurement.rainRateConvert() < 1000){
                measurements.add(measurement);
            }
            if(measurement.UVLevelConvert() >= 0.0 && measurement.UVLevelConvert() < 10.0){
                measurements.add(measurement);
            }
            if(measurement.windDirConvert() >= 0 && measurement.windDirConvert() <= 360){
                measurements.add(measurement);
            }
            if(measurement.windSpeedConvert() >= 0.0 && measurement.windSpeedConvert() < 20.0){
                measurements.add(measurement);
            }
            if(measurement.xmitBattConvert() >= 0.0 && measurement.xmitBattConvert() < 100.0){
                measurements.add(measurement);
            }
        }
        return measurements;
    }

    public void getWettestMonth()
    {
        ArrayList<Integer> months = new ArrayList<>();
        ArrayList<Double> rain = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if(measurement.rainRateConvert() >= 0 && measurement.rainRateConvert() < 1000){
                int month = rawMeasurement.getDateStamp().getMonthValue();
                String monthyear = month+""+rawMeasurement.getDateStamp().getYear();
                month = Integer.parseInt(monthyear);
                if (!months.contains(month)){
                    months.add(month);
                    System.out.println("added "+month);
                    rain.add(measurement.rainRateConvert());


                } else {
                    int index = months.indexOf(month);
                    System.out.println("Adding to existing "+month);
                    double currentrain = rain.get(index);
                    rain.set(index,currentrain+measurement.rainRateConvert());
                }

            }
        }
        //Debugging if necessary
//        System.out.println("----Array month----");
//        System.out.println(months);
//        System.out.println("-------------------");
//        System.out.println("----Array Rain-----");
//        System.out.println(rain);
//        System.out.println("-------------------");
        double maxVal = Collections.max(rain);
        int indexMax = rain.indexOf(maxVal);
        int month = months.get(indexMax);
        String monthyear = ""+month;
        String monthonly = "";
        String yearonly = "";
        if (monthyear.length()==6){
             monthonly = ""+monthyear.charAt(0)+monthyear.charAt(1);
            yearonly = ""+monthyear.substring(2);
        } else {
             monthonly = ""+monthyear.charAt(0);
             yearonly = ""+monthyear.substring(1);
        }

        LocalDate localDate = LocalDate.of(0, Integer.parseInt(monthonly), 1);
        String name = localDate.getMonth().name();
        System.out.println("Meeste regen is gevallen in de maand: "+name+" "+yearonly+" met een hoeveelheid van: "+maxVal);
    }


}
