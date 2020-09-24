package com.avans;


public class ValueConverterTest {





    public static void main(String[] args) {
        ValueConverter Converter = new ValueConverter();
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
        System.out.println("Luchtdruk is "+String.format("%.01f", Converter.airPressure(raw.getBarometer())));
        System.out.println("InsideTemp is "+ String.format("%.01f", Converter.temperature(raw.getInsideTemp())));
        System.out.println("InsideHum is "+Converter.humidity(raw.getInsideHum()));
        System.out.println("OutsideTemp is "+String.format("%.01f",Converter.temperature(raw.getOutsideTemp())));
        System.out.println("WindSpeed is "+String.format("%.01f",Converter.windSpeed(raw.getWindSpeed())));
        System.out.println("AvgWindSpeed is "+String.format("%.01f",Converter.windSpeed(raw.getAvgWindSpeed())));
        System.out.println("WindDir is "+Converter.windDirection(raw.getWindDir()));
        System.out.println("OutsideHum is "+Converter.humidity(raw.getOutsideHum()));
        System.out.println("RainRate is "+Converter.rainMeter(raw.getRainRate()));
        System.out.println("UVLevel is "+Converter.uvIndex(raw.getUVLevel()));
        System.out.println("BattLevel is "+Converter.batteryLevel(raw.getBattLevel()));
        System.out.println("Sunrise is "+Converter.sunRise(raw.getSunrise()));
        System.out.println("Sunset is "+Converter.sunSet(raw.getSunset()));
        System.out.println("Heat index is "+String.format("%.01f",Converter.heatIndex(raw.getOutsideTemp(),Converter.humidity(raw.getOutsideHum()))));
        System.out.println("Windchill is "+String.format("%.01f",Converter.windChill(raw.getOutsideTemp(), raw.getWindSpeed())));
        System.out.println("Dewpoint is "+String.format("%.01f",Converter.dewPoint(raw.getOutsideTemp(), raw.getOutsideHum())));




    }





}
