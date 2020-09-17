package com.avans;
import java.util.*;
import java.lang.*;
import java.text.*;

public class ValueConverter {

    /**
     * airPressure
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De luchtdruk in hPa
     */
    public static double airPressure(short rawValue) {
        double calc = 0.03386389*((double)rawValue /1000 );
        return Math.round(calc*1000)/1000.0d;
    }



    /**
     * temperature
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De temperatuur in graden Celcius
     */
    public static double temperature(short rawValue) {

        return Math.round((((double)rawValue/10 - 32) / 1.8) *10.0 )/10.0 ;
    }



    /**
     * humidity
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De relatieve luchtvochtigheid in procenten
     */
    public static double humidity(short rawValue) {
        double calc = (double) rawValue;
        return Math.round(calc);
    }



    /**
     * windSpeed
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De windsnelheid in km/h
     */
    public static double windSpeed(short rawValue) {
        return Math.round((int)rawValue*1.609344*100)*100;
    }



    /**
     * windDirection
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De windrichting in graden
     */
    public static double windDirection(short rawValue) {
        return (double) rawValue;
    }



    /**
     * rainMeter
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De hoeveelheid regen in mm
     */
    public static double rainMeter(short rawValue) {
        return (double) rawValue;
    }



    /**
     * uvIndex
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De uv index (dimensieloos)
     */
    public static double uvIndex(short rawValue) {
        return (double)rawValue /10;
    }



    /**
     * batteryLevel
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De batterijspanning in Volt
     */
    public static double batteryLevel(short rawValue) {
        return (((double)rawValue * 300) / 512) / 100.0;
    }



    /**
     * sunRise
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return Zonsopkomst in hh:mm notatie
     */
    public static String sunRise(short rawValue) {
            DecimalFormat df = new DecimalFormat("0.00");
        String time = "Sunrise: " + (String)df.format(rawValue);
        return time;
    }



    /**
     * sunSet
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return Zonsondergang in hh:mm notatie
     */
    public static String sunSet(short rawValue) {
        String rawTime = String.valueOf(rawValue);
        String time = "";
        if (rawTime.length() == 4){
            String minutes = rawTime.substring(3);
            String hour = rawTime.substring(1, 2);
            time = hour + ":" + minutes;
        }
        else if (rawTime.length() == 3){
            String minutes = rawTime.substring(2);
            String hour = rawTime.substring(1);
            time = hour + ":" + minutes;
        }
        return time;
    }

    public static double windChill (short rawValue1, short rawValue2){
        double windChill = 35.74 + 0.6215*rawValue1-35.75*(Math.pow(rawValue2, 0.16))+0.4275*rawValue1*(Math.pow(rawValue2, 0.16));
        return windChill;
    }



}
