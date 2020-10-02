package com.avans;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.util.*;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
	    menu();


    }

    public static void clearDM(){

        IO.writeShort(0x40,0xFE);
        IO.writeShort(0x40,0x01);
    }
    public static void text(){
        clearDM();
        IO.writeShort(0x40, 'D');IO.delay(100);
        IO.writeShort(0x40, 'O');IO.delay(100);
        IO.writeShort(0x40, 'E');IO.delay(100);
        IO.writeShort(0x40, ' ');IO.delay(100);
        IO.writeShort(0x40, 'D');IO.delay(100);
        IO.writeShort(0x40, 'I');IO.delay(100);
        IO.writeShort(0x40, 'T');IO.delay(100);
        IO.writeShort(0x40, ' ');IO.delay(100);
        IO.writeShort(0x40, 'M');IO.delay(100);
        IO.writeShort(0x40, 'A');IO.delay(100);
        IO.writeShort(0x40, 'A');IO.delay(100);
        IO.writeShort(0x40, 'R');IO.delay(100);
        IO.writeShort(0x40, ' ');IO.delay(100);
        IO.writeShort(0x40, 'E');IO.delay(100);
        IO.writeShort(0x40, 'E');IO.delay(100);
        IO.writeShort(0x40, 'N');IO.delay(100);
        IO.writeShort(0x40, 'S');IO.delay(100);
        IO.writeShort(0x40, ' ');IO.delay(100);
        IO.writeShort(0x40, 'N');IO.delay(100);
        IO.writeShort(0x40, 'A');IO.delay(100);
        IO.writeShort(0x40, '!');IO.delay(100);
    }
    public static void counter(){
        boolean active = true;
        int countone = 0;
        int countten = 0;
        int counthundred = 0;
        int countthoussand = 0;
        int counttenthoussand = 0;

        for (int i = 0; i<100000; i++){
            if(countone == 10){
                countone = 0;
                IO.writeShort(0x10, countone); IO.delay(100);
                countten++;
                if (countten == 10){
                    countten = 0;
                    IO.writeShort(0x12, countten);
                    counthundred++;
                    if (counthundred == 10){
                        counthundred = 0;
                        IO.writeShort(0x14, counthundred);
                        countthoussand++;
                        if (countthoussand == 10){
                            countthoussand = 0;
                            IO.writeShort(0x16, countthoussand);
                            counttenthoussand++;
                            IO.writeShort(0x18, counttenthoussand);
                        }else{
                            IO.writeShort(0x16, countthoussand);
                        }
                    } else{
                        IO.writeShort(0x14, counthundred);
                    }
                } else{
                    IO.writeShort(0x12, countten);
                }
            } else {

                IO.writeShort(0x10,countone); IO.delay(100);
                countone++;
            }
        }
    }
    public static void countOne(){
        int count = 0;
        while (count < 3 ){
            for (int i = 0; i <= 9; i ++){
                IO.writeShort(0x10, i);
                IO.delay(300);
            }
            count++;
        }
    }
    public static void countTop(){
        int count = 0;
        while (count < 3 ){
            for (int i = 0; i <= 9; i ++){
                IO.writeShort(0x18, i);
                IO.writeShort(0x16, i);
                IO.writeShort(0x14, i);
                IO.writeShort(0x12, i);
                IO.writeShort(0x10, i);
                IO.delay(300);
            }
            count++;
        }
    }
    public static void pixelTest() {
        int opcode = 3;
        // opcode is 3, maar moet wel 12 plaatsen naar links worden geschoven
        IO.writeShort(0x42, opcode << 12); // Clear display
        opcode = 1;

        for (int x = 0; x < 128; x++){
            IO.writeShort(0x42, opcode << 12 | x << 5 | 16);
        }
        for (int y = 0; y < 32; y++){
            IO.writeShort(0x42, opcode << 12 | 64 << 5 | y);

        }
        for (int x = 0; x < 128; x++){
            double math = 0.1 * (double)x;
            int y = (int)Math.pow(math,2)-10;
            if (x < 0 || x> 127 ||y< 0 || y >31) {}else{
                IO.writeShort(0x42, opcode << 12 | x << 5 | y);
                IO.delay(50);
            }
        }
    }



//opdrachtvideo 5
    public static boolean isKnopRoodIngedrukt() {
        if ( IO.readShort(0x80) != 0 ) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isKnopBlauwLinksIngedrukt() {
        if ( IO.readShort(0x90) != 0 ) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isKnopBlauwRechtsIngedrukt() {
        if ( IO.readShort(0x100) != 0 ) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean O1_tellerMetKnopBediening() {

        IO.writeShort(0x10, 0);
        IO.writeShort(0x12, 0);
        IO.writeShort(0x14, 0);
        IO.writeShort(0x16, 0);
        IO.writeShort(0x18, 0);
        int countone = 0;
        int countten = 0;
        int counthundred = 0;
        int countthoussand = 0;
        int counttenthoussand = 0;



        for (int i = 0; i<100000; i++){
            if(countone == 10){
                countone = 0;
                IO.writeShort(0x10, countone); IO.delay(100);
                countten++;

                if (countten == 10){
                    countten = 0;
                    IO.writeShort(0x12, countten);
                    counthundred++;

                    if (counthundred == 10){
                        counthundred = 0;
                        IO.writeShort(0x14, counthundred);
                        countthoussand++;

                        if (countthoussand == 10){
                            countthoussand = 0;
                            IO.writeShort(0x16, countthoussand);
                            counttenthoussand++;
                            IO.writeShort(0x18, counttenthoussand);

                        }else{
                            IO.writeShort(0x16, countthoussand);
                        }
                    } else{
                        IO.writeShort(0x14, counthundred);
                    }
                } else{
                    IO.writeShort(0x12, countten);
                }
            } else if (countone == -1) {
                countone = 9;
                IO.writeShort(0x10, countone); IO.delay(100);
                countten--;

                if (countten == -1){
                    countten = 9;
                    IO.writeShort(0x12, countten);
                    counthundred--;

                    if (counthundred == -1){
                        counthundred = 9;
                        IO.writeShort(0x14, counthundred);
                        countthoussand--;

                        if (countthoussand == -1){
                            countthoussand = 9;
                            IO.writeShort(0x16, countthoussand);
                            counttenthoussand--;
                            if (counttenthoussand < 0) {
                                counttenthoussand = 9;
                            }
                            IO.writeShort(0x18, counttenthoussand);

                        }else{
                            IO.writeShort(0x16, countthoussand);
                        }
                    } else{
                        IO.writeShort(0x14, counthundred);
                    }
                } else{
                    IO.writeShort(0x12, countten);
                }
            } else {
                IO.writeShort(0x10,countone); IO.delay(100);
                if (isKnopBlauwRechtsIngedrukt()) {
                    countone--;
                } else {
                    countone++;
                }
            }

            while (isKnopRoodIngedrukt()){
                System.out.println("waiting");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return true;
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
        IO.init();
        clearDM();
        if (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {
            writeText("Druk alle knoppen uit");
        }
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        clearDM();
        writeText("Welkom bij Weermenu!");
        writeNewLine();
        writeText("Blauw: Scrollen");
        writeNewLine();
        writeText("Rood: Afsluiten");
        while (!(isKnopRoodIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt())) {}
        DisplayTemp();
    }

    private static void DisplayTemp() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Temperatuur");
        writeNewLine();
        String tempbinnen = "Binnen: "+ String.format("%.01f",measurement.insideTempConvert());
        writeText(tempbinnen);
        writeNewLine();
        String tempbuiten = "Buiten: "+ String.format("%.01f",measurement.outsideTempConvert());
        writeText(tempbuiten);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayHum();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayBat();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayHum() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Luchtvochtigheid");
        writeNewLine();
        String humbinnen = "Binnen: "+ String.format("%.01f",measurement.insideHumConvert())+"%";
        writeText(humbinnen);
        writeNewLine();
        String humbuiten = "Buiten: "+ String.format("%.01f",measurement.outsideHumConvert())+"%";
        writeText(humbuiten);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayBar();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayTemp();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayBar() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Luchtdruk");
        writeNewLine();
        String luchtdruk = String.format("%.01f",measurement.barometerConvert())+"millibar";
        writeText(luchtdruk);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayWindSpeed();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayHum();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayWindSpeed() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Wind snelheid");
        writeNewLine();
        String snelheidnu = "Actueel: "+ String.format("%.01f",measurement.windSpeedConvert())+"km/h";
        writeText(snelheidnu);
        writeNewLine();
        String snelheidgem = "Gemiddelde: "+ String.format("%.01f",measurement.avgWindSpeedConvert())+"km/h";
        writeText(snelheidgem);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayWindDir();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayBar();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayWindDir() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Windrichting");
        writeNewLine();
        String richting = "Richting: "+ String.format("%.01f",measurement.windDirConvert())+" Deg";
        writeText(richting);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayRain();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayWindSpeed();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayRain() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Regenval");
        writeNewLine();
        String regen = String.format("%.01f",measurement.rainRateConvert())+" mm/h";
        writeText(regen);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayUV();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayWindDir();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayUV() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("UV Niveau");
        writeNewLine();
        String level = String.format("%.01f",measurement.UVLevelConvert());
        writeText(level);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplaySun();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayRain();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplaySun() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Zon");
        writeNewLine();
        String sunrise = "Zonsopkomst: "+ measurement.sunriseConvert();
        writeText(sunrise);
        writeNewLine();
        String sunset = "Zonsondergang: "+ measurement.sunsetConvert();
        writeText(sunset);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayBat();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplayUV();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayBat() {
        RawMeasurement raw = DatabaseConnection.getMostRecentMeasurement();
        Measurement measurement = new Measurement(raw);
        clearDM();
        writeText("Batterij");
        writeNewLine();
        String batt = "Batterij: "+ String.format("%.01f",measurement.battLevelConvert())+" Volt";
        writeText(batt);
        while (isKnopBlauwRechtsIngedrukt() || isKnopBlauwLinksIngedrukt() || isKnopRoodIngedrukt()) {}
        while (!(isKnopBlauwLinksIngedrukt() || isKnopBlauwRechtsIngedrukt() || isKnopRoodIngedrukt())) {}
        if (isKnopBlauwRechtsIngedrukt()) {
            DisplayTemp();
        } else if (isKnopBlauwLinksIngedrukt()){
            DisplaySun();
        } else if (isKnopRoodIngedrukt()) {
            DisplayQuit();
        }
    }

    private static void DisplayQuit() {
        clearDM();
        writeText("Afsluiten...");
        IO.delay(500);
        clearDM();
        writeNewLine();
        writeText("Afgesloten");
    }

}
