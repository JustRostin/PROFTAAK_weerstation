package com.avans;


public class ValueConverterTest {





    public static void main(String[] args) {
        //data
//        short Barometer = 30163;
//        short InsideTemp = 742;
//        short InsideHum = 48;
//        short OutsideTemp = 608;
//        short WindSpeed = 3;
//        short AvgWindSpeed = 3;
//        short WindDir = 257;
//        short OutsideHum = 71;
//        short RainRate = 290;
//        short UVLevel = 5;
//        short BattLevel = 209;
//        short Sunrise = 630;
//        short Sunset = 1906;

        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();

        System.out.println("Timestamp of measurement: "+ raw.getDateStamp());
        System.out.println("--------------------------");
        System.out.println("Luchtdruk is "+String.format("%.01f", ValueConverter.airPressure(raw.getBarometer())));
        System.out.println("InsideTemp is "+ String.format("%.01f", ValueConverter.temperature(raw.getInsideTemp())));
        System.out.println("InsideHum is "+ValueConverter.humidity(raw.getInsideHum()));
        System.out.println("OutsideTemp is "+String.format("%.01f",ValueConverter.temperature(raw.getOutsideTemp())));
        System.out.println("WindSpeed is "+String.format("%.01f",ValueConverter.windSpeed(raw.getWindSpeed())));
        System.out.println("AvgWindSpeed is "+String.format("%.01f",ValueConverter.windSpeed(raw.getAvgWindSpeed())));
        System.out.println("WindDir is "+ValueConverter.windDirection(raw.getWindDir()));
        System.out.println("OutsideHum is "+ValueConverter.humidity(raw.getOutsideHum()));
        System.out.println("RainRate is "+ValueConverter.rainMeter(raw.getRainRate()));
        System.out.println("UVLevel is "+ValueConverter.uvIndex(raw.getUVLevel()));
        System.out.println("BattLevel is "+ValueConverter.batteryLevel(raw.getBattLevel()));
        System.out.println("Sunrise is "+ValueConverter.sunRise(raw.getSunrise()));
        System.out.println("Sunset is "+ValueConverter.sunSet(raw.getSunset()));
        System.out.println("Heat index is "+String.format("%.01f",ValueConverter.heatIndex(raw.getOutsideTemp(),ValueConverter.humidity(raw.getOutsideHum()))));
        System.out.println("Windchill is "+String.format("%.01f",ValueConverter.windChill(raw.getOutsideTemp(), raw.getWindSpeed())));
        System.out.println("Dewpoint is "+String.format("%.01f",ValueConverter.dewPoint(raw.getOutsideTemp(), raw.getOutsideHum())));




    }





}
