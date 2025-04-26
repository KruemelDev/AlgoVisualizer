package com.kruemel.algoVisualizer.algo.sort;

import com.kruemel.algoVisualizer.algo.Algorithm;
import com.kruemel.algoVisualizer.algo.Util;
import com.kruemel.algoVisualizer.main.MainPanel;

import java.awt.*;

public class MergeSort implements Algorithm {
    int[] toSort;
    MainPanel mp;
    Util util;

    public MergeSort(MainPanel mp) {
        this.mp = mp;
        util = new Util(mp);
        toSort = util.GenRandomNumberArray(this.mp.getWidth(), this.mp.getHeight() - 80);
    }

    @Override
    public void Step() {

    }

    @Override
    public void Run() {

    }

    @Override
    public void Paint(Graphics2D g2d) {

    }
}
