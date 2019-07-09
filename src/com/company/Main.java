package com.company;
import sum.kern.Farbe;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String HexFile = "D:\\Games\\Schatzjäger\\data1\\data\\player\\sprites\\mh_hang_move_left.rle";
        FileInputStream inputStream = new FileInputStream(HexFile);

        ArrayList<Integer> file = new ArrayList<>();

        int read;
        while ((read = inputStream.read()) != -1) { //ArrayList is easier to handle
            file.add(read);
        }
        int counter = 0;

        if (file.get(counter) != 103) { //basic checking, first two bytes should equal HEX 67 == DEC 103
            System.err.println("This file is not a valid rle file!");
            System.exit(10);
        } else {
            System.out.println("File should be valid.");
        }

        counter = 3*4; //First value has to be clear as ArrayList starts at 0, whereas file counts from 1

        ArrayList<int[]> colorTable = new ArrayList<>(); //color table

        while (counter < ((128*4*4) + (3*4))) { //create color table
            int[] color = new int[4];
            color[0] = file.get(counter);
            counter++;
            color[1] = file.get(counter);
            counter++;
            color[2] = file.get(counter);
            counter++;
            //4th value is 0 every time
            counter++;

            colorTable.add(color);
        }

        //System.out.println(colorTable.get(3)[0] + " " + colorTable.get(3)[1] + " " + colorTable.get(3)[2] + " ");

        counter = (128*4*4) + (3*4); //if something went wrong when making the color table

        int width = file.get(counter);
        counter = counter + 4;
        int height = file.get(counter);
        counter = counter + 12;

        int layersCount = file.get(counter);
        counter = counter + 4;

        Draw draw = new Draw();

        while (true) {
            counter = (128*4*4) + (3*4) + 4 + 12 + 4;

            try {
                for (int l = 0; l < layersCount; l++) {
                    int localWidth = file.get(counter);
                    counter = counter + 4;
                    int localHeight = file.get(counter);
                    counter = counter + 4;
                    int OffsetRight = file.get(counter);
                    counter = counter + 4;
                    int OffsetUp = file.get(counter);
                    counter = counter + 4;
                    draw.setDims(localWidth, localHeight, OffsetRight, OffsetUp);
                    //System.out.println(Integer.toHexString(localWidth) + ", " + Integer.toHexString(localHeight));
                    counter = counter + 16;

                    while (true) {
                        int z;

                        z = countForHexChar(Integer.toHexString(file.get(counter)).charAt(0));
                        try {
                            countForHexChar(Integer.toHexString(file.get(counter)).charAt(1));
                        } catch (StringIndexOutOfBoundsException e) {
                            z = 0;
                        }

                        if (((draw.vCounter + 1) == localHeight) && ((draw.hCounter + 1) > localWidth)) {
                            break;
                        }

                        if (z < 10) { //First Byte says if you have to copy next x bytes or repeat the same two bytes x times
                            int repeat = file.get(counter);
                            counter++;
                            for (int i = 0; i < repeat; i++) {
                                draw(file, counter, draw, colorTable);
                            }
                            counter++;
                        } else {
                            int repeat = 256 - file.get(counter);
                            counter++;
                            for (int i = 0; i < repeat; i++) {
                                draw(file, counter, draw, colorTable);
                                counter++;
                            }
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void draw(ArrayList<Integer> file, int counter, Draw draw, ArrayList<int[]> colors) {
        draw.buntstift.setzeFarbe(Farbe.rgb(colors.get(file.get(counter))[2], colors.get(file.get(counter))[1], colors.get(file.get(counter))[0]));

        //if (file.get(counter) != 2) {
            draw.pixel(true);
        //} else {
        //    draw.pixel(false);
        //}
    }

    private static int countForHexChar(char z) {
        switch (z) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'a':
                return 10;
            case 'b':
                return 11;
            case 'c':
                return 12;
            case 'd':
                return 13;
            case 'e':
                return 14;
            case 'f':
                return 15;
            default:
                return -1;
        }
    }
}
