package com.avans;

public class ValueConverter {

    /**
     * airPressure
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De luchtdruk in hPa
     */
    public static double airPressure(short rawValue) {
        double calc = 0.03386389*((double)rawValue /1000 );
        return Math.round(calc * 1000)/1000;
    }



    /**
     * temperature
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De temperatuur in graden Celcius
     */
    public static double temperature(short rawValue) {
        return Math.round(((double)rawValue - 32) / 1.8 *100)*100 ;
    }



    /**
     * humidity
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De relatieve luchtvochtigheid in procenten
     */
    public static double humidity(short rawValue) {
        double calc = (double) rawValue;
        return calc;
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
        double calc = (double) rawValue;
        return calc;
    }



    /**
     * rainMeter
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De hoeveelheid regen in mm
     */
    public static double rainMeter(short rawValue) {

    }



    /**
     * uvIndex
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De uv index (dimensieloos)
     */
    public static double uvIndex(short rawValue) {

    }



    /**
     * batteryLevel
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return De batterijspanning in Volt
     */
    public static double batteryLevel(short rawValue) {

    }



    /**
     * sunRise
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return Zonsopkomst in hh:mm notatie
     */
    public static String sunRise(short rawValue) {

    }



    /**
     * sunSet
     *
     * @param rawValue Ruwe meetwaarde van het vp2pro weerstation
     * @return Zonsondergang in hh:mm notatie
     */
    public static String sunSet(short rawValue) {

    }



}
