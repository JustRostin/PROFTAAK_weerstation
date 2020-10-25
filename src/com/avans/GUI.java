package com.avans;

public class GUI {

    public static void clearDM(){
        int[] address = {0x18,0x16,0x14,0x12,0x10,0x24,0x22,0x20,0x34,0x32,0x30};
        for (int i = 0; i < address.length; i++) {
            IO.writeShort((short)address[i], 0b100000000);
        }
        IO.writeShort(0x40,0xFE);
        IO.writeShort(0x40,0x01);
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

    public static void writeNumber(int address, int number, boolean comma) {
        if (comma) {
            short[] values = {0b110111111, 0b110000110, 0b111011011, 0b111001111, 0b111100110, 0b111101101, 0b111111101, 0b110000111, 0b111111111, 0b111101111};
            IO.writeShort(address, values[number]);
        } else {
            IO.writeShort(address, number);
        }
    }

    public static void writeValue(String type, int value, int commaPositie){
        boolean isNegatief;
        if (value < 0) {
            isNegatief = true;
            value = value * -1;
        } else {
            isNegatief = false;
        }
        if (type.equals("Left")){
            int[] address = {0x20,0x22,0x24};
            for (int i = 0; i < address.length; i++){
                if ((value%10 == 0)&&(isNegatief)) {
                    IO.writeShort(address[i], 0b101000000);
                    isNegatief = false;
                    break;
                }
                if (i==commaPositie){
                    GUI.writeNumber(address[i],(value%10),true);
                } else {
                    GUI.writeNumber(address[i],(value%10),false);
                }
                value = value / 10;
            }
        } else if (type.equals("Top")) {
            int[] address = {0x10,0x12,0x14,0x16,0x18};
            for (int i = 0; i < address.length; i++){
                if ((value%10 == 0)&&(isNegatief)) {
                    IO.writeShort(address[i], 0b101000000);
                    isNegatief = false;
                    break;
                }
                if (i==commaPositie){
                    GUI.writeNumber(address[i],(value%10),true);
                } else {
                    GUI.writeNumber(address[i],(value%10),false);
                }
                value = value / 10;
            }
        } else if (type.equals("Right")) {
            int[] address = {0x30,0x32,0x34};
            for (int i = 0; i < address.length; i++){
                if ((value%10 == 0)&&(isNegatief)) {
                    IO.writeShort(address[i], 0b101000000);
                    isNegatief = false;
                    break;
                }
                if (i==commaPositie){
                    GUI.writeNumber(address[i],(value%10),true);
                } else {
                    GUI.writeNumber(address[i],(value%10),false);
                }
                value = value / 10;
            }
        }
    }

}
