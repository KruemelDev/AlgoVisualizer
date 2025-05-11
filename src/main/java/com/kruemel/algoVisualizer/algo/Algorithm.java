package com.kruemel.algoVisualizer.algo;

import java.awt.*;

public interface Algorithm {
    void Step();
    void Run();
    void Reset();
    void Paint(Graphics2D g2d);

}
