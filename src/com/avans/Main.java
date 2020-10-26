package com.avans;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        IO.init();
        menu();
    }

    public static void menu() {
        GUI.clearDM();
        if (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {
            GUI.writeText("Druk alle knoppen uit");
        }
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        GUI.clearDM();
        GUI.writeText("Welkom bij Weermenu!");
        GUI.writeNewLine();
        GUI.writeText("Blauw: Scrollen");
        GUI.writeNewLine();
        GUI.writeText("Rood: Selecteren");
        while (!(GUI.isKnopRoodIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt())) {}
        SelectStartMonth(LocalDate.now().minus(java.time.Period.ofDays(7)));
    }

    private static void SelectStart(LocalDate start) {
        GUI.clearDM();
        int year = start.getYear();
        int month = start.getMonthValue();
        int day = start.getDayOfMonth();
        int[] addressyear = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < addressyear.length; i++){
            GUI.writeNumber(addressyear[i],(year%10),false);
            year = year / 10;
        }
        int[] addressmonth = {0x30,0x32};
        for (int i = 0; i < addressmonth.length; i++){
            GUI.writeNumber(addressmonth[i],(month%10),false);
            month = month / 10;
        }
        int[] addressday = {0x20,0x22};
        for (int i = 0; i < addressday.length; i++){
            GUI.writeNumber(addressday[i],(day%10),false);
            day = day / 10;
        }
        GUI.writeText("Selecteer start Dag.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectStart(start.plusDays(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectStart(start.minusDays(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStartYear(start);
        }
    }
    private static void SelectStartMonth(LocalDate start) {
        GUI.clearDM();
        int year = start.getYear();
        int month = start.getMonthValue();
        int day = start.getDayOfMonth();
        int[] addressyear = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < addressyear.length; i++){
            GUI.writeNumber(addressyear[i],(year%10),false);
            year = year / 10;
        }
        int[] addressmonth = {0x30,0x32};
        for (int i = 0; i < addressmonth.length; i++){
            GUI.writeNumber(addressmonth[i],(month%10),false);
            month = month / 10;
        }
        int[] addressday = {0x20,0x22};
        for (int i = 0; i < addressday.length; i++){
            GUI.writeNumber(addressday[i],(day%10),false);
            day = day / 10;
        }
        GUI.writeText("Selecteer start Maand.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectStartMonth(start.plusMonths(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectStartMonth(start.minusMonths(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(start);
        }
    }
    private static void SelectStartYear(LocalDate start) {
        GUI.clearDM();
        int year = start.getYear();
        int month = start.getMonthValue();
        int day = start.getDayOfMonth();
        int[] addressyear = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < addressyear.length; i++){
            GUI.writeNumber(addressyear[i],(year%10),false);
            year = year / 10;
        }
        int[] addressmonth = {0x30,0x32};
        for (int i = 0; i < addressmonth.length; i++){
            GUI.writeNumber(addressmonth[i],(month%10),false);
            month = month / 10;
        }
        int[] addressday = {0x20,0x22};
        for (int i = 0; i < addressday.length; i++){
            GUI.writeNumber(addressday[i],(day%10),false);
            day = day / 10;
        }
        GUI.writeText("Selecteer start Jaar.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectStartYear(start.plusYears(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectStartYear(start.minusYears(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectEndMonth(start, LocalDate.now());
        }
    }

    private static void SelectEnd(LocalDate start, LocalDate end) {
        GUI.clearDM();
        int year = end.getYear();
        int month = end.getMonthValue();
        int day = end.getDayOfMonth();
        int[] addressyear = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < addressyear.length; i++){
            GUI.writeNumber(addressyear[i],(year%10),false);
            year = year / 10;
        }
        int[] addressmonth = {0x30,0x32};
        for (int i = 0; i < addressmonth.length; i++){
            GUI.writeNumber(addressmonth[i],(month%10),false);
            month = month / 10;
        }
        int[] addressday = {0x20,0x22};
        for (int i = 0; i < addressday.length; i++){
            GUI.writeNumber(addressday[i],(day%10),false);
            day = day / 10;
        }
        GUI.writeText("Selecteer eind Dag.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectEnd(start, end.plusDays(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectEnd(start, end.minusDays(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectEndYear(start, end);
        }
    }
    private static void SelectEndMonth(LocalDate start, LocalDate end) {
        GUI.clearDM();
        int year = end.getYear();
        int month = end.getMonthValue();
        int day = end.getDayOfMonth();
        int[] addressyear = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < addressyear.length; i++){
            GUI.writeNumber(addressyear[i],(year%10),false);
            year = year / 10;
        }
        int[] addressmonth = {0x30,0x32};
        for (int i = 0; i < addressmonth.length; i++){
            GUI.writeNumber(addressmonth[i],(month%10),false);
            month = month / 10;
        }
        int[] addressday = {0x20,0x22};
        for (int i = 0; i < addressday.length; i++){
            GUI.writeNumber(addressday[i],(day%10),false);
            day = day / 10;
        }
        GUI.writeText("Selecteer eind Maand.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectEndMonth(start, end.plusMonths(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectEndMonth(start, end.minusMonths(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectEnd(start, end);
        }
    }
    private static void SelectEndYear(LocalDate start, LocalDate end) {
        GUI.clearDM();
        int year = end.getYear();
        int month = end.getMonthValue();
        int day = end.getDayOfMonth();
        int[] addressyear = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < addressyear.length; i++){
            GUI.writeNumber(addressyear[i],(year%10),false);
            year = year / 10;
        }
        int[] addressmonth = {0x30,0x32};
        for (int i = 0; i < addressmonth.length; i++){
            GUI.writeNumber(addressmonth[i],(month%10),false);
            month = month / 10;
        }
        int[] addressday = {0x20,0x22};
        for (int i = 0; i < addressday.length; i++){
            GUI.writeNumber(addressday[i],(day%10),false);
            day = day / 10;
        }
        GUI.writeText("Selecteer eind Jaar.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectEndYear(start, end.plusYears(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectEndYear(start, end.minusYears(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectPeriod(start, end, false);
        }
    }

    private static void SelectPeriod(LocalDate start, LocalDate end, boolean reselect){
        if (reselect) {
            SelectStartMonth(LocalDate.now().minus(java.time.Period.ofDays(7)));
        } else {
            GUI.clearDM();
            GUI.writeText("Laden ...");
            GUI.writeNewLine();
            Period period = new Period(start, end);
            SelectDisplay(0, period);
        }
    }

    private static void SelectDisplay(int DisplayNumber, Period period) {
        if (DisplayNumber < 0) { DisplayNumber = 16; }
        if (DisplayNumber > 16) { DisplayNumber = 0; }
        switch(DisplayNumber) {
            case 0:
                DisplayTemp(period, DisplayNumber);
                break;
            case 1:
                DisplayHum(period, DisplayNumber);
                break;
            case 2:
                DisplayPres(period, DisplayNumber);
                break;
            case 3:
                DisplayWindSpeed(period, DisplayNumber);
                break;
            case 4:
                DisplayWindDir(period, DisplayNumber);
                break;
            case 5:
                DisplayRain(period, DisplayNumber);
                break;
            case 6:
                DisplayUV(period, DisplayNumber);
                break;
            case 7:
                DisplaySun(period, DisplayNumber);
                break;
            case 8:
                DisplayBat(period, DisplayNumber);
                break;
            case 9:
                DisplayMaxContRain(period, DisplayNumber);
                break;
            case 10:
                DisplayWettestMonth(period, DisplayNumber);
                break;
            case 11:
                DisplayHeatwave(period, DisplayNumber);
                break;
            case 12:
                DisplayGoodDays(period, DisplayNumber);
                break;
            case 13:
                DisplayDegreeDays(period, DisplayNumber);
                break;
            case 14:
                DisplayWeightedDegreeDays(period, DisplayNumber);
                break;
            case 15:
                DisplayQuit(period, DisplayNumber);
                break;
            case 16:
                DisplayPeriodChange(period, DisplayNumber);
                break;


            default:
                DisplayQuit(period, DisplayNumber);
        }
    }

    private static void DisplayTemp(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(measurement.insideTempConvert()*10),1);
        GUI.writeValue("Right",(int)Math.round(measurement.outsideTempConvert()*10),1);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Huidige temperatuur");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayTempLowest(period, DisplayNumber);
        }
    }
    private static void DisplayTempLowest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getLowestTemp()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getLowestTempOut()*10),1);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Laagste temperatuur");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayTempHighest(period, DisplayNumber);
        }
    }
    private static void DisplayTempHighest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getHighestTemp()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getHighestTempOut()*10),1);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Hoogste temperatuur");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayTempStatsIn(period, DisplayNumber);
        }
    }
    private static void DisplayTempStatsIn(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("Temp")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("Temp")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Binnen temperatuur");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayTempStatsOut(period, DisplayNumber);
        }
    }
    private static void DisplayTempStatsOut(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("TempOut")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("TempOut")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Buiten temperatuur");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayTempStatsSD(period, DisplayNumber);
        }
    }
    private static void DisplayTempStatsSD(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getStandardDeviation("Temp")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getStandardDeviation("TempOut")*10),1);
        GUI.writeText("Binnen      Buiten");
        GUI.writeNewLine();
        GUI.writeText("Standaarddeviatie");
        GUI.writeNewLine();
        GUI.writeText("Temperatuur periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayTemp(period, DisplayNumber);
        }
    }


    private static void DisplayHum(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(measurement.insideHumConvert()*10),1);
        GUI.writeValue("Right",(int)Math.round(measurement.outsideHumConvert()*10),1);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Luchtvochtigheid");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayHumLowest(period, DisplayNumber);
        }
    }
    private static void DisplayHumLowest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getLowestHum()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getLowestHumOut()*10),1);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Laagste Luchtvochtigheid");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayHumHighest(period, DisplayNumber);
        }
    }
    private static void DisplayHumHighest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getHighestHum()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getHighestHumOut()*10),1);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Hoogste Luchtvochtigheid");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayHumStatsIn(period, DisplayNumber);
        }
    }
    private static void DisplayHumStatsIn(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("Hum")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("Hum")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Binnen luchtvochtigheid");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayHumStatsOut(period, DisplayNumber);
        }
    }
    private static void DisplayHumStatsOut(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("HumOut")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("HumOut")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Buiten luchtvochtigheid");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayHumStatsSD(period, DisplayNumber);
        }
    }
    private static void DisplayHumStatsSD(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getStandardDeviation("Hum")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getStandardDeviation("HumOut")*10),1);
        GUI.writeText("Binnen      Buiten");
        GUI.writeNewLine();
        GUI.writeText("Standaarddeviatie");
        GUI.writeNewLine();
        GUI.writeText("Luchtvochtigheid periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayHum(period, DisplayNumber);
        }
    }


    private static void DisplayPres(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Top",(int)Math.round(measurement.barometerConvert()*10),1);
        GUI.writeText("Luchtdruk in bar");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayPresHighest(period, DisplayNumber);
        }
    }
    private static void DisplayPresHighest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getLowestPress()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getHighestPress()*10),1);
        GUI.writeText("Laagste     hoogste");
        GUI.writeNewLine();
        GUI.writeText("Luchtdruk");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayPresStats(period, DisplayNumber);
        }
    }
    private static void DisplayPresStats(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("Press")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("Press")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Luchtdruk");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayPresStatsSD(period, DisplayNumber);
        }
    }
    private static void DisplayPresStatsSD(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getStandardDeviation("Press")*10),1);
        GUI.writeText("Standaarddeviatie");
        GUI.writeNewLine();
        GUI.writeText("Luchtdruk periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayPres(period, DisplayNumber);
        }
    }


    private static void DisplayWindSpeed(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(measurement.windSpeedConvert()*10),1);
        GUI.writeValue("Right",(int)Math.round(measurement.avgWindSpeedConvert()*10),1);
        GUI.writeText("Huidig     Gemiddelde");
        GUI.writeNewLine();
        GUI.writeText("Windsnelheid in km/u");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayWindHighest(period, DisplayNumber);
        }
    }
    private static void DisplayWindHighest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getLowestWind()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getHighestWind()*10),1);
        GUI.writeText("Laagste     hoogste");
        GUI.writeNewLine();
        GUI.writeText("Windsnelheid");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayWindStats(period, DisplayNumber);
        }
    }
    private static void DisplayWindStats(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("Wind")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("Wind")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Windsnelheid");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayWindStatsSD(period, DisplayNumber);
        }
    }
    private static void DisplayWindStatsSD(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getStandardDeviation("Wind")*10),1);
        GUI.writeText("Standaarddeviatie");
        GUI.writeNewLine();
        GUI.writeText("Windsnelheid periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayWindSpeed(period, DisplayNumber);
        }
    }


    private static void DisplayWindDir(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Top",(int)Math.round(measurement.windDirConvert()*10),1);
        GUI.writeText("Windrichting in");
        GUI.writeNewLine();
        GUI.writeText("Graden");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayRain(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(measurement.rainRateConvert()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMaxContRain()*10),1);
        GUI.writeText("Regen   ");
        GUI.writeNewLine();
        GUI.writeText("Huidig   Grootste bui");
        GUI.writeNewLine();
        GUI.writeText("mm/u       mm");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayRainHighest(period, DisplayNumber);
        }
    }
    private static void DisplayRainHighest(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getLowestRainrate()*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getHighestRainrate()*10),1);
        GUI.writeText("Laagste     hoogste");
        GUI.writeNewLine();
        GUI.writeText("Regenval mm/u");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayRainStats(period, DisplayNumber);
        }
    }
    private static void DisplayRainStats(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getModus("Rainrate")*10),1);
        GUI.writeValue("Right",(int)Math.round(period.getMedian("Rainrate")*10),1);
        GUI.writeText("Modus      mediaan");
        GUI.writeNewLine();
        GUI.writeText("Regenval mm/u");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayRainStatsSD(period, DisplayNumber);
        }
    }
    private static void DisplayRainStatsSD(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(period.getStandardDeviation("Rainrate")*10),1);
        GUI.writeText("Standaarddeviatie");
        GUI.writeNewLine();
        GUI.writeText("Regenval mm/u");
        GUI.writeNewLine();
        GUI.writeText("Van periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            DisplayRain(period, DisplayNumber);
        }
    }

    private static void DisplayUV(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(measurement.windDirConvert()*10),1);
        GUI.writeText("UV niveau");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplaySun(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeText("Zon");
        GUI.writeNewLine();
        String sunrise = "Zonsopkomst: "+ measurement.sunriseConvert();
        GUI.writeText(sunrise);
        GUI.writeNewLine();
        String sunset = "Zonsondergang: "+ measurement.sunsetConvert();
        GUI.writeText(sunset);
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayBat(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        GUI.writeValue("Left",(int)Math.round(measurement.battLevelConvert()*10),2);
        GUI.writeValue("Right",(int)Math.round(measurement.xmitBattConvert()*10),2);
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Huidige Batterij");
        GUI.writeNewLine();
        GUI.writeText("Niveau");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayMaxContRain(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeValue("Top",(int)Math.round(period.getMaxContRain()*10),1);
        GUI.writeText("Grootste regenval");
        GUI.writeNewLine();
        GUI.writeText("Van deze periode");
        GUI.writeNewLine();
        GUI.writeText("In mm");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayHeatwave(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeText("Periode heeft");
        GUI.writeNewLine();
        if (period.heatwave()){
            GUI.writeText("Wel een");
            GUI.writeNewLine();
        } else {
            GUI.writeText("Geen");
            GUI.writeNewLine();
        }
        GUI.writeText("Hittegolf");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayDegreeDays(Period period, int DisplayNumber){
        GUI.clearDM();
        GUI.writeText("Graaddagen processing...");
        GUI.writeNewLine();
        int result = period.getDegreeDays();
        GUI.clearDM();
        GUI.writeValue("Top", result, 0);
        GUI.writeText("Het aantal");
        GUI.writeNewLine();
        GUI.writeText("graaddagen van de");
        GUI.writeNewLine();
        GUI.writeText("geselecteerde periode");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayWeightedDegreeDays(Period period, int DisplayNumber){
        GUI.clearDM();
        GUI.writeText("Gewogen graaddagen processing...");
        GUI.writeNewLine();
        int result = period.getWeightedDegreeDays();
        GUI.clearDM();
        GUI.writeValue("Top", result, 0);
        GUI.writeText("Het aantal gewogen");
        GUI.writeNewLine();
        GUI.writeText("graaddagen van de");
        GUI.writeNewLine();
        GUI.writeText("geselecteerde periode");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayWettestMonth(Period period, int DisplayNumber){
        GUI.clearDM();
        GUI.writeText("Maand ophalen \nmet de meeste regen");
        GUI.writeNewLine();

        String month = period.getWettestMonth();
        String[] elements = month.split(",",0);
        GUI.clearDM();
        double amount = Double.parseDouble(elements[2]);
        amount = amount;
        System.out.println(amount);
        int length = String.valueOf((int)amount).length();
        System.out.println(length);
        if (length < 5){
            GUI.writeValue("Top",(int)amount*10,1);
            GUI.writeText("Meeste regen in MM\n");
            GUI.writeText("Maand:"+elements[0]+"\n");
            GUI.writeText("Jaar: "+elements[1]+"\n");
        } else if (length == 5) {
            int first = (int)amount*10;
            while (first >= 1000)
                first /= 1000;
            int last = (int)amount*10 %1000;
            GUI.writeValue("Left",first,-1);
            GUI.writeValue("Right",last,1);
            GUI.writeText("Meeste regen in MM\n");
            GUI.writeText("Maand:"+elements[0]+"\n");
            GUI.writeText("Jaar: "+elements[1]+"\n");
        }

        GUI.writeText("Gegevens buiten het bereik");

        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }

    private static void DisplayGoodDays(Period period, int DisplayNumber) {
        GUI.clearDM(); // Dit leegt het scherm.
        GUI.writeText("Goede Dagen Laden......."); // Klein laad tekstje dat aangeeft dat de functie bezig is.
        GUI.writeNewLine(); // Dit zorgt ervoor dat er op de volgende lijn van de dotmatrix wordt geschreven.

        int goodDayscount = period.getGoodDays(); // Hiermee zorg ik ervoor dat mijn functie start en dat de return resultaat in goodDayscount komt te staan.
        String goodDays = "Goede Dagen: " + goodDayscount; // Ik maak een string om te laten zien op het dotmatrix scherm waarin de goodDayscount verwerkt is.
        GUI.clearDM();
        GUI.writeText(goodDays);
        GUI.writeNewLine();

        GUI.writeValue("Left", goodDayscount, 0); // Ik gebruik een functie gemaakt door Raoul om de goodDayscount ook nog te laten zien op de 7 segment display

        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectDisplay(0,period);
        }
    }


    private static void DisplayQuit(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeText("Afsluiten?");
        GUI.writeNewLine();
        GUI.writeText("Blauw verder scrollen");
        GUI.writeNewLine();
        GUI.writeText("Rood is afsluiten");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            Quit();
        }
    }
    private static void DisplayPeriodChange(Period period, int DisplayNumber) {
        GUI.clearDM();
        GUI.writeText("Periode veranderen?");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {IO.delay(100);}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {IO.delay(100);}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectPeriod(LocalDate.now(), LocalDate.now(), true);
        }
    }

    public static void Quit() {
        GUI.clearDM();
        GUI.writeText("Afsluiten...");
        IO.delay(500);
        GUI.clearDM();
        GUI.writeNewLine();
        GUI.writeText("Afgesloten");
    }

}
