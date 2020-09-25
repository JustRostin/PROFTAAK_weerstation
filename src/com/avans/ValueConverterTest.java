package com.avans;


import java.util.ArrayList;

public class ValueConverterTest {





    public static void main(String[] args) {


        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        System.out.println(measurement);






    }





}
