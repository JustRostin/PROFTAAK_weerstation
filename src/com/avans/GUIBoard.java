package com.avans;

public class GUI {


    public static void main(String[] args) {
        IO.init();
	    menu();


    }

    public static void test(){
        int bit;
        bit = 1001001;
        IO.writeShort(0x10, 0x100 | 0x51);
    }

    public static void clearDM(){

        IO.writeShort(0x10,0xFE);
        IO.writeShort(0x10,0x01);
    }

    public static void clearDMscreen(){
        int opcode = 3;
        // opcode is 3, maar moet wel 12 plaatsen naar links worden geschoven
        IO.writeShort(0x42, opcode << 12); // Clear display
    }
    
    public static void tekenAssen(){

        int opcode = 1;

        for (int x = 0; x < 128; x++){
            IO.writeShort(0x42, opcode << 12 | x << 5 | 16); IO.delay(30);
        }
        for (int y = 0; y < 32; y++){
            IO.writeShort(0x42, opcode << 12 | 64 << 5 | y); IO.delay(30);

        }
    }
    //opdrachtvideo 4A
    public static void parabool() {
        clearDMscreen();
        tekenAssen();
        for(int i = 0; i < 16; i++){
            int x = 64;
            x = x + i;
            double y = (-0.1 * (i * i)) + 26;
            IO.writeShort(0x42, 1 << 12 | x << 5 | (int)y); IO.delay(30);
            int x1 = 64;
            x1 = x1 - i;
            IO.writeShort(0x42, 1 << 12 | x1 << 5 | (int)y); IO.delay(30);
        }
    }
    //opdrachtvideo 4C
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
    //opdrachtvideo 2
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
    //opdrachtvideo 1A
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
    //opdrachtvideo 1B
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
    
    //opdrachtvideo 3
    public static void Animatie() {
        int loops = 0;
        int value1 = 0b100000001;
        int value2 = 0b100100011;
        int value3 = 0b101100010;
        int value4 = 0b101010100;
        int value5 = 0b110011100;
        int value6 = 0b110001000;
        int value7 = 0b100000000;

        IO.delay(2000);
        while (loops < 10) {
            IO.writeShort(0x18, value1);
            IO.writeShort(0x16, value1);
            IO.writeShort(0x14, value1);
            IO.writeShort(0x12, value1);
            IO.writeShort(0x10, value1);
            IO.delay(100);
            IO.writeShort(0x18, value2);
            IO.writeShort(0x16, value2);
            IO.writeShort(0x14, value2);
            IO.writeShort(0x12, value2);
            IO.writeShort(0x10, value2);
            IO.delay(100);
            IO.writeShort(0x18, value3);
            IO.writeShort(0x16, value3);
            IO.writeShort(0x14, value3);
            IO.writeShort(0x12, value3);
            IO.writeShort(0x10, value3);
            IO.delay(100);
            IO.writeShort(0x18, value4);
            IO.writeShort(0x16, value4);
            IO.writeShort(0x14, value4);
            IO.writeShort(0x12, value4);
            IO.writeShort(0x10, value4);
            IO.delay(100);
            IO.writeShort(0x18, value5);
            IO.writeShort(0x16, value5);
            IO.writeShort(0x14, value5);
            IO.writeShort(0x12, value5);
            IO.writeShort(0x10, value5);
            IO.delay(100);
            IO.writeShort(0x18, value6);
            IO.writeShort(0x16, value6);
            IO.writeShort(0x14, value6);
            IO.writeShort(0x12, value6);
            IO.writeShort(0x10, value6);

            IO.writeShort(0x24, value1);
            IO.writeShort(0x22, value1);
            IO.writeShort(0x20, value1);

            IO.writeShort(0x34, value1);
            IO.writeShort(0x32, value1);
            IO.writeShort(0x30, value1);
            IO.delay(100);
            IO.writeShort(0x18, value7);
            IO.writeShort(0x16, value7);
            IO.writeShort(0x14, value7);
            IO.writeShort(0x12, value7);
            IO.writeShort(0x10, value7);

            IO.writeShort(0x24, value2);
            IO.writeShort(0x22, value2);
            IO.writeShort(0x20, value2);

            IO.writeShort(0x34, value2);
            IO.writeShort(0x32, value2);
            IO.writeShort(0x30, value2);
            IO.delay(100);
            IO.writeShort(0x24, value3);
            IO.writeShort(0x22, value3);
            IO.writeShort(0x20, value3);

            IO.writeShort(0x34, value3);
            IO.writeShort(0x32, value3);
            IO.writeShort(0x30, value3);
            IO.delay(100);
            IO.writeShort(0x24, value4);
            IO.writeShort(0x22, value4);
            IO.writeShort(0x20, value4);

            IO.writeShort(0x34, value4);
            IO.writeShort(0x32, value4);
            IO.writeShort(0x30, value4);
            IO.delay(100);
            IO.writeShort(0x24, value5);
            IO.writeShort(0x22, value5);
            IO.writeShort(0x20, value5);

            IO.writeShort(0x34, value5);
            IO.writeShort(0x32, value5);
            IO.writeShort(0x30, value5);
            IO.delay(100);
            IO.writeShort(0x24, value6);
            IO.writeShort(0x22, value6);
            IO.writeShort(0x20, value6);

            IO.writeShort(0x34, value6);
            IO.writeShort(0x32, value6);
            IO.writeShort(0x30, value6);
            IO.delay(100);
            IO.writeShort(0x24, value7);
            IO.writeShort(0x22, value7);
            IO.writeShort(0x20, value7);

            IO.writeShort(0x34, value7);
            IO.writeShort(0x32, value7);
            IO.writeShort(0x30, value7);
            IO.delay(500);

            loops++;
        }
    }

    public static void O_6a_tuimelaar() {
        int loops = 0;
        int value1 = 0b100000001;
        int value2 = 0b100000010;
        int value3 = 0b100000100;
        int value4 = 0b100001000;
        int value5 = 0b100010000;
        int value6 = 0b100100000;
        int value7 = 0b100000001;
        int value8 = 0b100000011;
        int value9 = 0b100000111;
        int value10 = 0b100001111;
        int value11 = 0b100011111;
        int value12 = 0b100111111;
        int value13 = 0b100111111;
        int value14 = 0b100111110;
        int value15 = 0b100111100;
        int value16 = 0b100111000;
        int value17 = 0b100110000;
        int value18 = 0b100100000;
        int value19 = 0b100000000;
        int value20 = 0b100000001;
        int value21 = 0b100100011;
        int value22 = 0b100100010;
        int value23 = 0b101100010;
        int value24 = 0b101000000;
        int value25 = 0b101010100;
        int value26 = 0b100010100;
        int value27 = 0b100011100;
        int value28 = 0b100001000;
        int value29 = 0b100000000;

        while (loops < 2) {

            IO.writeShort(0x30, value1);
            IO.delay(200);
            IO.writeShort(0x30, value2);
            IO.delay(200);
            IO.writeShort(0x30, value3);
            IO.delay(200);
            IO.writeShort(0x30, value4);
            IO.delay(200);
            IO.writeShort(0x30, value5);
            IO.delay(200);
            IO.writeShort(0x30, value6);


            IO.delay(200);

            loops++;
        }

        IO.writeShort(0x30, value7);
        IO.delay(200);
        IO.writeShort(0x30, value8);
        IO.delay(200);
        IO.writeShort(0x30, value9);
        IO.delay(200);
        IO.writeShort(0x30, value10);
        IO.delay(200);
        IO.writeShort(0x30, value11);
        IO.delay(200);
        IO.writeShort(0x30, value12);
        IO.delay(200);
        IO.writeShort(0x30, value13);
        IO.delay(200);
        IO.writeShort(0x30, value14);
        IO.delay(200);
        IO.writeShort(0x30, value15);
        IO.delay(200);
        IO.writeShort(0x30, value16);
        IO.delay(200);
        IO.writeShort(0x30, value17);
        IO.delay(200);
        IO.writeShort(0x30, value18);
        IO.delay(200);
        IO.writeShort(0x30, value19);
        IO.delay(200);
        IO.writeShort(0x30, value20);
        IO.delay(200);
        IO.writeShort(0x30, value21);
        IO.delay(200);
        IO.writeShort(0x30, value22);
        IO.delay(200);
        IO.writeShort(0x30, value23);
        IO.delay(200);
        IO.writeShort(0x30, value24);
        IO.delay(200);
        IO.writeShort(0x30, value25);
        IO.delay(200);
        IO.writeShort(0x30, value26);
        IO.delay(200);
        IO.writeShort(0x30, value27);
        IO.delay(200);
        IO.writeShort(0x30, value28);
        IO.delay(200);
        IO.writeShort(0x30, value29);
        IO.delay(200);

        loops = 0;
        while (loops < 2) {

            IO.writeShort(0x32, value1);
            IO.delay(200);
            IO.writeShort(0x32, value2);
            IO.delay(200);
            IO.writeShort(0x32, value3);
            IO.delay(200);
            IO.writeShort(0x32, value4);
            IO.delay(200);
            IO.writeShort(0x32, value5);
            IO.delay(200);
            IO.writeShort(0x32, value6);


            IO.delay(200);

            loops++;
        }

        IO.writeShort(0x32, value7);
        IO.delay(200);
        IO.writeShort(0x32, value8);
        IO.delay(200);
        IO.writeShort(0x32, value9);
        IO.delay(200);
        IO.writeShort(0x32, value10);
        IO.delay(200);
        IO.writeShort(0x32, value11);
        IO.delay(200);
        IO.writeShort(0x32, value12);
        IO.writeShort(0x32, value13);
        IO.delay(200);
        IO.writeShort(0x32, value14);
        IO.delay(200);
        IO.writeShort(0x32, value15);
        IO.delay(200);
        IO.writeShort(0x32, value16);
        IO.delay(200);
        IO.writeShort(0x32, value17);
        IO.delay(200);
        IO.writeShort(0x32, value18);
        IO.delay(200);
        IO.writeShort(0x32, value19);
        IO.delay(200);
        IO.writeShort(0x32, value20);
        IO.delay(200);
        IO.writeShort(0x32, value21);
        IO.delay(200);
        IO.writeShort(0x32, value22);
        IO.delay(200);
        IO.writeShort(0x32, value23);
        IO.delay(200);
        IO.writeShort(0x32, value24);
        IO.delay(200);
        IO.writeShort(0x32, value25);
        IO.delay(200);
        IO.writeShort(0x32, value26);
        IO.delay(200);
        IO.writeShort(0x32, value27);
        IO.delay(200);
        IO.writeShort(0x32, value28);
        IO.delay(200);
        IO.writeShort(0x32, value29);
        IO.delay(200);

        loops = 0;
        while (loops < 2) {

            IO.writeShort(0x34, value1);
            IO.delay(200);
            IO.writeShort(0x34, value2);
            IO.delay(200);
            IO.writeShort(0x34, value3);
            IO.delay(200);
            IO.writeShort(0x34, value4);
            IO.delay(200);
            IO.writeShort(0x34, value5);
            IO.delay(200);
            IO.writeShort(0x34, value6);


            IO.delay(200);

            loops++;
        }

        IO.writeShort(0x34, value7);
        IO.delay(200);
        IO.writeShort(0x34, value8);
        IO.delay(200);
        IO.writeShort(0x34, value9);
        IO.delay(200);
        IO.writeShort(0x34, value10);
        IO.delay(200);
        IO.writeShort(0x34, value11);
        IO.delay(200);
        IO.writeShort(0x34, value12);
        IO.writeShort(0x34, value13);
        IO.delay(200);
        IO.writeShort(0x34, value14);
        IO.delay(200);
        IO.writeShort(0x34, value15);
        IO.delay(200);
        IO.writeShort(0x34, value16);
        IO.delay(200);
        IO.writeShort(0x34, value17);
        IO.delay(200);
        IO.writeShort(0x34, value18);
        IO.delay(200);
        IO.writeShort(0x34, value19);
        IO.delay(200);
        IO.writeShort(0x34, value20);
        IO.delay(200);
        IO.writeShort(0x34, value21);
        IO.delay(200);
        IO.writeShort(0x34, value22);
        IO.delay(200);
        IO.writeShort(0x34, value23);
        IO.delay(200);
        IO.writeShort(0x34, value24);
        IO.delay(200);
        IO.writeShort(0x34, value25);
        IO.delay(200);
        IO.writeShort(0x34, value26);
        IO.delay(200);
        IO.writeShort(0x34, value27);
        IO.delay(200);
        IO.writeShort(0x34, value28);
        IO.delay(200);
        IO.writeShort(0x34, value29);
        IO.delay(200);

        loops = 0;
        while (loops < 2) {

            IO.writeShort(0x30, value1);
            IO.writeShort(0x32, value1);
            IO.writeShort(0x34, value1);
            IO.delay(200);
            IO.writeShort(0x30, value2);
            IO.writeShort(0x32, value2);
            IO.writeShort(0x34, value2);
            IO.delay(200);
            IO.writeShort(0x30, value3);
            IO.writeShort(0x32, value3);
            IO.writeShort(0x34, value3);
            IO.delay(200);
            IO.writeShort(0x30, value4);
            IO.writeShort(0x32, value4);
            IO.writeShort(0x34, value4);
            IO.delay(200);
            IO.writeShort(0x30, value5);
            IO.writeShort(0x32, value5);
            IO.writeShort(0x34, value5);
            IO.delay(200);
            IO.writeShort(0x30, value6);
            IO.writeShort(0x32, value6);
            IO.writeShort(0x34, value6);


            IO.delay(200);

            loops++;
        }

        IO.writeShort(0x30, value7);
        IO.writeShort(0x32, value7);
        IO.writeShort(0x34, value7);
        IO.delay(200);
        IO.writeShort(0x30, value8);
        IO.writeShort(0x32, value8);
        IO.writeShort(0x34, value8);
        IO.delay(200);
        IO.writeShort(0x30, value9);
        IO.writeShort(0x32, value9);
        IO.writeShort(0x34, value9);
        IO.delay(200);
        IO.writeShort(0x30, value10);
        IO.writeShort(0x32, value10);
        IO.writeShort(0x34, value10);
        IO.delay(200);
        IO.writeShort(0x30, value11);
        IO.writeShort(0x32, value11);
        IO.writeShort(0x34, value11);
        IO.delay(200);
        IO.writeShort(0x30, value12);
        IO.writeShort(0x32, value12);
        IO.writeShort(0x34, value12);
        IO.writeShort(0x30, value13);
        IO.writeShort(0x32, value13);
        IO.writeShort(0x34, value13);
        IO.delay(200);
        IO.writeShort(0x30, value14);
        IO.writeShort(0x32, value14);
        IO.writeShort(0x34, value14);
        IO.delay(200);
        IO.writeShort(0x30, value15);
        IO.writeShort(0x32, value15);
        IO.writeShort(0x34, value15);
        IO.delay(200);
        IO.writeShort(0x30, value16);
        IO.writeShort(0x32, value16);
        IO.writeShort(0x34, value16);
        IO.delay(200);
        IO.writeShort(0x30, value17);
        IO.writeShort(0x32, value17);
        IO.writeShort(0x34, value17);
        IO.delay(200);
        IO.writeShort(0x30, value18);
        IO.writeShort(0x32, value18);
        IO.writeShort(0x34, value18);
        IO.delay(200);
        IO.writeShort(0x30, value19);
        IO.writeShort(0x32, value19);
        IO.writeShort(0x34, value19);
        IO.delay(200);
        IO.writeShort(0x30, value20);
        IO.writeShort(0x32, value20);
        IO.writeShort(0x34, value20);
        IO.delay(200);
        IO.writeShort(0x30, value21);
        IO.writeShort(0x32, value21);
        IO.writeShort(0x34, value21);
        IO.delay(200);
        IO.writeShort(0x30, value22);
        IO.writeShort(0x32, value22);
        IO.writeShort(0x34, value22);
        IO.delay(200);
        IO.writeShort(0x30, value23);
        IO.writeShort(0x32, value23);
        IO.writeShort(0x34, value23);
        IO.delay(200);
        IO.writeShort(0x30, value24);
        IO.writeShort(0x32, value24);
        IO.writeShort(0x34, value24);
        IO.delay(200);
        IO.writeShort(0x30, value25);
        IO.writeShort(0x32, value25);
        IO.writeShort(0x34, value25);
        IO.delay(200);
        IO.writeShort(0x30, value26);
        IO.writeShort(0x32, value26);
        IO.writeShort(0x34, value26);
        IO.delay(200);
        IO.writeShort(0x30, value27);
        IO.writeShort(0x32, value27);
        IO.writeShort(0x34, value27);
        IO.delay(200);
        IO.writeShort(0x30, value28);
        IO.writeShort(0x32, value28);
        IO.writeShort(0x34, value28);
        IO.delay(200);
        IO.writeShort(0x30, value29);
        IO.writeShort(0x32, value29);
        IO.writeShort(0x34, value29);
        IO.delay(200);
    }

}