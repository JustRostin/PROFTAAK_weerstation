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
    private double AverageWindChill;
    private boolean stats = false;
    private ArrayList<Measurement> cachedMeasurements = new ArrayList<>();
    private LocalDate cachedStart = LocalDate.of(2000,1,1);
    private LocalDate cachedEnd = LocalDate.of(2000,1,1);

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
        this.stats = false;
    }

    public void setStart(LocalDate beginPeriod) {
        this.beginDate = beginPeriod;
        this.stats = false;
    }

    public void setEnd(int day, int month, int year) {
        this.beginDate = LocalDate.of(year, month, day);
        this.stats = false;
    }

    public void setEnd(LocalDate endPeriod) {
        this.beginDate = endPeriod;
        this.stats = false;
    }


    public long getNumberOfDays(){
        return ChronoUnit.DAYS.between(beginDate, endDate);
    }

    public ArrayList<RawMeasurement> getRawMeasurements() {
        return DatabaseConnection.getMeasurementsBetween(LocalDateTime.of(beginDate, LocalTime.of(0, 1)), LocalDateTime.of(endDate, LocalTime.of(23, 59)));
    }

    public ArrayList<Measurement> getMeasurements() {
        ArrayList<Measurement> measurements = new ArrayList<>();
        if (this.cachedStart.equals(this.beginDate) && this.cachedEnd.equals(this.endDate)) {
            measurements = this.cachedMeasurements;
        } else {
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
            this.cachedMeasurements = measurements;
            this.cachedStart = this.beginDate;
            this.cachedEnd = this.endDate;
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
        this.LowestTemp = 60;
        this.LowestTempOut = 60;
        this.LowestHum = 120;
        this.LowestHumOut = 120;
        this.LowestPress = 1100;
        this.LowestRainrate  = 200;
        this.LowestWind  = 200;
        this.HighestTemp = -40;
        this.HighestTempOut = -40;
        this.HighestHum = 0;
        this.HighestHumOut = 0;
        this.HighestPress = 10;
        this.HighestRainrate = -1;
        this.HighestWind = -1;
        double totalTemp = 0.0;
        double totalTempOut = 0.0;
        double totalHum = 0.0;
        double totalHumOut = 0.0;
        double totalPress = 0.0;
        double totalRainrate = 0.0;
        double totalWind = 0.0;
        double totalWindChill = 0.0;
        int amount = 0;
        for (Measurement measurement : measurements) {
            if (measurement.insideTempConvert() < this.LowestTemp) {
                this.LowestTemp = measurement.insideTempConvert();
            }
            if (measurement.outsideTempConvert() < this.LowestTempOut) {
                this.LowestTempOut = measurement.outsideTempConvert();
            }
            if (measurement.insideHumConvert() < this.LowestHum) {
                this.LowestHum = measurement.insideHumConvert();
            }
            if (measurement.outsideHumConvert() < this.LowestHumOut) {
                this.LowestHumOut = measurement.outsideHumConvert();
            }
            if (measurement.barometerConvert() < this.LowestPress) {
                this.LowestPress = measurement.barometerConvert();
            }
            if (measurement.rainRateConvert() < this.LowestRainrate) {
                this.LowestRainrate = measurement.rainRateConvert();
            }
            if (measurement.windSpeedConvert() < this.LowestWind) {
                this.LowestWind = measurement.windSpeedConvert();
            }
            if (measurement.insideTempConvert() > this.HighestTemp) {
                this.HighestTemp = measurement.insideTempConvert();
            }
            if (measurement.outsideTempConvert() > this.HighestTempOut) {
                this.HighestTempOut = measurement.outsideTempConvert();
            }
            if (measurement.insideHumConvert() > this.HighestHum) {
                this.HighestHum = measurement.insideHumConvert();
            }
            if (measurement.outsideHumConvert() > this.HighestHumOut) {
                this.HighestHumOut = measurement.outsideHumConvert();
            }
            if (measurement.barometerConvert() > this.HighestPress) {
                this.HighestPress = measurement.barometerConvert();
            }
            if (measurement.rainRateConvert() > this.HighestRainrate) {
                this.HighestRainrate = measurement.rainRateConvert();
            }
            if (measurement.windSpeedConvert() > this.HighestWind) {
                this.HighestWind = measurement.windSpeedConvert();
            }
            totalTemp += measurement.insideTempConvert();
            totalTempOut += measurement.outsideTempConvert();
            totalHum += measurement.insideHumConvert();
            totalHumOut += measurement.outsideHumConvert();
            totalPress += measurement.barometerConvert();
            totalRainrate += measurement.rainRateConvert();
            totalWind += measurement.windSpeedConvert();
            totalWindChill += measurement.windChillConvert();
            amount++;
        }
        this.AverageTemp = totalTemp/amount;
        this.AverageTempOut = totalTempOut/amount;
        this.AverageHum = totalHum/amount;
        this.AverageHumOut = totalHumOut/amount;
        this.AveragePress = totalPress/amount;
        this.AverageRainrate = totalRainrate/amount;
        this.AverageWind = totalWind/amount;
        this.AverageWindChill = totalWindChill/amount;
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
    public double getAverageWindChill() {
        calcStats();
        return AverageWindChill;
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
                if (measurement.insideTempConvert() != 0){
                    values.add(measurement.insideTempConvert());
                }
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
            if ((values.get(i) - Average)<0) {
                standardDeviation += (Math.sqrt((values.get(i) - Average)*-1) / values.size());
            } else {
                standardDeviation += (Math.sqrt(values.get(i) - Average) / values.size());
            }
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

    // Individuele opdracht Lieselotte Sihasale: graaddagen.
    public  int getDegreeDays(){
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        int counter = 0;
        double pTotal = 0.0;
        double totalDegreeDays= 0.0;
        LocalDate pDay = LocalDate.of(1900,1,1);

        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if((measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0)){
                LocalDate day = measurement.getTimeStamp(rawMeasurement);
                if (!pDay.isEqual(LocalDate.of(1900, 1, 1))) {
                    if (!pDay.equals(day)) {
                        double avOutsideTemp = pTotal / counter;
                        double avInsideTemp = 18.0;
                        double degreeDays = avInsideTemp - avOutsideTemp;
                        if(degreeDays < 0){
                            degreeDays = 0;
                        }
                        totalDegreeDays += degreeDays;
                        pTotal = 0.0;
                        counter = 0;
                    }
                }
                pTotal = pTotal + measurement.outsideTempConvert();
                counter++;
                pDay = day;
            }
        }

        //String resultValue = String.format("%.0f",totalDegreeDays);
        //String result = "Graaddagen: " + resultValue;

        int result = (int)Math.round(totalDegreeDays);
        return result;
    }

    // Individuele opdracht Lieselotte Sihasale: gewogen graaddagen.
    public int getWeightedDegreeDays(){
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        int counter = 0;
        double pTotal = 0.0;
        double weightingFactor;
        double totalWeightedDegreeDays= 0.0;
        LocalDate pDay = LocalDate.of(1900,1,1);

        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if((measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0)){
                LocalDate day = measurement.getTimeStamp(rawMeasurement);
                if (!pDay.isEqual(LocalDate.of(1900, 1, 1))) {
                    if (!pDay.equals(day)) {
                        double avOutsideTemp = pTotal / counter;
                        double avInsideTemp = 18.0;
                        double weightedDegreeDays = avInsideTemp - avOutsideTemp;
                        if(weightedDegreeDays < 0){
                            weightedDegreeDays = 0;
                        }
                        totalWeightedDegreeDays += weightedDegreeDays;
                        pTotal = 0.0;
                        counter = 0;
                    }
                }
                if (day.getMonthValue() >= 4 && day.getMonthValue() <= 9) {
                    weightingFactor = 0.8;
                } else if (day.getMonthValue() == 3 || day.getMonthValue() == 10) {
                    weightingFactor = 1.0;
                } else {
                    weightingFactor = 1.1;
                }
                pTotal = pTotal + (measurement.outsideTempConvert() * weightingFactor);
                counter++;
                pDay = day;
            }
        }

        //String resultValue = String.format("%.0f",totalWeightedDegreeDays);
        //String result = "Gewogen graaddagen: " + resultValue;

        int result = (int)Math.round(totalWeightedDegreeDays);

        return result;
    }


    //Raoul individuele opdracht E
    //get maximum continuous rainfall
    public Double getMaxContRain() {
        return getMaxContRain(this.getMeasurements());
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


    //Jason individuele opdracht F
    //get boolean heatwave
    public boolean heatwave() {
        boolean heatwave = false;
        int tropicalDays = 0;
        int summerDays = 0;

        LocalDate day = this.beginDate;
        ArrayList<Double> maxPerDay = new ArrayList<>();

        for (int i = 0; i < this.getNumberOfDays(); i++) {
            day = day.plusDays(i);
            Period dag = new Period(day, day);
            maxPerDay.add( dag.getHighestTempOut() );
        }
        for (Double max : maxPerDay) {
            if (max >= 25.0) {
                if (max >= 30.0) {
                    tropicalDays++;
                }
                summerDays++;
                if (tropicalDays >= 3 && summerDays >= 5) {
                    heatwave = true;
                }
            } else {
                tropicalDays = 0;
                summerDays = 0;
            }
        }
        return heatwave;
    }
    public static void testjason() {
        Period periode = new Period();
        periode.setStart(1, 8, 2019);
        periode.setEnd(30, 9, 2019);
        if (periode.heatwave()) {
            System.out.print("HIJ WERKT!");
        }
        //moet werken hittegolf volgens knmi op 23 augustus
    }

    //Wesley individuele opdracht J
    //get good days
    public int getGoodDays(){
        double goodWindChillLow = 15.0;
        double goodWindChillHigh = 20.0;
        double goodWindSpeedAVG = 10.0;
        double goodMAXRainfall = 0.3;

        double windChillDay;
        double windSpeedAVGDay;
        double rainfallMAXDay;

        LocalDate firstDay = this.beginDate;
        LocalDate currentDay = this.endDate;
        LocalDate lastDay = this.endDate;

        ArrayList<Measurement> measurements = getMeasurements();
        int goodDays = 0;
        int counter = 0;
        long numberOfDays = getNumberOfDays();

        while( counter < numberOfDays) {
            setStart(currentDay);
            setEnd(currentDay);

            windChillDay = getAverageWindChill();
            windSpeedAVGDay = getAverageWind();
            rainfallMAXDay = getHighestRainrate();

            if ( windChillDay >= goodWindChillLow && windChillDay >= goodWindChillHigh ){
                if (windSpeedAVGDay <= goodWindSpeedAVG) {
                        if (rainfallMAXDay <= goodMAXRainfall) {
                            goodDays = goodDays + 1;
                        }
                    }
                }

            currentDay = currentDay.minusDays(1);
            counter++;

            }
        System.out.println("Good days between " + firstDay + " and " + lastDay + ": " + goodDays);
        return goodDays;
    }


        /*
            for (int i = 0; i < measurements.size(); i++) {
                windChillDay = measurements.get(i).windChillConvert();
                if (windChillDay >= goodWindChillLow && windChillDay >= goodWindChillHigh) {
                    for (int ii = 0; ii < measurements.size(); ii++) {
                        windSpeedAVGDay = measurements.get(ii).avgWindSpeedConvert();
                        if (windSpeedAVGDay <= goodWindSpeedAVG) {
                            for (int iii = 0; iii < measurements.size(); iii++) {
                                rainfallMAXDay = measurements.get(iii).rainRateConvert();
                                if (rainfallMAXDay <= goodMAXRainfall) {
                                    gooddays = gooddays + 1;
                                }
                            }
                        }
                    }
                }
            }

         */

}
