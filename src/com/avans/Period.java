package com.avans;

import sun.security.util.Length;

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
                    //System.out.println("added "+month);
                    rain.add(measurement.rainRateConvert());


                } else {
                    int index = months.indexOf(month);
                    //System.out.println("Adding to existing "+month);
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
        return getMaxContRain(measurements);
    }
    public Double getMaxContRain(ArrayList<Measurement> measurements) {
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
        showers.add(rain);
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
    public void TestGetMaxContRain() {
        ArrayList<Measurement> testwaarden = new ArrayList<>();
        short[] waarden = {0,5,9,5,4,7,3,6,5,4,8,9,3,5,4,6,8,7,1,6,8,2,5,4,6,0,-1,2,2,6,8,4,7,8,8,8,9,9,6,6,5,4,1,3,0,9,9,9,9,9,9,9,9,9};
        for (Short getal: waarden) {
            Measurement measurement = new Measurement((short)0,(short)0,(short)0,(short)0,(short)0,(short)0,(short)0,(short)0,getal,(short)0,(short)0,(short)0,(short)0,(short)0);
            testwaarden.add(measurement);
        }
        getMaxContRain(testwaarden);
        System.out.println("Test resultaat hoort 0,4mm te zijn");

        ArrayList<Measurement> testwaarden2 = new ArrayList<>();
        short[] waarden2 = {0,-1,-5,-6,0,5,9,3,1,7,3,2,1,8,9,6,4,4,7,2,1,6,8,2,1,7,2,0,0,0,0,0,0,0,0,0,0,0,0,9,6,2,4,3,4,1,2,2,4,5,3,6,4,4,3};
        for (Short getal: waarden2) {
            Measurement measurement = new Measurement((short)0,(short)0,(short)0,(short)0,(short)0,(short)0,(short)0,(short)0,getal,(short)0,(short)0,(short)0,(short)0,(short)0);
            testwaarden2.add(measurement);
        }
        getMaxContRain(testwaarden2);
        System.out.println("Test resultaat hoort 0,3mm te zijn");

        ArrayList<Measurement> testwaarden3 = new ArrayList<>();
        short[] waarden3 = {0,0,0,0,0,0,0,0,0,0,0};
        for (Short getal: waarden3) {
            Measurement measurement = new Measurement((short)0,(short)0,(short)0,(short)0,(short)0,(short)0,(short)0,(short)0,getal,(short)0,(short)0,(short)0,(short)0,(short)0);
            testwaarden3.add(measurement);
        }
        getMaxContRain(testwaarden3);
        System.out.println("Test resultaat hoort 0,0mm te zijn");
    }


//    public double getMedian(RawMeasurement rawmeasurement) {
//        ArrayList<Measurement> measurements = new ArrayList<>();
//        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
//       Collections.sort(rawMeasurements);
//        double median = rawMeasurements.get(rawMeasurements.size() / 2);
//    }
}