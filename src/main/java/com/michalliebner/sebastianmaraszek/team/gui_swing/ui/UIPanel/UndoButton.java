package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.UIPanel;

import javax.swing.*;
import java.awt.*;

public class UndoButton extends JButton {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    UndoButton() {
        setForeground(new Color(222, 227, 225));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBounds(207, 630, WIDTH, HEIGHT);
        setText("UNDO");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        RenderingHints qualityHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);

        Color color1 = new Color(80, 78, 80);
        Color color2 = new Color(46, 45, 51);
        GradientPaint gp = new GradientPaint(0, 0, color1, 4, 40, color2);
        g2.setPaint(gp);

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.dispose();
        super.paintComponent(g);
    }
}
