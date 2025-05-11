package com.kruemel.algoVisualizer.algo.pathfinding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {
    Node parent;
    int row, col;
    int gCost, hCost, fCost;
    boolean start, goal;
    boolean solid;
    boolean checked;

    AStar aStar;
    public Node(int row, int col, AStar aStar){
        this.row = row;
        this.col = col;
        this.aStar = aStar;
        setBackground(Color.WHITE);
        setForeground(Color.BLUE);
        addActionListener(this);
        this.setFocusable(false);
    }

    public void setStartNode(){
        setBackground(Color.GREEN);
        this.setForeground(Color.WHITE);
        setText("Start");
        start = true;
    }
    public void setGoalNode(){
        setBackground(Color.blue);
        setForeground(Color.WHITE);
        setText("Goal");
        goal = true;
    }
    public void setPathNode(){
        setBackground(Color.RED);
        setForeground(Color.WHITE);
    }
    public void toggleSolid(){
        if (solid) {
            this.showCost();
            setBackground(Color.WHITE);
        }
        else {
            this.setText("");
            setBackground(Color.BLACK);
        }

        solid = !solid;
    }
    public void showCost(){
        setForeground(Color.BLACK);
        setText("<html>G: " + gCost + "<br>" + "H: " + hCost + "<br>" + "F: " + fCost + "</html>");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.start || this.goal) return;
        if (aStar.running) return;
        toggleSolid();
    }
}
