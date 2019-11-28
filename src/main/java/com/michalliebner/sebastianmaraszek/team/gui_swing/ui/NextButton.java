package com.michalliebner.sebastianmaraszek.team.gui_swing.ui;


import javax.swing.*;
import java.awt.*;

public class NextButton extends JButton {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
        public NextButton() {
            setForeground(new Color(222, 227, 225));
            setBorderPainted(false);
            setContentAreaFilled(false);
            setBounds(930,630,WIDTH,HEIGHT);
            setText("PASS");
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g.create();
            RenderingHints qualityHints =
                    new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHints(qualityHints);


            Color color2 = new Color(72, 44, 114);
            GradientPaint gp = new GradientPaint(90, 300, color2, 4, 40, color2);
            g2.setPaint(gp);

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
            super.paintComponent(g);
        }

}
