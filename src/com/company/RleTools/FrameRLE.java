package com.company.RleTools;

import java.util.ArrayList;

public class FrameRLE {
    public ArrayList<ArrayList<Integer>> indicies;

    public int[] dimensions = {0, 0}, offset = {0, 0};

    private int counter = 0;
    //private int row = 0;

    public FrameRLE() {
        indicies = new ArrayList<>();
        indicies.add(new ArrayList<>());
    }

    public void add(int index) {
        if (counter > dimensions[0]) {
            counter = 0;
            //row++;

            indicies.add(new ArrayList<Integer>());
        }

        indicies.get(indicies.size() - 1).add(index);
    }
}
