package com.company.RleTools;

import java.util.ArrayList;

public class AnimatedRLE {
    public ArrayList<int[]>  colorTable;  //b,g,r
    public ArrayList<FrameRLE> frames;

    int[] dimensions = {0, 0};

    public AnimatedRLE() {
        colorTable = new ArrayList<>();
        frames = new ArrayList<>();
    }
}
