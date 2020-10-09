package com.avans;

public class GUI {
    public static void clearDM(){

        IO.writeShort(0x40,0xFE);
        IO.writeShort(0x40,0x01);
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

}
