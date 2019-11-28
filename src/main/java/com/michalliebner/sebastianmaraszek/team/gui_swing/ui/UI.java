package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;

import javax.swing.*;
import java.awt.*;

class UI extends JPanel {
    UI(){
        setMinimumSize(new Dimension(600,800));
        setBackground(new Color(45, 45, 45));
        setBounds(700,0,500,800);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.DARK_GRAY;
        Color color2 = Color.GRAY;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
