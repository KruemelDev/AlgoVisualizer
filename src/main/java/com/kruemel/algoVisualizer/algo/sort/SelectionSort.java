package com.kruemel.algoVisualizer.algo.sort;

import com.kruemel.algoVisualizer.algo.Algorithm;
import com.kruemel.algoVisualizer.algo.Util;
import com.kruemel.algoVisualizer.main.MainPanel;

import java.awt.*;

public class SelectionSort implements Algorithm {
    int[] toSort;
    MainPanel mp;
    Util util;
    int currentIndex;
    int startIndex;
    int minIndex = 0;
    public SelectionSort(MainPanel mp) {
        this.mp = mp;
        this.util = new Util(mp);
        toSort = util.GenRandomNumberArray(this.mp.getWidth(), this.mp.getHeight() - 80);
    }
    int lastI = 0;
    int lastJ = 1;
    @Override
    public void Step() {
        if (lastI >= toSort.length - 1) return;
        if (lastJ == lastI + 1) minIndex = lastI;

        currentIndex = lastJ;

        if (toSort[lastJ] < toSort[minIndex]) {
            minIndex = lastJ;
        }
        lastJ++;

        if (lastJ >= toSort.length) {
            int temp = toSort[lastI];
            toSort[lastI] = toSort[minIndex];
            toSort[minIndex] = temp;

            lastI++;
            lastJ = lastI + 1;
            startIndex = lastI;
            minIndex = lastI;
        }

        mp.repaint();
    }


    @Override
    public void Run() {
        for (int i = lastI; i < toSort.length - 1; i++) {
            lastI = i;
            minIndex = lastI;

            for (int j = lastJ; j < toSort.length; j++) {
                if (mp.currentAlgorithm != this || mp.keyController.pausePressed) return;
                if (toSort[j] < toSort[minIndex]) {
                    minIndex = j;
                }
                lastJ = j;
                currentIndex = lastJ;
                mp.repaint();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (minIndex != lastI) {
                int temp = toSort[lastI];
                toSort[lastI] = toSort[minIndex];
                toSort[minIndex] = temp;
            }

            lastI++;
            lastJ = lastI + 1;
            startIndex = lastI;
        }
    }

    private void drawArray(Graphics2D g2d){
        int colWidth = this.mp.getWidth() / toSort.length;
        for (int i = 0; i < toSort.length; i++) {
            if (i == currentIndex) g2d.setColor(Color.RED);
            else if (i == startIndex) g2d.setColor(Color.BLUE);
            else if (i == minIndex) g2d.setColor(Color.YELLOW);
            else g2d.setColor(Color.WHITE);

            int colHeight = toSort[i];
            g2d.fillRect(i * colWidth, this.mp.getHeight() - colHeight, colWidth, colHeight);
        }
    }

    @Override
    public void Paint(Graphics2D g2d) {
        util.DrawTitle(g2d, "Selection Sort: ");
        drawArray(g2d);
    }
}
