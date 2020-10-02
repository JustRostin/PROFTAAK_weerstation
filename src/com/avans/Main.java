package com.avans;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    IO.init();
	    clearDM();


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



}
