package com.kruemel.algoVisualizer.algo.sort;

import com.kruemel.algoVisualizer.main.MainPanel;

import java.util.Random;

public class Util{
    private static final Random random = new Random();

    public static int[] GenRandomNumberArray(int length, int maxInt){
        int[] array = new int[length];
        for(int i = 0; i < length; i++){
            array[i] = random.nextInt(maxInt);
        }
        return array;
    }

}
