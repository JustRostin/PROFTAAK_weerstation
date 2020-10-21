package com.avans;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
//        IO.init();
//	    menu();
        Period p = new Period(LocalDate.now().minus(java.time.Period.ofYears(1)), LocalDate.now());
        p.getmode();
    }

    public static void writeText(String text){
        // Creating array of string length
        char[] ch = new char[text.length()];

        // Copy character by character into array
        for (int i = 0; i < text.length(); i++) {
            ch[i] = text.charAt(i);
        }
        // Printing content of array to screen
        for (char c : ch) {
            IO.writeShort(0x40, c);
        }
    }
    public static void writeNewLine() {
        IO.writeShort(0x40, '\n');
    }

    public static void menu() {
        GUI.clearDM();
        if (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {
            writeText("Druk alle knoppen uit");
        }
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        GUI.clearDM();
        writeText("Welkom bij Weermenu!");
        writeNewLine();
        writeText("Blauw: Scrollen");
        writeNewLine();
        writeText("Rood: Afsluiten");
        while (!(GUI.isKnopRoodIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt())) {}
        DisplayTemp();
    }

    private static void DisplayTemp() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Temperatuur");
        writeNewLine();
        String tempbinnen = "Binnen: "+ String.format("%.01f",measurement.insideTempConvert());
        writeText(tempbinnen);
        writeNewLine();
        String tempbuiten = "Buiten: "+ String.format("%.01f",measurement.outsideTempConvert());
        writeText(tempbuiten);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayHum();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayBat();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayHum() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Luchtvochtigheid");
        writeNewLine();
        String humbinnen = "Binnen: "+ String.format("%.01f",measurement.insideHumConvert())+"%";
        writeText(humbinnen);
        writeNewLine();
        String humbuiten = "Buiten: "+ String.format("%.01f",measurement.outsideHumConvert())+"%";
        writeText(humbuiten);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayBar();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayTemp();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayBar() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Luchtdruk");
        writeNewLine();
        String luchtdruk = String.format("%.01f",measurement.barometerConvert())+"millibar";
        writeText(luchtdruk);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayWindSpeed();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayHum();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayWindSpeed() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Wind snelheid");
        writeNewLine();
        String snelheidnu = "Actueel: "+ String.format("%.01f",measurement.windSpeedConvert())+"km/h";
        writeText(snelheidnu);
        writeNewLine();
        String snelheidgem = "Gemiddelde: "+ String.format("%.01f",measurement.avgWindSpeedConvert())+"km/h";
        writeText(snelheidgem);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayWindDir();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayBar();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayWindDir() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Windrichting");
        writeNewLine();
        String richting = "Richting: "+ String.format("%.01f",measurement.windDirConvert())+" Deg";
        writeText(richting);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayRain();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayWindSpeed();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayRain() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Regenval");
        writeNewLine();
        String regen = String.format("%.01f",measurement.rainRateConvert())+" mm/h";
        writeText(regen);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayUV();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayWindDir();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayUV() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("UV Niveau");
        writeNewLine();
        String level = String.format("%.01f",measurement.UVLevelConvert());
        writeText(level);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplaySun();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayRain();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplaySun() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Zon");
        writeNewLine();
        String sunrise = "Zonsopkomst: "+ measurement.sunriseConvert();
        writeText(sunrise);
        writeNewLine();
        String sunset = "Zonsondergang: "+ measurement.sunsetConvert();
        writeText(sunset);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayBat();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplayUV();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayBat() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        GUI.clearDM();
        writeText("Batterij");
        writeNewLine();
        String batt = "Batterij: "+ String.format("%.01f",measurement.battLevelConvert())+" Volt";
        writeText(batt);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            DisplayTemp();
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            DisplaySun();
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayQuit() {
        GUI.clearDM();
        writeText("Afsluiten...");
        IO.delay(500);
        GUI.clearDM();
        writeNewLine();
        writeText("Afgesloten");
    }

}
