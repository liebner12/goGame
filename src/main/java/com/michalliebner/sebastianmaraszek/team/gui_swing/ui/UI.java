package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import javax.swing.*;
import java.awt.*;

class UI extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 700;
    UI(){
        setBounds(770,30,WIDTH,HEIGHT);
    }
    protected void paintComponent(Graphics g) {


        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color2 = new Color(113, 89, 68);
        Color color1 = new Color(193 ,161, 121);
        GradientPaint gp = new GradientPaint(90, 300, color1, 4, h, color2);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, w, h,15,15);
        g2d.dispose();
        super.paintComponent(g);
    }
}
