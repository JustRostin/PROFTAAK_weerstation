package com.avans;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        //IO.init();
        //menu();
        Period.testjason1();
        Period.testjason2();
    }

    public static void menu() {
        GUI.clearDM();
        if (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {
            GUI.writeText("Druk alle knoppen uit");
        }
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        GUI.clearDM();
        GUI.writeText("Welkom bij Weermenu!");
        GUI.writeNewLine();
        GUI.writeText("Blauw: Scrollen");
        GUI.writeNewLine();
        GUI.writeText("Rood: Selecteren");
        while (!(GUI.isKnopRoodIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt())) {}
        SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
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
        GUI.writeText("Selecteer startdatum.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectStart(start.plusDays(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectStart(start.minusDays(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectEnd(start, LocalDate.now());
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
        GUI.writeText("Selecteer einddatum.");
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectEnd(start, end.plusDays(1));
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectEnd(start, end.minusDays(1));
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectPeriod(start, end, false);
        }
    }

    private static void SelectPeriod(LocalDate start, LocalDate end, boolean reselect){
        if (reselect) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        } else {
            Period period = new Period(start, end);
            SelectDisplay(0, period);
        }
    }

    private static void SelectDisplay(int DisplayNumber, Period period) {
        if (DisplayNumber < 0) { DisplayNumber = 10; }
        if (DisplayNumber > 10) { DisplayNumber = 0; }
        switch(DisplayNumber) {
            case 0:
                DisplayTemp(period, DisplayNumber);
                break;
            case 1:
                DisplayTempStats(period, DisplayNumber);
                break;
            case 2:
                DisplayHum(period, DisplayNumber);
                break;
            case 3:
                DisplayBar(period, DisplayNumber);
                break;
            case 4:
                DisplayWindSpeed(period, DisplayNumber);
                break;
            case 5:
                DisplayWindDir(period, DisplayNumber);
                break;
            case 6:
                DisplayRain(period, DisplayNumber);
                break;
            case 7:
                DisplayUV(period, DisplayNumber);
                break;
            case 8:
                DisplaySun(period, DisplayNumber);
                break;
            case 9:
                DisplayBat(period, DisplayNumber);
                break;
            case 10:
                DisplayQuit(period, DisplayNumber);
                break;
            default:
                DisplayQuit(period, DisplayNumber);
        }
    }

    private static void DisplayTemp(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int binnen = (int)Math.round(measurement.insideTempConvert()*10);
        int[] address = {0x20,0x22,0x24};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(binnen%10),true);
            } else {
                GUI.writeNumber(address[i],(binnen%10),false);
            }
            binnen = binnen / 10;
        }
        int buiten = (int)Math.round(measurement.outsideTempConvert()*10);
        int[] address2 = {0x30,0x32,0x34};
        for (int i = 0; i < address2.length; i++){
            if (i==1){
                GUI.writeNumber(address2[i],(buiten%10),true);
            } else {
                GUI.writeNumber(address2[i],(buiten%10),false);
            }
            buiten = buiten / 10;
        }
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Huidige temperatuur");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }
    private static void DisplayTempStats(Period period, int DisplayNumber) {
        GUI.clearDM();
        int lowest = (int)Math.round(period.getLowestTemp()*10);
        int[] address = {0x20,0x22,0x24};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(lowest%10),true);
            } else {
                GUI.writeNumber(address[i],(lowest%10),false);
            }
            lowest = lowest / 10;
        }
        int highest = (int)Math.round(period.getHighestTemp()*10);
        int[] address2 = {0x30,0x32,0x34};
        for (int i = 0; i < address2.length; i++){
            if (i==1){
                GUI.writeNumber(address2[i],(highest%10),true);
            } else {
                GUI.writeNumber(address2[i],(highest%10),false);
            }
            highest = highest / 10;
        }
        GUI.writeText("Laagste     Hoogste");
        GUI.writeNewLine();
        GUI.writeText("Temperatuur van");
        GUI.writeNewLine();
        GUI.writeText("Periode");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayHum(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int binnen = (int)Math.round(measurement.insideHumConvert()*10);
        int[] address = {0x20,0x22,0x24};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(binnen%10),true);
            } else {
                GUI.writeNumber(address[i],(binnen%10),false);
            }
            binnen = binnen / 10;
        }
        int buiten = (int)Math.round(measurement.outsideHumConvert()*10);
        int[] address2 = {0x30,0x32,0x34};
        for (int i = 0; i < address2.length; i++){
            if (i==1){
                GUI.writeNumber(address2[i],(buiten%10),true);
            } else {
                GUI.writeNumber(address2[i],(buiten%10),false);
            }
            buiten = buiten / 10;
        }
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Luchtvochtigheid");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayBar(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int druk = (int)Math.round(measurement.barometerConvert()*10);
        int[] address = {0x10,0x12,0x14,0x16,0x18};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(druk%10),true);
            } else {
                GUI.writeNumber(address[i],(druk%10),false);
            }
            druk = druk / 10;
        }
        GUI.writeText("Luchtdruk in bar");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayWindSpeed(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int huidig = (int)Math.round(measurement.windSpeedConvert()*10);
        int[] address = {0x20,0x22,0x24};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(huidig%10),true);
            } else {
                GUI.writeNumber(address[i],(huidig%10),false);
            }
            huidig = huidig / 10;
        }
        int Gemiddelde = (int)Math.round(measurement.avgWindSpeedConvert()*10);
        int[] address2 = {0x30,0x32,0x34};
        for (int i = 0; i < address2.length; i++){
            if (i==1){
                GUI.writeNumber(address2[i],(Gemiddelde%10),true);
            } else {
                GUI.writeNumber(address2[i],(Gemiddelde%10),false);
            }
            Gemiddelde = Gemiddelde / 10;
        }
        GUI.writeText("Huidig     Gemiddelde");
        GUI.writeNewLine();
        GUI.writeText("Windsnelheid in km/u");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayWindDir(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int richting = (int)Math.round(measurement.windDirConvert()*10);
        int[] address = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(richting%10),true);
            } else {
                GUI.writeNumber(address[i],(richting%10),false);
            }
            richting = richting / 10;
        }
        GUI.writeText("Windrichting in");
        GUI.writeNewLine();
        GUI.writeText("Graden");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayRain(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int huidig = (int)Math.round(measurement.rainRateConvert()*10);
        int[] address3 = {0x20,0x22,0x24};
        for (int i = 0; i < address3.length; i++){
            if (i==1){
                GUI.writeNumber(address3[i],(huidig%10),true);
            } else {
                GUI.writeNumber(address3[i],(huidig%10),false);
            }
            huidig = huidig / 10;
        }
        int hoogste = (int)Math.round(period.getMaxContRain()*10);
        int[] address = {0x30,0x32,0x34};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(hoogste%10),true);
            } else {
                GUI.writeNumber(address[i],(hoogste%10),false);
            }
            hoogste = hoogste / 10;
        }
        int totaal = (int)Math.round(period.getMaxContRain()*10);
        int[] address2 = {0x10,0x12,0x14,0x16,0x18};
        for (int i = 0; i < address2.length; i++){
            if (i==1){
                GUI.writeNumber(address2[i],(totaal%10),true);
            } else {
                GUI.writeNumber(address2[i],(totaal%10),false);
            }
            totaal = totaal / 10;
        }
        GUI.writeText("Regen   Totaal    ");
        GUI.writeNewLine();
        GUI.writeText("Huidig   Grootste bui");
        GUI.writeNewLine();
        GUI.writeText("mm/u       mm");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayUV(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int uv = (int)Math.round(measurement.windDirConvert()*10);
        int[] address = {0x10,0x12,0x14,0x16};
        for (int i = 0; i < address.length; i++){
            if (i==1){
                GUI.writeNumber(address[i],(uv%10),true);
            } else {
                GUI.writeNumber(address[i],(uv%10),false);
            }
            uv = uv / 10;
        }
        GUI.writeText("UV niveau");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
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
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
        }
    }

    private static void DisplayBat(Period period, int DisplayNumber) {
        Measurement measurement = period.getLatestMeasurement();
        GUI.clearDM();
        int batt = (int)Math.round(measurement.battLevelConvert()*10);
        int[] address = {0x20,0x22,0x24};
        for (int i = 0; i < address.length; i++){
            if (i==2){
                GUI.writeNumber(address[i],(batt%10),true);
            } else {
                GUI.writeNumber(address[i],(batt%10),false);
            }
            batt = batt / 10;
        }
        int xmitt = (int)Math.round(measurement.xmitBattConvert()*10);
        int[] address2 = {0x30,0x32,0x34};
        for (int i = 0; i < address2.length; i++){
            if (i==2){
                GUI.writeNumber(address2[i],(xmitt%10),true);
            } else {
                GUI.writeNumber(address2[i],(xmitt%10),false);
            }
            xmitt = xmitt / 10;
        }
        GUI.writeText("Binnen       Buiten");
        GUI.writeNewLine();
        GUI.writeText("Huidige Batterij");
        GUI.writeNewLine();
        GUI.writeText("Niveau");
        GUI.writeNewLine();
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            SelectStart(LocalDate.now().minus(java.time.Period.ofDays(7)));
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
        while (GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopRoodIngedrukt()) {}
        while (!(GUI.isKnopBlauwLinksIngedrukt() || GUI.isKnopBlauwRechtsIngedrukt() || GUI.isKnopRoodIngedrukt())) {}
        if (GUI.isKnopBlauwRechtsIngedrukt()) {
            SelectDisplay(DisplayNumber+1,period);
        } else if (GUI.isKnopBlauwLinksIngedrukt()){
            SelectDisplay(DisplayNumber-1,period);
        } else if (GUI.isKnopRoodIngedrukt()) {
            Quit();
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
