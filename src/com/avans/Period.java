package com.avans;

import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class Period {

    private LocalDate beginDate;
    private LocalDate endDate;

    public Period() {
        beginDate = LocalDate.now();
        endDate = LocalDate.now();
    }

    public Period(LocalDate beginPeriod, LocalDate endPeriod) {
        this.beginDate = beginPeriod;
        this.endDate = endPeriod;
    }

    public Period(LocalDate beginPeriod) {
        this.beginDate = beginPeriod;
        this.endDate = LocalDate.now();
    }

    public Period(int days) {
        this.beginDate = LocalDate.now().minus(java.time.Period.ofDays(days));
        this.endDate = LocalDate.now();
    }

    public void setStart(int day, int month, int year) {
        this.beginDate = LocalDate.of(year, month, day);
    }

    public void setStart(LocalDate beginPeriod) {
        this.beginDate = beginPeriod;
    }

    public void setEnd(int day, int month, int year) {
        this.beginDate = LocalDate.of(year, month, day);
    }

    public void setEnd(LocalDate endPeriod) {
        this.beginDate = endPeriod;
    }

    public ArrayList<RawMeasurement> getRawMeasurements() {
        return DatabaseConnection.getMeasurementsBetween(LocalDateTime.of(beginDate, LocalTime.of(0, 1)), LocalDateTime.of(endDate, LocalTime.of(23, 59)));
    }

    public void getLowest(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        double lowest = -40;
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0) {
                if (measurement.outsideTempConvert() < lowest) {
                    lowest = measurement.outsideTempConvert();
                }
            }
        }
    }

    public void getHighest(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        double highest = 60;
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0) {
                if (measurement.outsideTempConvert() > highest) {
                    highest = measurement.outsideTempConvert();
                }
            }
        }
    }

    public double getAverage(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        double average = -40;
        double total = 0.0;
        int amount = 0;
        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() > -20 && measurement.outsideTempConvert() < 45.0) {
                amount++;
                total += measurement.outsideTempConvert();
            }
        }
        average = total / amount;
        return average;
    }

    public String getmode(RawMeasurement rawmeasurement) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        ArrayList<RawMeasurement> rawMeasurements = getRawMeasurements();
        int mode1 = 0;
        int mode2 = 0;
        int mode3 = 0;
        int mode4 = 0;
        int mode5 = 0;
        int mode6 = 0;
        int mode7 = 0;
        int mode8 = 0;
        int mode9 = 0;
        int mode10 = 0;
        int mode11 = 0;
        int mode12 = 0;
        int mode13 = 0;
        int mode14 = 0;
        int mode15 = 0;
        int mode16 = 0;
        int mode17 = 0;
        int mode18 = 0;
        int mode19 = 0;
        int mode20 = 0;
        int mode21 = 0;
        int mode22 = 0;
        int mode23 = 0;
        int mode24 = 0;
        int mode25 = 0;
        int mode26 = 0;
        int mode27 = 0;
        int mode28 = 0;
        int mode29 = 0;
        int mode30 = 0;
        int mode31 = 0;
        int mode32 = 0;
        int mode33 = 0;

        for (RawMeasurement rawMeasurement : rawMeasurements) {
            Measurement measurement = new Measurement(rawMeasurement);
            if (measurement.outsideTempConvert() >= -20.0 && measurement.outsideTempConvert() < -18.0) {
                mode1++;
            }
            if (measurement.outsideTempConvert() >= -18.0 && measurement.outsideTempConvert() < -16.0) {
                mode2++;
            }
            if (measurement.outsideTempConvert() >= -16.0 && measurement.outsideTempConvert() < -14.0) {
                mode3++;
            }
            if (measurement.outsideTempConvert() >= -14.0 && measurement.outsideTempConvert() < -12.0) {
                mode4++;
            }
            if (measurement.outsideTempConvert() >= -12.0 && measurement.outsideTempConvert() < -10.0) {
                mode5++;
            }
            if (measurement.outsideTempConvert() >= -10.0 && measurement.outsideTempConvert() < -8.0) {
                mode6++;
            }
            if (measurement.outsideTempConvert() >= -8.0 && measurement.outsideTempConvert() < -6.0) {
                mode7++;
            }
            if (measurement.outsideTempConvert() >= -6.0 && measurement.outsideTempConvert() < -4.0) {
                mode8++;
            }
            if (measurement.outsideTempConvert() >= -4.0 && measurement.outsideTempConvert() < -2.0) {
                mode9++;
            }
            if (measurement.outsideTempConvert() >= -2.0 && measurement.outsideTempConvert() < 0) {
                mode10++;
            }
            if (measurement.outsideTempConvert() >= 0 && measurement.outsideTempConvert() < 2) {
                mode11++;
            }
            if (measurement.outsideTempConvert() >= 2 && measurement.outsideTempConvert() < 4) {
                mode12++;
            }
            if (measurement.outsideTempConvert() >= 4 && measurement.outsideTempConvert() < 6) {
                mode13++;
            }
            if (measurement.outsideTempConvert() >= 6 && measurement.outsideTempConvert() < 8) {
                mode14++;
            }
            if (measurement.outsideTempConvert() >= 8 && measurement.outsideTempConvert() < 10) {
                mode15++;
            }
            if (measurement.outsideTempConvert() >= 10 && measurement.outsideTempConvert() < 12) {
                mode16++;
            }
            if (measurement.outsideTempConvert() >= 12 && measurement.outsideTempConvert() < 14) {
                mode17++;
            }
            if (measurement.outsideTempConvert() >= 14 && measurement.outsideTempConvert() < 16) {
                mode18++;
            }
            if (measurement.outsideTempConvert() >= 16 && measurement.outsideTempConvert() < 18) {
                mode19++;
            }
            if (measurement.outsideTempConvert() >= 18 && measurement.outsideTempConvert() < 20) {
                mode20++;
            }
            if (measurement.outsideTempConvert() >= 20 && measurement.outsideTempConvert() < 22) {
                mode21++;
            }
            if (measurement.outsideTempConvert() >= 22 && measurement.outsideTempConvert() < 24) {
                mode22++;
            }
            if (measurement.outsideTempConvert() >= 24 && measurement.outsideTempConvert() < 26) {
                mode23++;
            }
            if (measurement.outsideTempConvert() >= 26 && measurement.outsideTempConvert() < 28) {
                mode24++;
            }
            if (measurement.outsideTempConvert() >= 28 && measurement.outsideTempConvert() < 30) {
                mode25++;
            }
            if (measurement.outsideTempConvert() >= 30 && measurement.outsideTempConvert() < 32) {
                mode26++;
            }
            if (measurement.outsideTempConvert() >= 32 && measurement.outsideTempConvert() < 34) {
                mode27++;
            }
            if (measurement.outsideTempConvert() >= 34 && measurement.outsideTempConvert() < 36) {
                mode28++;
            }
            if (measurement.outsideTempConvert() >= 36 && measurement.outsideTempConvert() < 38) {
                mode29++;
            }
            if (measurement.outsideTempConvert() >= 38 && measurement.outsideTempConvert() < 40) {
                mode30++;
            }
            if (measurement.outsideTempConvert() >= 40 && measurement.outsideTempConvert() < 42) {
                mode31++;
            }
            if (measurement.outsideTempConvert() >= 42 && measurement.outsideTempConvert() < 44) {
                mode32++;
            }
            if (measurement.outsideTempConvert() >= 44 && measurement.outsideTempConvert() < 46) {
                mode33++;
            }

        }
        int most = mode1;
        String value = "-20 and -18";
        if (mode2 > most) {
            most = mode2;
            value = "-18 and -16";
        }
        if (mode3 > most) {
            most = mode3;
            value = "-16 and -14";
        }
        if (mode4 > most) {
            most = mode4;
            value = "-14 and -12";
        }
        if (mode5 > most) {
            most = mode5;
            value = "-12 and -10";
        }
        if (mode6 > most) {
            most = mode6;
            value = "-10 and -8";
        }
        if (mode7 > most) {
            most = mode7;
            value = "-8 and -6";
        }
        if (mode8 > most) {
            most = mode8;
            value = "-6 and -4";
        }
        if (mode9 > most) {
            most = mode9;
            value = "-4 and -2";
        }
        if (mode10 > most) {
            most = mode10;
            value = "-2 and 0";
        }
        if (mode11 > most) {
            most = mode11;
            value = "0 and 2";
        }
        if (mode12 > most) {
            most = mode12;
            value = "2 and 4";
        }
        if (mode13 > most) {
            most = mode13;
            value = "4 and 6";
        }
        if (mode14 > most) {
            most = mode14;
            value = "6 and 8";
        }
        if (mode15 > most) {
            most = mode15;
            value = "8 and 10";
        }
        if (mode16 > most) {
            most = mode16;
            value = "10 and 12";
        }
        if (mode17 > most) {
            most = mode17;
            value = "12 and 14";
        }
        if (mode18 > most) {
            most = mode18;
            value = "14 and 16";
        }
        if (mode19 > most) {
            most = mode19;
            value = "16 and 18";
        }
        if (mode20 > most) {
            most = mode20;
            value = "18 and 20";
        }
        if (mode21 > most) {
            most = mode21;
            value = "20 and 22";
        }
        if (mode22 > most) {
            most = mode22;
            value = "22 and 24";
        }
        if (mode23 > most) {
            most = mode23;
            value = "24 and 26";
        }
        if (mode24 > most) {
            most = mode24;
            value = "26 and 28";
        }
        if (mode25 > most) {
            most = mode25;
            value = "28 and 30";
        }
        if (mode26 > most) {
            most = mode26;
            value = "30 and 32";
        }
        if (mode27 > most) {
            most = mode28;
            value = "32 and 34";
        }
        if (mode29 > most) {
            most = mode29;
            value = "34 and 36";
        }
        if (mode30 > most) {
            most = mode30;
            value = "36 and 38";
        }
        if (mode31 > most) {
            most = mode31;
            value = "38 and 40";
        }
        if (mode32 > most) {
            most = mode32;
            value = "40 and 42";
        }
        if (mode33 > most) {
            value = "42 and 44";
        }

        return "Most occuring temperature was between " + value + " degrees Celsius";

    }
}