package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel.Results;

import javax.swing.*;
import java.awt.*;


public class ResultScore extends JTextField {

    ResultScore() {
        setForeground(new Color(245, 245, 249));
        setOpaque(false);
        setEditable(false);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("SansSerif", Font.BOLD, 18));
        setForeground(new Color(245, 245, 249));
    }

    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        Color color1 = new Color(60, 58, 60);
        Color color2 = new Color(49, 48, 54);
        GradientPaint gp = new GradientPaint(0, 0, color1, 4, 40, color2);
        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints qualityHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(qualityHints);
        g2d.setColor(new Color(45, 45, 45));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }

}


