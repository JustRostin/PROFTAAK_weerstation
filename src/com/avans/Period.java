package com.avans;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class Period {

    private LocalDate beginDate;
    private LocalDate endDate;

    private double LowestTemp;
    private double LowestTempOut;
    private double LowestHum;
    private double LowestHumOut;
    private double LowestPress;
    private double LowestRainrate;
    private double LowestWind;
    private double HighestTemp;
    private double HighestTempOut;
    private double HighestHum;
    private double HighestHumOut;
    private double HighestPress;
    private double HighestRainrate;
    private double HighestWind;
    private double AverageTemp;
    private double AverageTempOut;
    private double AverageHum;
    private double AverageHumOut;
    private double AveragePress;
    private double AverageRainrate;
    private double AverageWind;
    private boolean stats = false;

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
        this.beginDate = LocalDate.now().minusDays(days);
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
        ArrayList<RawMeasurement> rawMeasurements = this.getRawMeasurements();
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

    public Measurement getLatestMeasurement() {
        ArrayList<Measurement> measurements = this.getMeasurements();
        return measurements.get(measurements.size()-1);
    }

    public void calcStats() {
        if (stats) {
            return;
        }
        ArrayList<Measurement> measurements = this.getMeasurements();
        this.LowestTemp = -40;
        this.LowestTempOut = -40;
        this.LowestHum = 0;
        this.LowestHumOut = 0;
        this.LowestPress = 10;
        this.LowestRainrate  = -1;
        this.LowestWind  = -1;
        this.HighestTemp = 60;
        this.HighestTempOut = 60;
        this.HighestHum = 120;
        this.HighestHumOut = 120;
        this.HighestPress = 1100;
        this.HighestRainrate = 200;
        this.HighestWind = 200;
        double totalTemp = 0.0;
        double totalTempOut = 0.0;
        double totalHum = 0.0;
        double totalHumOut = 0.0;
        double totalPress = 0.0;
        double totalRainrate = 0.0;
        double totalWind = 0.0;
        int amount = 0;
        for (Measurement measurement : measurements) {
            if (measurement.insideTempConvert() > this.LowestTemp) {
                this.LowestTemp = measurement.insideTempConvert();
            }
            if (measurement.outsideTempConvert() > this.LowestTempOut) {
                this.LowestTempOut = measurement.outsideTempConvert();
            }
            if (measurement.insideHumConvert() > this.LowestHum) {
                this.LowestHum = measurement.insideHumConvert();
            }
            if (measurement.outsideHumConvert() > this.LowestHumOut) {
                this.LowestHumOut = measurement.outsideHumConvert();
            }
            if (measurement.barometerConvert() > this.LowestPress) {
                this.LowestPress = measurement.barometerConvert();
            }
            if (measurement.rainRateConvert() > this.LowestRainrate) {
                this.LowestRainrate = measurement.rainRateConvert();
            }
            if (measurement.windSpeedConvert() > this.LowestWind) {
                this.LowestWind = measurement.windSpeedConvert();
            }
            if (measurement.insideTempConvert() < this.HighestTemp) {
                this.HighestTemp = measurement.insideTempConvert();
            }
            if (measurement.outsideTempConvert() < this.HighestTempOut) {
                this.HighestTempOut = measurement.outsideTempConvert();
            }
            if (measurement.insideHumConvert() < this.HighestHum) {
                this.HighestHum = measurement.insideHumConvert();
            }
            if (measurement.outsideHumConvert() < this.HighestHumOut) {
                this.HighestHumOut = measurement.outsideHumConvert();
            }
            if (measurement.barometerConvert() < this.HighestPress) {
                this.HighestPress = measurement.barometerConvert();
            }
            if (measurement.rainRateConvert() < this.HighestRainrate) {
                this.HighestRainrate = measurement.rainRateConvert();
            }
            if (measurement.windSpeedConvert() < this.HighestWind) {
                this.HighestWind = measurement.windSpeedConvert();
            }
            totalTemp += measurement.insideTempConvert();
            totalTempOut += measurement.outsideTempConvert();
            totalHum += measurement.insideHumConvert();
            totalHumOut += measurement.outsideHumConvert();
            totalPress += measurement.barometerConvert();
            totalRainrate += measurement.rainRateConvert();
            totalWind += measurement.windSpeedConvert();
            amount++;
        }
        this.AverageTemp = totalTemp/amount;
        this.AverageTempOut = totalTempOut/amount;
        this.AverageHum = totalHum/amount;
        this.AverageHumOut = totalHumOut/amount;
        this.AveragePress = totalPress/amount;
        this.AverageRainrate = totalRainrate/amount;
        this.AverageWind = totalWind/amount;
    }

    public double getLowestTemp() {
        calcStats();
        return this.LowestTemp;
    }
    public double getLowestTempOut() {
        calcStats();
        return this.LowestTempOut;
    }
    public double getLowestHum() {
        calcStats();
        return this.LowestHum;
    }
    public double getLowestHumOut() {
        calcStats();
        return this.LowestHumOut;
    }
    public double getLowestPress() {
        calcStats();
        return this.LowestPress;
    }
    public double getLowestRainrate() {
        calcStats();
        return this.LowestRainrate;
    }
    public double getHighestTemp() {
        calcStats();
        return this.HighestTemp;
    }
    public double getHighestHum() {
        calcStats();
        return this.HighestHum;
    }
    public double getHighestPress() {
        calcStats();
        return this.HighestPress;
    }
    public double getHighestRainrate() {
        calcStats();
        return this.HighestRainrate;
    }
    public double getLowestWind() {
        calcStats();
        return this.LowestWind;
    }
    public double getHighestTempOut() {
        calcStats();
        return this.HighestTempOut;
    }
    public double getHighestHumOut() {
        calcStats();
        return this.HighestHumOut;
    }
    public double getHighestWind() {
        calcStats();
        return this.HighestWind;
    }
    public double getAverageTemp() {
        calcStats();
        return this.AverageTemp;
    }
    public double getAverageTempOut() {
        calcStats();
        return this.AverageTempOut;
    }
    public double getAverageHum() {
        calcStats();
        return this.AverageHum;
    }
    public double getAverageHumOut() {
        calcStats();
        return this.AverageHumOut;
    }
    public double getAveragePress() {
        calcStats();
        return this.AveragePress;
    }
    public double getAverageRainrate() {
        calcStats();
        return this.AverageRainrate;
    }
    public double getAverageWind() {
        calcStats();
        return this.AverageWind;
    }


    public double getModus(String type) {
        ArrayList<Measurement> measurements = this.getMeasurements();
        ArrayList<Double> values = new ArrayList<>();
        HashMap<Double, Integer> elementCountMap = new HashMap<>();

        if (type.equals("Temp")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.insideTempConvert());
            }
        }
        if (type.equals("TempOut")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.outsideTempConvert());
            }
        }
        if (type.equals("Hum")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.insideHumConvert());
            }
        }
        if (type.equals("HumOut")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.outsideHumConvert());
            }
        }
        if (type.equals("Press")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.barometerConvert());
            }
        }
        if (type.equals("Rainrate")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.rainRateConvert());
            }
        }
        if (type.equals("Wind")) {
            for (Measurement measurement : measurements) {
                values.add(measurement.windSpeedConvert());
            }
        }

        for (double t : values){
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
        return element;
    }

    public double getMedian(String type) {
        ArrayList<Measurement> measurements = this.getMeasurements();
        ArrayList<Double> Temps = new ArrayList<>();
        ArrayList<Double> TempOuts = new ArrayList<>();
        ArrayList<Double> Hums = new ArrayList<>();
        ArrayList<Double> HumOuts = new ArrayList<>();
        ArrayList<Double> Press = new ArrayList<>();
        ArrayList<Double> Rainrates = new ArrayList<>();
        ArrayList<Double> Winds = new ArrayList<>();

        for (Measurement measurement: measurements) {
            Temps.add(measurement.insideTempConvert());
            TempOuts.add(measurement.outsideTempConvert());
            Hums.add(measurement.insideHumConvert());
            HumOuts.add(measurement.outsideHumConvert());
            Press.add(measurement.barometerConvert());
            Rainrates.add(measurement.rainRateConvert());
            Winds.add(measurement.windSpeedConvert());
        }

        Collections.sort(Temps);
        Collections.sort(TempOuts);
        Collections.sort(Hums);
        Collections.sort(HumOuts);
        Collections.sort(Press);
        Collections.sort(Rainrates);
        Collections.sort(Winds);

        if (type.equals("Temp")) {
            return Temps.get(Temps.size() / 2);
        }
        if (type.equals("TempOut")) {
            return TempOuts.get(TempOuts.size() / 2);
        }
        if (type.equals("Hum")) {
            return Hums.get(Hums.size() / 2);
        }
        if (type.equals("HumOut")) {
            return HumOuts.get(HumOuts.size() / 2);
        }
        if (type.equals("Press")) {
            return Press.get(Press.size() / 2);
        }
        if (type.equals("Rainrate")) {
            return Rainrates.get(Rainrates.size() / 2);
        }
        if (type.equals("Wind")) {
            return Winds.get(Winds.size() / 2);
        }
        return 0.0;
    }

    public double getStandardDeviation(String type){
        ArrayList<Measurement> measurements = this.getMeasurements();
        ArrayList<Double> values = new ArrayList<>();
        double Average = 0;
        if (type.equals("Temp")) {
            Average = getAverageTemp();
            for (Measurement measurement : measurements) {
                values.add(measurement.insideTempConvert());
            }
        }
        if (type.equals("TempOut")) {
            Average = getAverageTempOut();
            for (Measurement measurement : measurements) {
                values.add(measurement.outsideTempConvert());
            }
        }
        if (type.equals("Hum")) {
            Average = getAverageHum();
            for (Measurement measurement : measurements) {
                values.add(measurement.insideHumConvert());
            }
        }
        if (type.equals("HumOut")) {
            Average = getAverageHumOut();
            for (Measurement measurement : measurements) {
                values.add(measurement.outsideHumConvert());
            }
        }
        if (type.equals("Press")) {
            Average = getAveragePress();
            for (Measurement measurement : measurements) {
                values.add(measurement.barometerConvert());
            }
        }
        if (type.equals("Rainrate")) {
            Average = getAverageRainrate();
            for (Measurement measurement : measurements) {
                values.add(measurement.rainRateConvert());
            }
        }
        if (type.equals("Wind")) {
            Average = getAverageWind();
            for (Measurement measurement : measurements) {
                values.add(measurement.windSpeedConvert());
            }
        }

        double standardDeviation = 0;
        for (int i = 0; i < values.size(); i++) {
            standardDeviation += Math.sqrt(values.get(i) - Average) / values.size();
        }
        return Math.sqrt(standardDeviation);
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
        ArrayList<Measurement> measurements = this.getMeasurements();
        return getMaxContRain(measurements);
    }
    public Double getMaxContRain(ArrayList<Measurement> measurements) {
        double rain = 0.0; //accumulated amount of rain
        ArrayList<Double> showers = new ArrayList<>();
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



}