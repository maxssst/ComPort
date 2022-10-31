package com.company;

import arduino.Arduino;


import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        Arduino arduino = new Arduino("COM3", 9600);

        boolean connected = arduino.openConnection();
        System.out.println("Connected: " + connected + arduino.getSerialPort());

        String data = "";
        int count = 0;

        while (arduino.openConnection()) {
            for (int i = 0; i < 10; i++) {
                if (count <= 100) {
                    data += arduino.serialRead();
                    System.out.println(data);
                    count ++;
                    Thread.sleep(4000);
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("outputData" + count + ".txt"));
                    writer.write(data);
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Thread.sleep(2000);
            }


        }

    }
}