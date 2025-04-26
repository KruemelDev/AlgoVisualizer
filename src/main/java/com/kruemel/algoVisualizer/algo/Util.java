package com.kruemel.algoVisualizer.algo;

import com.kruemel.algoVisualizer.main.MainPanel;

import java.awt.*;
import java.util.Random;

public class Util{
    private static final Random random = new Random();
    public MainPanel mp;

    public Util(MainPanel mp){
        this.mp = mp;
    }

    public int[] GenRandomNumberArray(int length, int maxInt){
        int[] array = new int[length];
        for(int i = 0; i < length; i++){
            array[i] = random.nextInt(maxInt);
        }
        return array;
    }
    public void DrawTitle(Graphics2D g2d, String title) {
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(title);
        int textHeight = fm.getHeight();

        int x = (this.mp.getWidth() - textWidth) / 2;
        int y = (80 - textHeight) / 2 + fm.getAscent();

        g2d.drawString(title, x, y);
    }
}
