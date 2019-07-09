package com.company;

import com.company.RleTools.AnimatedRLE;
import com.company.RleTools.FrameRLE;
import com.company.RleTools.RleImage;
import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.kern.Farbe;
import sum.kern.Muster;

import java.io.IOException;

public class Draw2 {
    public static void main(String[] args) {
        Bildschirm bildschirm = new Bildschirm();
        Buntstift buntstift = new Buntstift();
        buntstift.setzeFuellMuster(Muster.GEFUELLT);
        AnimatedRLE image = null;

        try {
            image = RleImage.read("D:\\Games\\Schatzjäger\\data1\\data\\player\\sprites\\mh_hang_move_left.rle");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        FrameRLE frame = image.frames.get(0);

        for (int i = 0; i < frame.dimensions[0]; i++) {
            for (int j = 0; i < frame.dimensions[1]; j++) {
                buntstift.setzeFarbe(Farbe.rgb(image.colorTable.get((frame.indicies.get(i).get(j)))[2], image.colorTable.get(frame.indicies.get(i).get(j))[1], image.colorTable.get(frame.indicies.get(i).get(j))[0]));
                buntstift.bewegeBis(i, j);
                buntstift.zeichneRechteck(1, 1);
            }
        }
    }
}
