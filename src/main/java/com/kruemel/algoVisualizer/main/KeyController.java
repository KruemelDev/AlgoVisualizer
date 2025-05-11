package com.kruemel.algoVisualizer.main;

import com.kruemel.algoVisualizer.algo.pathfinding.AStar;
import com.kruemel.algoVisualizer.algo.sort.BubbleSort;
import com.kruemel.algoVisualizer.algo.sort.InsertionSort;
import com.kruemel.algoVisualizer.algo.sort.MergeSort;
import com.kruemel.algoVisualizer.algo.sort.SelectionSort;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {

    MainPanel mp;
    public boolean pausePressed = false;
    public KeyController(MainPanel mp) {
        this.mp = mp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                reset();
                this.mp.currentAlgorithm = new SelectionSort(this.mp);
                break;
            case KeyEvent.VK_2:
                reset();
                this.mp.currentAlgorithm = new BubbleSort(this.mp);
                break;
            case KeyEvent.VK_3:
                reset();
                this.mp.currentAlgorithm = new InsertionSort(this.mp);
                break;
            case KeyEvent.VK_4:
                reset();
                this.mp.currentAlgorithm = new MergeSort(this.mp);
                break;
            case KeyEvent.VK_5:
                reset();
                this.mp.currentAlgorithm = new AStar(this.mp);
                break;
            case KeyEvent.VK_P:
                pausePressed = true;
                break;
            case KeyEvent.VK_ENTER:
                if (this.mp.currentAlgorithm != null) {
                    Thread thread = new Thread(() -> this.mp.currentAlgorithm.Run());
                    thread.start();
                }
                break;
            case KeyEvent.VK_SPACE:
                if (this.mp.currentAlgorithm != null) this.mp.currentAlgorithm.Step();
                break;
            case KeyEvent.VK_ESCAPE:
                reset();
                this.mp.currentAlgorithm = null;
                break;

        }
        this.mp.repaint();
    }
    private void reset(){
        if (this.mp.currentAlgorithm != null) this.mp.currentAlgorithm.Reset();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                pausePressed = false;
                break;
        }
    }
}
