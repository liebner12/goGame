package com.michalliebner.sebastianmaraszek.team.gui_swing.ui.WinnerFrame;

import javax.swing.*;
import java.awt.*;

public class WinnerFramePanel extends JPanel {
    private static final int WIDTH = 424;
    private static final int HEIGHT = 200;
    private JLabel gameMode;

    public WinnerFramePanel() {
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color2 = new Color(113, 89, 68);
        Color color1 = new Color(193, 161, 121);
        GradientPaint gp = new GradientPaint(90, 300, color1, 4, HEIGHT, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void setText(){
        gameMode = new JLabel();
        gameMode.setBackground(new Color(60, 58, 60));
        gameMode.setBounds(90, 30, 240, 32);
        gameMode.setFont(new Font("Arial", Font.BOLD, 24));
        gameMode.setForeground(new Color(245, 245, 249));
    }
    public void initStartFrame() {
        setText();
        add(gameMode);
    }

    public JLabel getText(){
        return gameMode;
    }


}
