package com.kruemel.algoVisualizer.algo.sort;

import com.kruemel.algoVisualizer.algo.Algorithm;
import com.kruemel.algoVisualizer.algo.Util;
import com.kruemel.algoVisualizer.main.MainPanel;

import java.awt.*;

public class BubbleSort implements Algorithm {
    int[] toSort;
    MainPanel mp;
    Util util;

    int firstIndex = 0;
    int secondIndex = 0;
    public BubbleSort(MainPanel mp) {
        this.mp = mp;
        util = new Util(mp);
        toSort = util.GenRandomNumberArray(this.mp.getWidth(), this.mp.getHeight() - 80);
    }

    @Override
    public void Step() {

    }

    @Override
    public void Run() {
        for (int i = 0; i < toSort.length; i++) {
            for (int j = 0; j < toSort.length - 1 - i; j++) {
                if (mp.currentAlgorithm != this || mp.keyController.pausePressed) return;
                firstIndex = j;
                secondIndex = j + 1;
                if (toSort[j] > toSort[j + 1]) {
                    int temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                this.mp.repaint();
            }
        }
    }
    private void drawArray(Graphics2D g2d){
        int colWidth = this.mp.getWidth() / toSort.length;
        for (int i = 0; i < toSort.length; i++) {
            if (i == firstIndex) g2d.setColor(Color.RED);
            else if (i == secondIndex) g2d.setColor(Color.YELLOW);
            else g2d.setColor(Color.WHITE);

            int colHeight = toSort[i];
            g2d.fillRect(i * colWidth, this.mp.getHeight() - colHeight, colWidth, colHeight);
        }
    }
    @Override
    public void Reset(){

    }
    @Override
    public void Paint(Graphics2D g2d) {
        util.DrawTitle(g2d, "Bubble Sort: ");
        drawArray(g2d);
    }
}
