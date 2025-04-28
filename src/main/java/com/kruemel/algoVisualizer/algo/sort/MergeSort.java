package com.kruemel.algoVisualizer.algo.sort;

import com.kruemel.algoVisualizer.algo.Algorithm;
import com.kruemel.algoVisualizer.algo.Util;
import com.kruemel.algoVisualizer.main.MainPanel;

import java.awt.*;

public class MergeSort implements Algorithm {
    int[] toSort;
    MainPanel mp;
    Util util;
    InsertionSort insertionSort;

    public MergeSort(MainPanel mp) {
        this.mp = mp;
        util = new Util(mp);
        toSort = util.GenRandomNumberArray(this.mp.getWidth(), this.mp.getHeight() - 80);
        insertionSort = new InsertionSort(mp);
    }

    @Override
    public void Step() {

    }

    @Override
    public void Run() {
        mergeSort(0, toSort.length - 1);
        this.mp.repaint();
    }
    private void mergeSort(int left, int right){
        if (left < right){
            int mid  = (left + right - 1) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }
    private void merge(int left, int mid, int right){
        int divider = mid + 1;
        int leftCache = left;
        int[] mergedList = new int[right - left + 1];

        for (int i = 0; i < mergedList.length; i++) {
            if (left > mid) {
                mergedList[i] = toSort[divider];
                divider++;
            }
            else if (divider > right) {
                mergedList[i] = toSort[left];
                left++;
            }
            else if (toSort[left] < toSort[divider]) {
                mergedList[i] = toSort[left];
                left++;
            }
            else if (toSort[divider] <= toSort[left]) {
                mergedList[i] = toSort[divider];
                divider++;
            }
        }
        for (int i = 0; i < mergedList.length; i++) {
            toSort[i + leftCache] = mergedList[i];
            this.mp.repaint();
            try{
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void drawArray(Graphics2D g2d){
        int colWidth = this.mp.getWidth() / toSort.length;
        for (int i = 0; i < toSort.length; i++) {
            g2d.setColor(Color.WHITE);
            int colHeight = toSort[i];
            g2d.fillRect(i * colWidth, this.mp.getHeight() - colHeight, colWidth, colHeight);
        }
    }

    @Override
    public void Paint(Graphics2D g2d) {
        util.DrawTitle(g2d, "Merge sort: ");
        drawArray(g2d);
    }
}
