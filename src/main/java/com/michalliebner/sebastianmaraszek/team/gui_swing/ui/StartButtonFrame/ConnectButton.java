package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.StartButtonFrame;
import javax.swing.*;
import java.awt.*;

public class ConnectButton extends JButton {
    private static final int WIDTH = 172;
    private static final int HEIGHT = 40;

    ConnectButton() {
        Cursor cursor= new Cursor(Cursor.HAND_CURSOR);
        setCursor(cursor);
        setForeground(new Color(222, 227, 225));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBounds(20, 100, WIDTH, HEIGHT);
        setText("Multi  player");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        RenderingHints qualityHints =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(qualityHints);

        Color color1 = new Color(60, 58, 60);
        Color color2 = new Color(49, 48, 54);
        GradientPaint gp = new GradientPaint(0, 0, color1, 4, 40, color2);
        g2.setPaint(gp);

        g2.fillRoundRect(0, 0, WIDTH, HEIGHT, 20, 20);
        g2.dispose();
        super.paintComponent(g);
    }


}
