package com.avans;

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

}
