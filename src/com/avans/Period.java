package com.avans;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.text.*;

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



    //TODO tests
    public  String degreeDays(Measurement measurement){
        double degreeDays = 0.0;
        double singleDegreeDay;
        ArrayList<Measurement> temperature = getMeasurements();
        double avInsideTemp = 18.0;

        for (int i = 0; i < temperature.size() ; i++) {
            singleDegreeDay = avInsideTemp - temperature.get(i).outsideTempConvert();
            if (singleDegreeDay < 0){
                singleDegreeDay = 0;
            }
            degreeDays = degreeDays + singleDegreeDay;
        }
        int resultValue = (int)degreeDays;
        String result = "Weighted degree days: " + degreeDays + " --> " + resultValue;
        return result;
    }

    //TODO test
    public String weightedDegreeDays(Measurement measurement){
        ArrayList<Measurement> temperature = getMeasurements();
        double singleDegreeDay;
        double avInsideTemp = 18.0;
        double weightingfactor;
        double weightedDegreeDays = 0.0;
        LocalDate day = this.beginDate;

        for(int i = 0; i < temperature.size(); i++){
            singleDegreeDay = avInsideTemp - temperature.get(i).outsideTempConvert();
            if (singleDegreeDay < 0){
                singleDegreeDay = 0;
            }
            if (day.getMonthValue() >= 4 && day.getMonthValue() <= 9){
                weightingfactor = 0.8;
            } else if (day.getMonthValue() == 3 || day.getMonthValue() == 10){
                weightingfactor = 1.0;
            } else {
                weightingfactor = 1.1;
            }
            double addWeightedDegreeDays = singleDegreeDay * weightingfactor;
            if( addWeightedDegreeDays < 0){
                addWeightedDegreeDays = 0;
            }
            weightedDegreeDays = weightedDegreeDays + addWeightedDegreeDays;
            day = day.plusDays(1);
        }

        int resultValue = (int)weightedDegreeDays;
        String result = "Weighted degree days: " + weightedDegreeDays + " --> " + resultValue;

        return result;
    }


    //Raoul individuele opdracht E
    //get maximum continuous rainfall
    public Double getMaxContRain() {
        ArrayList<Measurement> measurements = getMeasurements();
        double rain = 0.0; //accumulated amount of rain
        ArrayList<Double> showers = new ArrayList<Double>();
        boolean isEnd = false;
        //Find rainfall
        for (Measurement measurement: measurements) {
            if (measurement.rainRateConvert() > 0.0) {
                isEnd = false;
                double mm = (measurement.rainRateConvert()*0.2)/60;
                rain += mm;
            } else {
                if (!isEnd) {
                    isEnd = true;
                    showers.add(rain);
                    rain = 0;
                }
            }
        }
        //Find biggest rainfall
        double max = 0.0;
        for (Double rainfall: showers) {
            if (rainfall > max) {
                max = rainfall;
            }
        }
        System.out.println("max: "+String.format("%.01f",max)+" mm"); //print result
        return max; //return result
    }



}
