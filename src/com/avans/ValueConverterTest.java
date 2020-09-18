package com.avans;


public class ValueConverterTest {





    public static void main(String[] args) {
        ValueConverter Converter = new ValueConverter();
        //data
        short Barometer = 30163;
        short InsideTemp = 742;
        short InsideHum = 48;
        short OutsideTemp = 608;
        short WindSpeed = 3;
        short AvgWindSpeed = 3;
        short WindDir = 257;
        short OutsideHum = 71;
        short RainRate = 0;
        short UVLevel = 5;
        short BattLevel = 209;
        short Sunrise = 630;
        short Sunset = 1906;


        System.out.println("Luchtdruk is "+Converter.airPressure(Barometer)+" en moet 1.021 zijn");
        System.out.println("InsideTemp is "+Converter.temperature(InsideTemp)+" en moet 23.4 zijn");
        System.out.println("InsideHum is "+Converter.humidity(InsideHum)+" en moet 48 zijn");
        System.out.println("OutsideTemp is "+Converter.temperature(OutsideTemp)+" en moet 16 zijn");
        System.out.println("WindSpeed is "+Converter.windSpeed(WindSpeed)+" en moet 5 zijn");
        System.out.println("AvgWindSpeed is "+Converter.windSpeed(AvgWindSpeed)+" en moet 5 zijn");
        System.out.println("WindDir is "+Converter.windDirection(WindDir)+" en moet 257 zijn");
        System.out.println("OutsideHum is "+Converter.humidity(OutsideHum)+" en moet 71 zijn");
        System.out.println("RainRate is "+Converter.rainMeter(RainRate)+" en moet 0 zijn");
        System.out.println("UVLevel is "+Converter.uvIndex(UVLevel)+" en moet 0.5 zijn");
        System.out.println("BattLevel is "+Converter.batteryLevel(BattLevel)+" en moet 1.22 zijn");
        System.out.println("Sunrise is "+Converter.sunRise(Sunrise)+" en moet 6:30 zijn");
        System.out.println("Sunset is "+Converter.sunSet(Sunset)+" en moet 19:06 zijn");
        System.out.println("Windchill is "+Converter.windChill(OutsideTemp, WindSpeed) +" en moet 20.04 of 20.05 zijn");
        System.out.println("Dewpoint is "+Converter.dewPoint(OutsideTemp, OutsideHum) +" en moet 12.88 zijn");


    }





}
