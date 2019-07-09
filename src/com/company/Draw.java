package com.company;

import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.kern.Farbe;
import sum.kern.Muster;

public class Draw {
    Bildschirm bildschirm;
    public Buntstift buntstift;
    int hCounter;
    int vCounter;
    int width;
    int heightAdd;
    int height;
    int xOffset, yOffset;

    double lastFrame;

    public Draw() {
        bildschirm = new Bildschirm(true);
        buntstift = new Buntstift();
        buntstift.setzeFuellMuster(Muster.GEFUELLT);
        heightAdd = 0;
        hCounter = 0;
        vCounter = 0;
        xOffset = 0;
        yOffset = 0;
        width = 0;
        lastFrame = System.currentTimeMillis();
    }

    public boolean pixel(boolean draw) {
        if (hCounter >= width) {
            if (vCounter < height)
                vCounter++;
            else
                return false;
            hCounter = 0;
        }

        if (draw) {
            buntstift.bewegeBis(1*(xOffset + hCounter), 1*(yOffset + heightAdd + vCounter));
            buntstift.zeichneRechteck(1, 1);
        }

        hCounter++;

        return true;
    }

    public void setDims(int width, int height, int xOffset, int yOffset) {
        double thisFrame = System.currentTimeMillis() - lastFrame;
        lastFrame = System.currentTimeMillis();

        if (thisFrame < 1000/10) {
            try {
                Thread.sleep((long) (1000 / 10 - thisFrame));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        bildschirm.zeichneDich();

        buntstift.bewegeBis(0, 0);
        buntstift.setzeFarbe(Farbe.SCHWARZ);
        buntstift.zeichneRechteck(bildschirm.breite(), bildschirm.hoehe());

        this.xOffset = xOffset;
        this.yOffset = yOffset;
        hCounter = 0;
        vCounter = 0;
        this.width = width;
        this.height = height;
    }
}
