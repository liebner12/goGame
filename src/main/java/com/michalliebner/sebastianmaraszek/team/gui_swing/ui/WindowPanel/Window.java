package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WindowPanel;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    public Window() {
        setBounds(0, 0, WIDTH, HEIGHT);
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(60, 58, 60);
        Color color2 = new Color(49, 48, 54);
        GradientPaint gp = new GradientPaint(90, 300, color1, 4, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}

