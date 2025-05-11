package com.kruemel.algoVisualizer.algo.pathfinding;

import com.kruemel.algoVisualizer.main.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    int maxCol, maxRow;
    Node[][] nodes;
    MainPanel mp;
    AStar aStar;
    public Board(int maxCol, int maxRow, MainPanel mp, AStar aStar){
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        this.mp = mp;
        this.aStar = aStar;
        nodes = new Node[maxRow][maxCol];
        this.setPreferredSize(new Dimension(this.mp.getWidth(), this.mp.getHeight()));
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.setFocusable(false);
    }

    public void placeNodes(){
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                nodes[i][j] = new Node(i, j, aStar);
                this.add(nodes[i][j]);
            }
        }
        this.mp.add(this);
        this.mp.window.setVisible(true);
    }


}
