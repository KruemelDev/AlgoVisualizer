package com.kruemel.algoVisualizer.main;

import com.kruemel.algoVisualizer.algo.Algorithm;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final String windowTitle = "AlgoVisualizer";
    public final int windowWidth = 1280;
    public final int windowHeight = 720;

    public JFrame window;
    public KeyController keyController = new KeyController(this);

    public Algorithm currentAlgorithm;
    public MainPanel() {
        window = new JFrame(windowTitle);

        window.setMinimumSize(new Dimension(windowWidth, windowHeight));
        window.setPreferredSize(new Dimension(windowWidth, windowHeight));

        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(new Color(0, 0, 0));
        window.setVisible(true);
        window.add(this);

        setFocusable(true);
        addKeyListener(keyController);
    }
    private void PaintMainPanelView(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "AlgoVisualizer; Press 1-9 to select algo";
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        int x = (windowWidth - textWidth) / 2;
        int y = (windowHeight - textHeight) / 2 + fm.getAscent();

        g2d.drawString(text, x, y);

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (currentAlgorithm == null) PaintMainPanelView(g2d);
        else currentAlgorithm.Paint(g2d);
        g2d.dispose();
    }
}
