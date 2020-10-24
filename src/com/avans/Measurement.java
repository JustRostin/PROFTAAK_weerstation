package com.avans;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Measurement {

    private String timestamp;
    private short barometer;
    private short insideTemp;
    private short insideHum;
    private short outsideTemp;
    private short windSpeed;
    private short avgWindSpeed;
    private short windDir;
    private short outsideHum;
    private short rainRate;
    private short UVLevel;
    private short xmitBatt;
    private short battLevel;
    private short sunrise;
    private short sunset;
    private short windChill;


    public Measurement(RawMeasurement data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timestamp = data.getDateStamp().format(formatter);
        this.barometer = data.getBarometer();
        this.insideTemp = data.getInsideTemp();
        this.insideHum = data.getInsideHum();
        this.outsideTemp = data.getOutsideTemp();
        this.windSpeed = data.getWindSpeed();
        this.avgWindSpeed = data.getAvgWindSpeed();
        this.windDir = data.getWindDir();
        this.outsideHum = data.getOutsideHum();
        this.rainRate = data.getRainRate();
        this.UVLevel = data.getUVLevel();
        this.xmitBatt = data.getXmitBatt();
        this.battLevel = data.getBattLevel();
        this.sunrise = data.getSunrise();
        this.sunset = data.getSunset();
    }

    public Measurement(short barometer, short insideTemp, short insideHum, short outsideTemp,
                       short windSpeed, short avgWindSpeed, short windDir, short outsideHum,
                       short rainRate, short UVLevel, short xmitBatt, short battLevel,
                       short sunrise, short sunset) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
        this.barometer = barometer;
        this.insideTemp = insideTemp;
        this.insideHum = insideHum;
        this.outsideTemp = outsideTemp;
        this.windSpeed = windSpeed;
        this.avgWindSpeed = avgWindSpeed;
        this.windDir = windDir;
        this.outsideHum = outsideHum;
        this.rainRate = rainRate;
        this.UVLevel = UVLevel;
        this.xmitBatt = xmitBatt;
        this.battLevel = battLevel;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
    
    public LocalDate getTimeStamp(RawMeasurement data){
        LocalDate stamp = data.getDateStamp().toLocalDate();
        return stamp;
    }

    public double barometerConvert(){
        double barometer = ValueConverter.airPressure(this.barometer);
        return barometer;
    }
    public double insideTempConvert(){
        double insideTemp = ValueConverter.temperature(this.insideTemp);
        return insideTemp;
    }
    public double insideHumConvert(){
        double insideHum = ValueConverter.humidity(this.insideHum);
        return insideHum;
    }
    public double outsideTempConvert(){
        double outsideTemp = ValueConverter.temperature(this.outsideTemp);
        return outsideTemp;
    }
    public double windSpeedConvert(){
        double windSpeed = ValueConverter.windSpeed(this.windSpeed);
        return windSpeed;
    }
    public double avgWindSpeedConvert(){
        double avgWindSpeed = ValueConverter.windSpeed(this.avgWindSpeed);
        return avgWindSpeed;
    }
    public double windDirConvert(){
        double windDir = ValueConverter.windDirection(this.windDir);
        return windDir;
    }
    public double outsideHumConvert(){
        double outsideHum = ValueConverter.humidity(this.outsideHum);
        return outsideHum;
    }
    public double windChillConvert(){
        double windChill = ValueConverter.windChill(this.outsideTemp, this.avgWindSpeed);
        return windChill;
    }
    public double rainRateConvert(){
        double rainRate = ValueConverter.rainMeter(this.rainRate);
        return rainRate;
    }
    public double UVLevelConvert(){
        double UVLevel = ValueConverter.uvIndex(this.UVLevel);
        return UVLevel;
    }
    public double xmitBattConvert(){
        double xmitBatt = ValueConverter.batteryLevel(this.xmitBatt);
        return xmitBatt;
    }
    public double battLevelConvert(){
        double battLevel = ValueConverter.batteryLevel(this.battLevel);
        return battLevel;
    }
    public String sunriseConvert(){
        String sunrise = ValueConverter.sunRise(this.sunrise);
        return sunrise;
    }
    public String sunsetConvert(){
        String sunset = ValueConverter.sunSet(this.sunset);
        return sunset;
    }

    public String toString(){
        String s = "Converted:"
                + "\nbarometer \t\t= \t" + String.format("%.01f",barometerConvert())
                + "\ninsideTemp \t\t= \t" + String.format("%.01f",insideTempConvert())
                + "\ninsideHum \t\t= \t" + insideHumConvert()
                + "\noutsideTemp \t= \t" + String.format("%.01f",outsideTempConvert())
                + "\nwindSpeed \t\t= \t" + String.format("%.01f",windSpeedConvert())
                + "\navgWindSpeed \t= \t" + String.format("%.01f",avgWindSpeedConvert())
                + "\nwindDir \t\t= \t" + windDirConvert()
                + "\noutsideHum \t\t= \t" + outsideHumConvert()
                + "\nrainRate \t\t= \t" + rainRateConvert()
                + "\nwindchill \t\t= \t" + String.format("%.01f",windChillConvert())
                + "\nUVLevel \t\t= \t" + UVLevelConvert()
                + "\nxmitBatt \t\t= \t" + xmitBattConvert()
                + "\nbattLevel \t\t= \t" + battLevelConvert()
                + "\nsunrise \t\t= \t" + sunriseConvert()
                + "\nsunset \t\t\t= \t" + sunsetConvert();
        return s;
    }
}
